package com.tutorial_4.tpo_tutor_4;

import java.math.BigInteger;

public class Logic {

    public Model add(LogicCall call){
        if(!call.validate()){
            return null;
        }
        BigInteger result = call._firstNum.add(call._secondNumber);
        return new Model(result);
    }

}
