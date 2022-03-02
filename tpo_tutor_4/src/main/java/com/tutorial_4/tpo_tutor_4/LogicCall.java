package com.tutorial_4.tpo_tutor_4;

import java.math.BigInteger;

public class LogicCall {
    public final BigInteger _firstNum;
    public final BigInteger _secondNumber;

    public LogicCall(BigInteger firstNum , BigInteger secondNum){
        _firstNum = firstNum;
        _secondNumber = secondNum;
    }

    public boolean validate(){
        return _firstNum != null && _secondNumber != null;
    }
}
