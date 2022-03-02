package requester;

import common.Common;

public class Application {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %5$s %n");
        for (int i = 0; i < Common.REQUESTER_POOL_SIZE; i++) {
            new Thread(new DefaultRequestor()).start();
        }
    }
}