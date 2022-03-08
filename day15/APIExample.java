package  day15;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class APIExample {
    private Map<String, User> userProfileMap = new HashMap<>();
    private Map<String, List<Tweet>> tweetsMap = new HashMap<>();
    private Map<String, List<String>> followmap = new HashMap<>();

    @PostMapping("tweet/createuser")
    private ResponseEntity<String> createUser(@RequestBody Map<String,String> requestbody){
        ResponseEntity<String> responseEntity=null;
        String name=requestbody.get("name");
        String email=requestbody.get("email");
        String password=requestbody.get("password");
        if (containsInvalidChar(name))
            responseEntity=new ResponseEntity<>("name contains invalid character",HttpStatus.BAD_REQUEST);
        else if (containsInvalidEmail(email)){
            responseEntity=new ResponseEntity<>("Email contains invalid character or alerady present",HttpStatus.BAD_REQUEST);
        }else if(containsInvalidPassword(password)){

        }else {
            User user =new User(name,email,password);
            userProfileMap.put(email,user);
            System.out.println(userProfileMap);
            responseEntity=new ResponseEntity<>("user created successfully"+ userProfileMap,HttpStatus.OK);
        }
        return responseEntity;
    }

    @PostMapping("tweet/posttweet")
    private ResponseEntity<String> postTweet(@RequestBody Tweet tweet){
        ResponseEntity<String> responseEntity =null;
        String name=tweet.getName();
        String email=tweet.getEmail();
        String t = tweet.getTweet();
        if (containsInvalidChar(name)){
            responseEntity=new ResponseEntity<>("name contains invalid character",HttpStatus.BAD_REQUEST);
        }else if (!isContainsEmail(email)){
            responseEntity=new ResponseEntity<>("Email contains invalid character or not present",HttpStatus.BAD_REQUEST);
        }else {
            if (tweetsMap.containsKey(email)){
                tweetsMap.get(email).add(tweet);
                System.out.println("tweet map 2nd time:"+tweetsMap);
                responseEntity=new ResponseEntity<>("Tweet added successfully",HttpStatus.OK);
            }else{
                List<Tweet> tweetList=new ArrayList<>();
                tweetList.add(tweet);
                tweetsMap.put(email, tweetList);
                System.out.println("tweet map 1st time :"+tweetsMap);
                responseEntity=new ResponseEntity<>("Tweet added successfully",HttpStatus.OK);
            }
        }
        return responseEntity;
    }

    @GetMapping("tweet/alltweets")
    private ResponseEntity<String> allTweets(@RequestParam String email,@RequestParam String password){
        ResponseEntity<String> responseEntity =null;
        if (!isContainsEmail(email)){
            responseEntity=new ResponseEntity<>("Email not present",HttpStatus.BAD_REQUEST);
        }else if(containsInvalidPassword(password)){
            responseEntity=new ResponseEntity<>("Password contains invalid character",HttpStatus.BAD_REQUEST);
        }else{
            if (isPasswordMatch(email,password)){
                System.out.println("Tweets are :"+tweetsMap.get(email));
                responseEntity=new ResponseEntity<>("Tweets are :\n"+tweetsMap.get(email)+"\n",HttpStatus.OK);
            }else {
                responseEntity=new ResponseEntity<>("Pasword not match",HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return responseEntity;
    }

    @PostMapping("tweet/followfriend")
    private ResponseEntity<String> followFriend(@RequestBody Map<String,String> requestbody){
        String email=requestbody.get("email");
        String password=requestbody.get("password");
        String friendEmail=requestbody.get("friendemail");
        ResponseEntity<String> responseEntity=null;
        if (!isContainsEmail(email) || !isContainsEmail(friendEmail)){
            responseEntity=new ResponseEntity<>("Email not contain",HttpStatus.BAD_REQUEST);
        }else if (!isPasswordMatch(email,password)){
            responseEntity=new ResponseEntity<>("Password incorrect",HttpStatus.NOT_ACCEPTABLE);
        }else if(email.equals(friendEmail)){
            responseEntity=new ResponseEntity<>("Same mail can't added",HttpStatus.NOT_ACCEPTABLE);
        }else{
            if(followmap.containsKey(email)){
                User user=userProfileMap.get(friendEmail);
                followmap.get(email).add(user.getEmail());
                System.out.println(followmap);
                responseEntity=new ResponseEntity<>("Friend Added"+followmap.get(email),HttpStatus.OK);
            }else{
                List<String> friendlist=new ArrayList<>();
                friendlist.add(userProfileMap.get(friendEmail).getEmail());
                followmap.put(email,friendlist);
                System.out.println(followmap);
                responseEntity=new ResponseEntity<>("Friend Added"+followmap.get(email),HttpStatus.OK);
            }
        }
        return responseEntity;
    }

    private boolean containsInvalidPassword(String password) {
        if (password==null)
            return true;
        return false;
    }

    private boolean containsInvalidChar(String name) {
        if (name == null) {
            return true;
        }else if(name.length()>3){
            return false;
        }
        return true;
    }

    private boolean containsInvalidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(email);
        if(email==null){
            return true;
        }else if(m.matches()){
            if (isContainsEmail(email)){
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

    private boolean isPasswordMatch(String email,String password) {
        User user = userProfileMap.get(email);
        if (user.getPassword().equals(password)) {
            return true;
        }else{
            return false;
        }
    }

    private Boolean isContainsEmail(String email) {
        if (userProfileMap.containsKey(email))
            return true;
        return false;
    }

}