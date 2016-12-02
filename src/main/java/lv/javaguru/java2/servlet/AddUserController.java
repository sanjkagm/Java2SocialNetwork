package lv.javaguru.java2.servlet;

import lv.javaguru.java2.mvc.MVCController;
import lv.javaguru.java2.mvc.MVCModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * Created by Pavel on 06.11.2016..
 */
//@WebServlet(urlPatterns = { "/addUser" })
public class AddUserController /*extends HttpServlet*/ implements MVCController {
    private static final long serialVersionUID = 1L;

    /*public AddUserServlet() {
        super();
    }*/

    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/addUserView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }*/


    public MVCModel processGet(HttpServletRequest req) {

        String view;
        Object data = null;
        String[] messages = new String[2];


        try {
            view = "/addUserView.jsp";
        } catch (Exception exception) {
            view = "/error.jsp";
            data = null;
            messages[0] = "Error";
        }

        return new MVCModel(view,data,messages);
    }


    public MVCModel processPost(HttpServletRequest req) {
        return processGet(req);
    }

}
