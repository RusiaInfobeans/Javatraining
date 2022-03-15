package TweeterProject.controller;

import TweeterProject.Dao.FollowerDao;
import TweeterProject.Dao.UserDao;
import TweeterProject.Exception.CommonException;
import TweeterProject.Follower;
import TweeterProject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FollowerController {
    @Autowired
    UserDao userDao;
    @Autowired
    FollowerDao followerDao;

    @PostMapping("/follow")
    public ModelAndView follow(@RequestBody MultiValueMap<String, String> requestBodyMap) {
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
                    modelAndView.getModel().put("pasword", user.getPassword());
                    modelAndView.getModel().put("name", user.getName());
                    return modelAndView;
                }
                throw new CommonException("you are already follow", HttpStatus.BAD_REQUEST);
            }
        }
        throw new CommonException("invalid Data", HttpStatus.BAD_REQUEST);
    }

}