package com.tutorial_4.tpo_tutor_4;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/adding")
public class Controller extends HttpServlet {

    private static final String INTEGER_PATTERN = "^-?[0-9]+$";
    private static final Pattern INTEGER_REGEX = Pattern.compile(INTEGER_PATTERN);
    private static final String FIRST = "first";
    private static final String SECOND = "second";
    private final Logic _logic ;

    public Controller(){
        _logic = new Logic();
    }

    private static BigInteger getValue(String input){
        Matcher matcher = INTEGER_REGEX.matcher(input);
        if(!matcher.matches()){
            return null;
        }
        return new BigInteger(input);
    }
    @Override
    protected  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BigInteger firstNum = getValue(request.getParameter(FIRST));
        BigInteger secondNum = getValue(request.getParameter(SECOND));

        LogicCall call = new LogicCall(firstNum,secondNum);
        Model model  = _logic.add(call);

        request.setAttribute("request", model);
        HttpSession session = request.getSession();
        session.setAttribute("session", model);

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/result");
        dispatcher.forward(request,response);
    }
}
