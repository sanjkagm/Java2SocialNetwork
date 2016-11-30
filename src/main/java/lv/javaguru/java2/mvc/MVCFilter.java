package lv.javaguru.java2.mvc;



import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.Utils;
import lv.javaguru.java2.servlet.AddUserController;
import lv.javaguru.java2.servlet.DoAddUserController;
import lv.javaguru.java2.servlet.MessagesController;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "MVCFilter", urlPatterns = { "/addUser","/doAddUser","/messages/*" })
public class MVCFilter implements Filter {

    private Map<String, MVCController> controllers;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        controllers = new HashMap<>();
        controllers.put("/addUser", new AddUserController());
        controllers.put("/doAddUser", new DoAddUserController());
        controllers.put("/messages", new MessagesController());
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String contextURI = req.getServletPath();
        String method = req.getMethod();

        if (contextURI.contains("/css")) {
            filterChain.doFilter(request, response);
        } else {

            if (contextURI.contains("/messages")) {
                Utils utils = new Utils();
                User userInSession = utils.checkIfUserLoggedIn(req);

                if (userInSession == null) {
                    resp.sendRedirect(req.getContextPath() + "/login");
                    return;
                }
            }


            MVCController controller = controllers.get(contextURI);
            MVCModel model;
            if (method.equalsIgnoreCase("GET")){
                model = controller.processGet(req);
            }
            else {
                model = controller.processPost(req);
            }

            req.setAttribute("data", model.getData());
            req.setAttribute("messages", model.getMessages());

            ServletContext context = req.getServletContext();
            RequestDispatcher requestDispatcher = context.getRequestDispatcher(model.getJspName());
            requestDispatcher.forward(req, resp);
        }

    }

    @Override
    public void destroy() {

    }

}