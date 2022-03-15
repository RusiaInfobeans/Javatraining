package TweeterProject.controller;

import TweeterProject.Dao.FollowerDao;
import TweeterProject.Dao.TweeterDao;
import TweeterProject.Dao.UserDao;
import TweeterProject.Follower;
import TweeterProject.Tweet;
import TweeterProject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class Controller {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TweeterDao tweeterDao;
    @Autowired
    private FollowerDao followerDao;

    private boolean containsInvalidChars(String name) {
        name = name.replaceAll("\\s", "");
        return ((name != null) && (!name.equals("")) && (name.matches("^[a-zA-Z]*$")));
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) return false;
        return pat.matcher(email).matches();
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerUser(@RequestBody MultiValueMap<String, String> requestBodyMap) {
        String email = requestBodyMap.get("email").get(0);
        String name = requestBodyMap.get("name").get(0);
        String password = requestBodyMap.get("password").get(0);
        ModelAndView modelAndView = new ModelAndView("Logintweeter");
        List<User> userList=userDao.findAll();
        User userByEmail = userDao.findByEmail(email);
        if (userByEmail != null) {
            boolean validEmail = isValidEmail(email);
            boolean b = containsInvalidChars(name);
            if (validEmail && b && password.length() > 3) {
                User user = new User(name, email, password);
                userDao.saveUser(user);
                return modelAndView;
            }
        }
        return errorMessageModelAndView("Invalid data");
    }

    @GetMapping("/fetchUser")
    ModelAndView fetchUser(@RequestParam String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            ModelAndView modelAndView = new ModelAndView("users");
            modelAndView.getModel().put("userEmail", user.getEmail());
            modelAndView.getModel().put("passwordUser", user.getPassword());
            modelAndView.getModel().put("users", userDao.findAll().stream().filter(user_ -> user_.getId() != user.getId()).collect(Collectors.toList()));
            return modelAndView;
        }
        return errorMessageModelAndView("Data not matched");
    }

    @GetMapping("/tweets")
    ModelAndView getTweets(@RequestParam String email) {
        if (email != "") {
            User user = userDao.findByEmail(email);
            System.out.println();
            if (user != null && email.equals(user.getEmail())) {
                List<Tweet> tweets = tweeterDao.findAllTweets(user.getId());
                ModelAndView modelAndView = new ModelAndView("tweets");
                modelAndView.getModel().put("tweets", tweets);
                modelAndView.getModel().put("name", email);
                return modelAndView;
            }
        }
        return errorMessageModelAndView("error");
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView login(@RequestBody MultiValueMap<String, String> map) {
        String email = map.get("email").get(0);
        String password = map.get("password").get(0);
        if (isValidEmail(email)) {
            User user = userDao.findByEmail(email);
            if (user != null && email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                ModelAndView modelAndView = new ModelAndView("profile");
                modelAndView.getModel().put("name", user.getName());
                modelAndView.getModel().put("email", user.getEmail());
                return modelAndView;
            }
        }
        return errorMessageModelAndView("no Data Match");
    }

    private ModelAndView errorMessageModelAndView(String message) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.getModel().put("error", message);
        return modelAndView;
    }


    @GetMapping("/loginForm")
    public ModelAndView loginForm() {
        ModelAndView modelAndView = new ModelAndView("Logintweeter");
        return modelAndView;
    }

    @GetMapping("/registerUser")
    public ModelAndView registerUser() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

/*

    @PutMapping("/update")
    private ResponseEntity<String> updateRecord(@RequestBody Map<String, String> requestBodyMap) {
        String email = requestBodyMap.get("email");
        String name = requestBodyMap.get("name");
        String password = requestBodyMap.get("password");
        if (!containsInvalidChars(name)) {
            return new ResponseEntity<>("name contains invalid characters",
                    HttpStatus.BAD_REQUEST);
        } else if (userProfile.containsKey(email) && userProfile.get(email).getPassword().equals(password)) {
            User user = userProfile.get(email);
            String currName = user.getName();
            if (currName.equals(name)) {
                return new ResponseEntity<>("No change rquired",HttpStatus.OK);
            } else {
                user.setName(name);
                userProfile.put(email, user);
                return new ResponseEntity<>("update successful",
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User doesn't exist",
                HttpStatus.NOT_FOUND);

    }
*/


    @PostMapping(value = "/tweet", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView postTweet(@RequestBody MultiValueMap<String, String> tweetBody) {
        String email = tweetBody.get("email").get(0);
        //String password = tweetBody.get("password").get(0);
        String tweet = tweetBody.get("tweet").get(0);
        if (email != ""  && tweet != "") {
            User user = userDao.findByEmail(email);
            if (email.equals(user.getEmail()) ) {
                Tweet tweet1 = new Tweet(tweet, Timestamp.from(Instant.now()), user);
                ModelAndView modelAndView = new ModelAndView("success");
                tweeterDao.saveTweet(tweet1);
                return modelAndView;
            }
        }
        return errorMessageModelAndView("error at  post tweet");
    }

    @GetMapping("/follow")
    public ModelAndView follow(@RequestParam MultiValueMap<String, String> requestBodyMap) {
        String userEmail = requestBodyMap.get("userEmail").get(0);
        String password = requestBodyMap.get("password").get(0);
        String followerEmail = requestBodyMap.get("followerEmail").get(0);
        if (!"".equals(userEmail) && !"".equals(password) && !"".equals(followerEmail)) {
            User user = userDao.findByEmail(userEmail);
            if (userEmail != followerEmail && password.equals(user.getPassword()) && userEmail.equals(user.getEmail())) {
                User followerUser = userDao.findByEmail(followerEmail);
                List<Follower> followersOfUser = followerDao.findAll(user.getId()).stream().filter(follower -> follower.getFollowerUserId() == followerUser.getId()).collect(Collectors.toList());
                if (followersOfUser.size() == 0) {
                    Follower follower = new Follower(followerUser.getId(), user);
                    followerDao.saveFollower(follower);
                    ModelAndView modelAndView = new ModelAndView("profile");
                    modelAndView.getModel().put("email", userEmail);
                    modelAndView.getModel().put("name", user.getName());
                    return modelAndView;
                }
                return errorMessageModelAndView("you are already follower");
            }
        }
        return errorMessageModelAndView("Error");
    }


    //    User can delete account  -->DELETE
/*
    @DeleteMapping("/delete")
    String deleteRecord(@RequestParam String email) {
        userProfile.remove(email);
        return email + " successfully deleted";
    }

*/


}
