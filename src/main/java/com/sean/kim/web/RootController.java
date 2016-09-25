package com.sean.kim.web;

import javafx.concurrent.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by seankim on 2016-09-24.
 */
@Controller
public class RootController {

    @Autowired
    private ScheduledService scheduledService;

    public String term, course, section;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView handleRootGetRequest(HttpServletRequest request) throws Exception {
        term = request.getParameter("term");
        course = request.getParameter("course");
        section = request.getParameter("section");

        ModelAndView mav = new ModelAndView();
        if(term != null)
        {
            mav.setViewName("redirect:/getschedule");
        }
        else
        {
            mav.setViewName( "index" );
        }

        return mav;
    }

    @RequestMapping(value = "/getschedule.html", method = RequestMethod.GET)
    public ModelAndView handleScheduleGetRequest(HttpServletRequest request) throws Exception{

        ModelAndView mav = new ModelAndView();

        String apiKey = "c533f0b22b5d58a3a15602d5fe91e2e2";


    }
}
