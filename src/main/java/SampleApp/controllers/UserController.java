package SampleApp.controllers;

import SampleApp.model.User;
import SampleApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    private static Logger logger = Logger.getLogger(UserController.class.toString());

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public ModelAndView todo() {
        logger.info("Index page displayed!");
        return new ModelAndView("index","user", new User());
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    protected ModelAndView onSubmit(User command) throws Exception {
        User user = (User) command;
        logger.info("New user with the username " + user.getName() + " received.");
        userService.add(user);
        return new ModelAndView("home","user",user);
    }

    @RequestMapping(value= "/user/{vanity}", method = RequestMethod.GET)
    public ModelAndView viewProfile(@PathVariable String vanity) {
        ModelAndView mv = new ModelAndView("vanity");
        logger.info(vanity + "vanity called!");
        mv.addObject("name", vanity);
        return mv;
    }
}
