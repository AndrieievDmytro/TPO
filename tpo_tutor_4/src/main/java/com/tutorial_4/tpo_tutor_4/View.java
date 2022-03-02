package com.tutorial_4.tpo_tutor_4;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/result")
public class View extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        Model attributeModel = (Model)request.getAttribute("request");
        HttpSession session = request.getSession();
        Model sessionModel = (Model)session.getAttribute("session");

        if (attributeModel == null || sessionModel == null){
            String message = String.format("%s%s", request.getContextPath(),request.getServletPath());
            response.sendError(400, message);
            return;
        }

        PrintWriter writer = response.getWriter();
        writer.println("Attribute Result "+ attributeModel.getResult());
        writer.println("Session Result "+ sessionModel.getResult());
        writer.close();
    }
}
