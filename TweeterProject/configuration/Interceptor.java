package TweeterProject.configuration;

import TweeterProject.Dao.UserDao;
import TweeterProject.Exception.CommonException;
import TweeterProject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {
    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("GET") && !request.getRequestURI().contains("Form")) {
            boolean b = validateUser(request.getParameter("email"), request.getParameter("password"));
            if (!b) throw new CommonException("email or password not found", HttpStatus.BAD_REQUEST);
        }
        return true;

    }

    public boolean validateUser(String email, String password) {
        User userByEmail = userDao.findByEmail(email);
        return userByEmail !=null && userByEmail.getEmail() != null && password != null && password.equals(userByEmail.getPassword());
    }
}