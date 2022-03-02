package com.example.MVC_tomcat.controller;

import com.example.MVC_tomcat.exception.CustomException;
import com.example.MVC_tomcat.logic.AddCall;
import com.example.MVC_tomcat.logic.AddCommand;
import com.example.MVC_tomcat.model.SumModel;
import com.example.MVC_tomcat.view.SumView;

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

import static com.example.MVC_tomcat.model.SumModel.SUM_ATTRIBUTE;

@WebServlet(AddController.URI)
public final class AddController extends HttpServlet {
    public static final String URI = "/add";
    private static final String OPERAND1 = "operand1";
    private static final String OPERAND2 = "operand2";

    private static final String INTEGER_PATTERN = "^-?[0-9]+$";
    private static final Pattern INTEGER_REGEX = Pattern.compile(INTEGER_PATTERN);

    private static BigInteger tryParse(String input) throws CustomException {
        Matcher matcher = INTEGER_REGEX.matcher(input);
        if(matcher.matches() == false) {
            throw new CustomException("invalid operands");
        }

        return new BigInteger(input);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            BigInteger operand1 = tryParse(request.getParameter(OPERAND1));
            BigInteger operand2 = tryParse(request.getParameter(OPERAND2));

            AddCall call = new AddCall(operand1, operand2);
            AddCommand command = new AddCommand(call);
            SumModel result = command.execute();

            request.setAttribute(SUM_ATTRIBUTE, result);

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(SumView.URI);

            dispatcher.forward(request, response);
        } catch (CustomException ex) {
            String message = String.format("%s", ex.getMessage());
            response.sendError(400, message);
        } catch (IllegalArgumentException ex) {
            String message = String.format("%s%s: Oops...", request.getContextPath(), request.getServletPath());
            response.sendError(500, message);
        }
    }
}
