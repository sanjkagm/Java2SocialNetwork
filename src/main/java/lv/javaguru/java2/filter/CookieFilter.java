package lv.javaguru.java2.filter;




import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {

    public CookieFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        UserDAOImpl userDAOObj = new UserDAOImpl();
        User userInSession = userDAOObj.getLoginedUser(session);

        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }





        // Flag check cookie
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null) {
            String userName = userDAOObj.getUserNameInCookie(req);
            try {
                User user = userDAOObj.getByUsername(userName);
                userDAOObj.storeLoginedUser(session, user);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Mark checked.
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }

        chain.doFilter(request, response);
    }

}

