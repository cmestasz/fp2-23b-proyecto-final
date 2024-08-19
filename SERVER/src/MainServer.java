
import Utils.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import FX.MainGame.Board;

public class MainServer extends Thread implements MainMenuOperation, MainGameOperation {
    private ArrayList<ServerConnection> connectionsList = new ArrayList<ServerConnection>();
    private HashMap<Integer, Long> lastModifiedMap = new HashMap<Integer, Long>(); // Guarda CUALQUIER archivo que es recibido o cambiado
    private int totalConnections;
    private boolean active = true;
    private int tickRate;

    private HashMap<String, int[]> matches = new HashMap<String, int[]>();

    public MainServer(int tickRate) {
        this.tickRate = tickRate;
    }

    public void run() {
        File directory = new File("connections");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            while (active) {
                int newTotalConnections = 0;
                for (File file : directory.listFiles())
                    if (file.getName().endsWith(".dat"))
                        newTotalConnections++;
                System.out.println(newTotalConnections);

                if (totalConnections != newTotalConnections) {
                    for (int id = totalConnections; id < newTotalConnections; id++) {
                        // Se crea una nueva conexión y se agrega a la lista.
                        ServerConnection connection = new ServerConnection(totalConnections);
                        System.out.println("connecting: " + connection);
                        connectionsList.add(connection);
                        lastModifiedMap.put(totalConnections, connection.getLastModified());
                    }
                    totalConnections = newTotalConnections;

                }

                for (int id = 0; id < totalConnections; id++)
                    respond(id);

                sleep(tickRate);
            }

            for (ServerConnection connection : connectionsList) {
                connection.deleteDataConnection();
                connection = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void end() {
        active = false;
    }

    private void respond(int id) {
        ServerConnection connection = connectionsList.get(id);
        System.out.println("responding: " + connection);
        long lastModified = connection.getLastModified();

        // Se verifica si la conexión ha sido modificada desde la última respuesta.
        if (lastModifiedMap.get(id) != lastModified) {
            try {
                if (!connection.isInitialized())
                    connection.initialize();

                DataInputStream in = connection.getDataInputStream();
                int operation = in.readInt();
                String code = Utils.readString(in);

                int[] ids;
                int idOther;
                ServerConnection other;
                DataOutputStream toHost;
                DataOutputStream toGuest;
                DataOutputStream toOther;
                switch (operation) {
                    case OPERATION_CREATE:
                        // Se almacena la información de la conexión que ha creado un nuevo código.
                        matches.put(code, new int[] { connection.getId(), -1 });
                        lastModifiedMap.put(id, lastModified);
                        break;

                    case OPERATION_JOIN:
                        // Se intenta unir dos conexiones con el código.
                        Utils.readString(in);
                        Utils.readString(in);
                        int otherId = in.readInt();
                        ids = matches.get(code);
                        toGuest = connection.getDataOutputStream();
                        toGuest.writeInt(RESPONSE_GUEST);
                        if (ids != null && ids[1] == -1) {
                            ServerConnection host = connectionsList.get(ids[0]);
                            ids[1] = connection.getId();
                            Utils.writeString(toGuest, host.getName());
                            Utils.writeString(toGuest, host.getKingdom());

                            toHost = host.getDataOutputStream();
                            toHost.writeInt(RESPONSE_HOST);
                            Utils.writeString(toHost, connection.getName());
                            Utils.writeString(toHost, connection.getKingdom());
                            toHost.writeInt(otherId);

                            toHost.close();
                            lastModifiedMap.put(host.getId(), host.getLastModified());
                        }
                        toGuest.writeChar(0);
                        toGuest.close();
                        lastModifiedMap.put(id, connection.getLastModified());
                        break;

                    case OPERATION_START:
                        // Se inicia la conexión del invitado con el código.
                        ObjectInputStream inObj = connection.getObjectInputStream();
                        Board board = (Board) inObj.readObject();
                        board.invertBoard();
                        inObj.close();
                        connection.deleteObjConnection();

                        int idGuest = matches.get(code)[1];
                        ServerConnection guest = connectionsList.get(idGuest);

                        toGuest = guest.getDataOutputStream();
                        ObjectOutputStream toGuestObj = guest.getObjectOutputStream();
                        toGuest.writeInt(RESPONSE_START);
                        toGuestObj.writeObject(board);
                        toGuest.close();
                        toGuestObj.close();

                        lastModifiedMap.put(idGuest, guest.getLastModified());
                        lastModifiedMap.put(id, connection.getLastModified());
                        break;

                    case OPERATION_CHAT:
                        String message = Utils.readString(in);
                        message.replaceAll("\n", "");
                        ids = matches.get(code);
                        idOther = id == ids[0] ? ids[1] : ids[0];
                        other = connectionsList.get(idOther);

                        toOther = other.getDataOutputStream();
                        toOther.writeInt(RESPONSE_CHAT);
                        Utils.writeString(toOther, message);
                        toOther.close();

                        lastModifiedMap.put(id, connection.getLastModified());
                        lastModifiedMap.put(idOther, other.getLastModified());
                        break;

                    case OPERATION_MOVE:
                    case OPERATION_ATTACK:
                        int sI = in.readInt();
                        int sJ = in.readInt();
                        int oI = in.readInt();
                        int oJ = in.readInt();

                        ids = matches.get(code);
                        idOther = id == ids[0] ? ids[1] : ids[0];
                        other = connectionsList.get(idOther);

                        toOther = other.getDataOutputStream();
                        toOther.writeInt(operation == OPERATION_MOVE ? RESPONSE_MOVE : RESPONSE_ATTACK);
                        Utils.writeIdxs(toOther, sI, sJ, oI, oJ);
                        toOther.close();

                        lastModifiedMap.put(id, connection.getLastModified());
                        lastModifiedMap.put(idOther, other.getLastModified());
                        break;

                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DBConnector();
        int tickRate = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tiempo entre ticks (en milisegundos):"));
        MainServer server = new MainServer(tickRate);
        server.start();
        JOptionPane.showMessageDialog(null, "El servidor esta ejecutandose correctamente\nPresione ok para detenerlo");
        server.end();
    }
}
