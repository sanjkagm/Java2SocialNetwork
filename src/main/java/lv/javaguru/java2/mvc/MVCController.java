package lv.javaguru.java2.mvc;

import javax.servlet.http.HttpServletRequest;

public interface MVCController {

    MVCModel processGet(HttpServletRequest req);

    MVCModel processPost(HttpServletRequest req);

}
