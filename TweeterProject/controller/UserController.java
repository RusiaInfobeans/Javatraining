package TweeterProject.controller;


import TweeterProject.Dao.UserDao;
import TweeterProject.Exception.CommonException;
import TweeterProject.User;
import TweeterProject.Utilis.Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;
    @Value("${follower.allowed}")
    boolean allowed;
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerUser(@RequestBody MultiValueMap<String, String> requestBodyMap) {
        String email = requestBodyMap.get("email").get(0);
        String name = requestBodyMap.get("name").get(0);
        String password = requestBodyMap.get("password").get(0);
        ModelAndView modelAndView = new ModelAndView("Logintweeter");
        User userByEmail = userDao.findByEmail(email);
        if (userByEmail.getEmail() == null) {
            boolean validEmail = Utility.isValidEmail(email);
            boolean b = Utility.containsInvalidChars(name);
            if (validEmail && b && password.length() > 3) {
                User user = new User(name, email, password);
                userDao.saveUser(user);
                return modelAndView;
            }
            throw new CommonException("invalid type of email or password ", HttpStatus.BAD_REQUEST);

        }
        throw new CommonException("Already registered email ", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/fetchUser")
    ModelAndView fetchUser(@RequestParam String email) {
        User user = userDao.findByEmail(email);
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.getModel().put("users", userDao.findAll().stream().filter(user_ -> user_.getId() != user.getId()).collect(Collectors.toList()));
        modelAndView.getModel().put("userEmail", email);
        modelAndView.getModel().put("allowed",allowed);
        modelAndView.getModel().put("passwordUser", user.getPassword());
        return modelAndView;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView login(@RequestBody MultiValueMap<String, String> map) {
        String email = map.get("email").get(0);
        String password = map.get("password").get(0);
        if (Utility.isValidEmail(email)) {
            User user = userDao.findByEmail(email);
            if (user != null && email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                ModelAndView modelAndView = new ModelAndView("profile");
                modelAndView.getModel().put("name", user.getName());
                modelAndView.getModel().put("email", user.getEmail());
                modelAndView.getModel().put("password", user.getPassword());
                return modelAndView;
            }
        }
        throw new CommonException("No Data Matched", HttpStatus.BAD_REQUEST);
    }


}