package SampleApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: gauravmunjal
 * Date: 18/7/12
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping
public class TodoController {

    @RequestMapping("/todo")
    public ModelAndView todo() {
        return new ModelAndView("index");
    }
}
