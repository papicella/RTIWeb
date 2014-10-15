package com.pivotal.rtiweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController
{
    protected static Logger logger = Logger.getLogger("controller");

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String login(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception
    {
        logger.debug("Received request to show home page");

        session.setAttribute("url", "jdbc:postgresql://mdw:5432/rtiweb");
        
        // This will resolve to /WEB-INF/jsp/main.jsp
        return "main";
    }
}
