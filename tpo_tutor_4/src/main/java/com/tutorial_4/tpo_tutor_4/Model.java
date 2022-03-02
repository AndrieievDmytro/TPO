package com.tutorial_4.tpo_tutor_4;

import java.math.BigInteger;

public class Model {
    private final BigInteger _result;

    Model(BigInteger result){
        _result = result;
    }

    public BigInteger getResult(){
        return _result;
    }
}
