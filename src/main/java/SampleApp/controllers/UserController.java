package SampleApp.controllers;

import SampleApp.model.Tweet;
import SampleApp.model.User;
import SampleApp.services.FollowService;
import SampleApp.services.TweetService;
import SampleApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: gauravmunjal
 * Date: 18/7/12
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping
public class UserController {
    private final UserService userService;
    private final TweetService tweetService;
    private final FollowService followService;

    private static Logger logger = Logger.getLogger(UserController.class.toString());

    @Autowired
    public UserController(UserService userService, TweetService tweetService, FollowService followService) {
        this.userService = userService;
        this.tweetService = tweetService;
        this.followService = followService;
    }

    @RequestMapping("/")
    public ModelAndView indexPage() {
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/index" , method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session!=null) {
            if (session.getAttribute("username")!=null) {
                return new ModelAndView("redirect:/home");
            }
        }
        return new ModelAndView("index","user", new User());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected ModelAndView login(User newUser, HttpSession session) throws Exception {
        User user = userService.getUser(newUser.getUsername());
        System.out.print(newUser.getUsername());
        System.out.print(newUser.getPassword());
        if (user == null) {
            return new ModelAndView("login","message","User does not exist.");
        }
        if (!user.getPassword().equals(newUser.getPassword())) {
            return new ModelAndView("login","message","Invalid Password.");
        }
        session.setAttribute("username",user.getUsername());
        session.setAttribute("userID",user.getId());
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    protected ModelAndView onSubmit(User command, HttpSession session) throws Exception {
        User user = (User) command;
        int user_id = userService.add(user);
        followService.followOneself(user_id);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("userID",user_id);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView visitHome(HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("user", userService.getProfile());
        List<Tweet> listTweets =tweetService.getFeed();
        Integer latestTweet=tweetService.getLatestTweet((Integer)session.getAttribute("userID"));
        session.setAttribute("latestTweet",latestTweet);
        session.setAttribute("offset",10);
        mv.addObject("list", listTweets);
        return mv;
    }

    @RequestMapping(value = "/user/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("logout");
    }

    @RequestMapping(value= "/user/{vanity}", method = RequestMethod.GET)
    public ModelAndView viewProfile(@PathVariable String vanity) {
        User user = userService.getUser(vanity);
        if (user==null) {
            return new ModelAndView("notfound");
        }
        ModelAndView mv = new ModelAndView("vanity");
        logger.info(user.getUsername() + " vanity called!");
        mv.addObject("user", user);
        return mv;
    }


}
