package common;

public class Common {
    public final static int RMI_PORT = 10991;
    public final static String ECHO_PATH = "echo";
    public final static String ADD_PATH = "add";
    public static final String ECHO_URI = uri(RMI_PORT, ECHO_PATH);
    public static final String ADD_URI = uri(RMI_PORT, ADD_PATH);

    private static String uri(int port, String path)
    {
        return String.format("rmi://localhost:%d/%s", port, path);
    }
}
