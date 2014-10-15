package com.pivotal.rtiweb.controller;

import com.pivotal.rtiweb.beans.InterfaceList;
import com.pivotal.rtiweb.main.ApplicationContextHolder;
import com.pivotal.rtiweb.utils.QueryUtil;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class RestController
{
    protected static Logger logger = Logger.getLogger("controller");
    private static ClassPathXmlApplicationContext applicationContext;
    private static DataSource dataSource;

    @RequestMapping(value="/testrest",
                    method = RequestMethod.GET,
                    produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String testRest()
    {
        return "hello world";
    }

    @RequestMapping(value="/interfacecount",
            method = RequestMethod.GET,
            produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody InterfaceList interfaceCount() throws SQLException
    {
        applicationContext = ApplicationContextHolder.getInstance();
        dataSource = (DataSource) applicationContext.getBean("dataSource");

        Connection conn = null;
        InterfaceList interfaceList = null;

        try
        {
            conn =  dataSource.getConnection();
            interfaceList = QueryUtil.interfaceCount(conn);
        }
        finally
        {
            if (conn != null)
            {
                conn.close();
            }
        }

        return interfaceList;

    }

}
