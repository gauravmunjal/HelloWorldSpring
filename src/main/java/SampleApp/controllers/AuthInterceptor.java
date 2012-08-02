package SampleApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: gauravmunjal
 * Date: 27/7/12
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private final ThreadLocal<Integer> userID;

    @Autowired
    public AuthInterceptor(ThreadLocal<Integer> userID) {
        this.userID = userID;
    }

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession(false);
        if (session!=null) {
            String username = (String) session.getAttribute("username");
            userID.set((Integer) session.getAttribute("userID"));
            if (username!=null) {
                return true;
            }
        }
        response.sendRedirect("/index");
        return false;
    }
}
