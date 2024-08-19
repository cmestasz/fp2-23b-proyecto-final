package Utils;

import java.io.*;

public class Utils {
    public static String readString(DataInputStream in) throws IOException {
        char c;
        String str = "";
        while ((c = in.readChar()) != 0)
            str += c;
        return str;
    }

    public static void writeStrings(DataOutputStream out, String[] strings) throws IOException {
        for (String str : strings) {
            out.writeChars(str);
            out.writeChar(0);
        }
    }

    public static void writeString(DataOutputStream out, String str) throws IOException {
        out.writeChars(str);
        out.writeChar(0);
    }

    public static void writeIdxs(DataOutputStream out, int sI, int sJ, int oI, int oJ) throws IOException {
        out.writeInt(sI);
        out.writeInt(sJ);
        out.writeInt(oI);
        out.writeInt(oJ);
    }
}
