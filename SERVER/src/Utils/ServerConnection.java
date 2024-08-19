package Utils;

import java.io.*;

public class ServerConnection {
    private int id;
    private String name;
    private String kingdom;
    private File connectionDataFile;
    private File connectionObjFile;
    private boolean initialized;

    public ServerConnection(int id) {
        this.id = id;
        this.connectionDataFile = new File("connections/" + id + ".dat");
        this.connectionObjFile = new File("connections/" + id + ".obj");
    }

    public void initialize() throws IOException {
        DataInputStream in = getDataInputStream();
        in.readInt();
        Utils.readString(in);
        this.name = Utils.readString(in);
        this.kingdom = Utils.readString(in);
        in.close();
        initialized = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKingdom() {
        return kingdom;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public File getConnectionDataFile() {
        return connectionDataFile;
    }

    public File getConnectionObjFile() {
        return connectionObjFile;
    }

    public long getLastModified() {
        return connectionDataFile.lastModified();
    }

    public DataInputStream getDataInputStream() throws IOException {
        return new DataInputStream(new FileInputStream(connectionDataFile));
    }

    public ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(new FileInputStream(connectionObjFile));
    }

    public DataOutputStream getDataOutputStream() throws IOException {
        return new DataOutputStream(new FileOutputStream(connectionDataFile));
    }

    public ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(new FileOutputStream(connectionObjFile));
    }

    public void deleteDataConnection() {
        connectionDataFile.delete();
    }

    public void deleteObjConnection() {
        connectionObjFile.delete();
    }

    public String toString() {
        return id + ": " + name;
    }

}
