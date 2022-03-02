package messages.responses;

import java.io.Serializable;

public class RandomResponse implements Serializable {
    
    private int randomNum;

    public RandomResponse() {
    }
    
    public RandomResponse(int randomNum) {
        this.randomNum = randomNum;
    }

    public int getRandomNum() {
        return randomNum;
    }
}
