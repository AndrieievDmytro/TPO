package common.responses;

import java.io.Serializable;

public class EchoResponse implements Serializable{

    public final String message;

    public EchoResponse(String message) {
        this.message = message;
    }
}
