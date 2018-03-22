package project.java.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        ActionFactory actionFactory = new ActionFactory();
        Action command = actionFactory.defineCommand(request);
        Action nextStep = null;
        ServletContext servletContext = getServletContext();
        try {
            nextStep = command.execute(request, response);
        } catch (Exception e) {
            request.setAttribute(Message.ERROR, e.getMessage());
            String errorJsp = Actions.ERROR.command.getJsp();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher(errorJsp);
            dispatcher.forward(request, response);
        }
        if (nextStep == null || nextStep == command) {
            String viewJsp = command.getJsp();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher(viewJsp);
            dispatcher.forward(request, response);
        } else {
            String viewJsp = nextStep.getJsp();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher(viewJsp);
            dispatcher.forward(request, response);
            response.sendRedirect("do?command=" + nextStep);
        }
    }


}
