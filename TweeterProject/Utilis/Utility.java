package TweeterProject.Utilis;
import TweeterProject.Dao.UserDao;
import TweeterProject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;


import java.util.regex.Pattern;

@Component
public class Utility {
    @Autowired
    static UserDao userDao;

    public static boolean containsInvalidChars(String name) {
        name = name.replaceAll("\\s", "");
        return ((name != null) && (!name.equals("")) && (name.matches("^[a-zA-Z]*$")));
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) return false;
        return pat.matcher(email).matches();
    }

    public static ModelAndView errorMessageModelAndView(String message) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.getModel().put("error", message);
        return modelAndView;
    }

    public static boolean isUserValid(MultiValueMap<String, String> map) {
        String email = map.get("email").get(0);
        String password = map.get("password").get(0);
        System.out.println(email + " email");
        System.out.println(password + " password");
        User user = userDao.findByEmail(email);
        if (user != null) return user.getPassword().equals(password);
        else return false;
    }

}