package lv.javaguru.java2.mvc;



import lv.javaguru.java2.config.SpringAppConfig;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


//@WebFilter(filterName = "MVCFilter", urlPatterns = { /*"/addUser","/doAddUser",*/"/messages/*" })
public class MVCFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(MVCFilter.class);

    private Map<String, MVCController> controllers;

    private ApplicationContext springContext;

    private MVCController getBean(Class<?> clazz) {
        return (MVCController) springContext.getBean(clazz);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            springContext = new AnnotationConfigApplicationContext(SpringAppConfig.class);
        }
        catch (BeansException e) {
            logger.error("Error! Spring context failure!");
        }


        controllers = new HashMap<>();
        //controllers.put("/addUser", getBean(AddUserController.class));
        //controllers.put("/doAddUser", getBean(DoAddUserController.class));
        controllers.put("/messages", getBean(MessagesController.class));
        controllers.put("/messages/", getBean(MessagesController.class));
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
            if (contextURI.contains("/messages/")) {
                controller = controllers.get("/messages/");
            }
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