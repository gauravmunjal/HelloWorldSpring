package SampleApp.controllers;

import SampleApp.model.Tweet;
import SampleApp.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Hashtable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gauravmunjal
 * Date: 30/7/12
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @RequestMapping("/tweet/create.json" )
    @ResponseBody
    public Tweet create(Tweet tweet, HttpSession session) {
        int id = tweetService.add(tweet, (String)session.getAttribute("username"));
        return tweetService.getTweet(id);
    }

    @RequestMapping("/tweet/updates.json" )
    @ResponseBody
    public Hashtable updates( HttpSession Session) {
        int latestTweet= (Integer) Session.getAttribute("latestTweet");
        Hashtable h= new Hashtable();
        int update=tweetService.getLatestTweet((Integer)Session.getAttribute("userID"));
        if(update>latestTweet){
            h.put("value", "yes");
            h.put("number",update-latestTweet);
            Session.setAttribute("currLatestTweet",update);
        }
        else  h.put("value","no");
        return h;
    }

    @RequestMapping("/tweet/getNewTweet.json" )
    @ResponseBody
    public Hashtable getUpdates( HttpSession Session) {
        Hashtable h= new Hashtable();
        Integer latestTweet= (Integer) Session.getAttribute("latestTweet");
        Integer currLatestTweet=(Integer) Session.getAttribute("currLatestTweet");
        List<Tweet> listUpdates=(List<Tweet>)tweetService.getNewTweets(latestTweet,currLatestTweet, (Integer)Session.getAttribute("userID"));
        h.put("val", listUpdates.size());
        System.out.println("List size" + listUpdates.size());
        h.put("List",listUpdates);
        Session.setAttribute("latestTweet",currLatestTweet);

        return h;

    }

}
