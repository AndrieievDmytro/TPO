package messages.requests;

import java.io.Serializable;

public class DefaultRequest implements Serializable {
    private int senderId;

    public DefaultRequest() {
    }

    public DefaultRequest(int senderId) {
        this.senderId = senderId;
    }

    public int getSenderId() {
        return senderId;
    }
}
