package com.pivotal.rtiweb.controller;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pivotal.rtiweb.main.ApplicationContextHolder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pivotal.rtiweb.reports.GenericQuery;
import com.pivotal.rtiweb.reports.ParameterQuery;
import com.pivotal.rtiweb.reports.Query;
import com.pivotal.rtiweb.reports.QueryBeanReader;
import com.pivotal.rtiweb.reports.QueryList;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.sql.DataSource;

@Controller
public class ReportsController
{
    protected static Logger logger = Logger.getLogger("controller");
    private static ClassPathXmlApplicationContext applicationContext;
    private static DataSource dataSource;

    @RequestMapping(value = "/displayqueryreports", method = RequestMethod.GET)
    public String showQueryReports
            (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception
    {


        logger.debug("Received request to show all query reports");

        QueryBeanReader reader = QueryBeanReader.getInstance();

        List<String> qlBeanNames = Arrays.asList(reader.getQueryListBeans());

        List queries = new ArrayList();
        List headerNames = new ArrayList();
        int i = 0;

        for (String beanName: qlBeanNames)
        {
            i++;
            QueryList ql = reader.getQueryListBean(beanName);
            headerNames.add(ql.getDescription());
            queries.add(ql.getQueryList());
        }

        model.addAttribute("queries", queries);
        model.addAttribute("headerNames", headerNames);

        // This will resolve to /WEB-INF/jsp/reports.jsp
        return "reports";
    }

    @RequestMapping(value = "/executequeryreport", method = RequestMethod.GET)
    public String executeQuery
            (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception
    {

        Connection conn = null;

        logger.debug("Received request to execute a query");

        javax.servlet.jsp.jstl.sql.Result queryResult = null;
        QueryBeanReader reader = QueryBeanReader.getInstance();

        applicationContext = ApplicationContextHolder.getInstance();
        dataSource = (DataSource) applicationContext.getBean("dataSource");

        long start = 0;
        long end = 0;

        String beanId = request.getParameter("beanId");
        String refreshTime = request.getParameter("refreshTime");
        if (refreshTime == null)
        {
            refreshTime = "none";
        }

        logger.debug("refresh time : " + refreshTime);

        try
        {
            conn = dataSource.getConnection();

            if (beanId != null) {

                Query qb = reader.getQueryBean(beanId);

                if (qb instanceof GenericQuery) {
                    logger.debug("Generic Query will be run");
                    GenericQuery genericQuery = (GenericQuery) qb;

                    start = System.currentTimeMillis();
                    queryResult =
                            genericQuery.invoke(conn);
                    end = System.currentTimeMillis();

                    model.addAttribute("queryResults", queryResult);
                    model.addAttribute("queryDescription", genericQuery.getQueryDescription());
                    model.addAttribute("querySQL", genericQuery.getQuery().trim());
                    model.addAttribute("beanId", beanId);
                }
                else if (qb instanceof ParameterQuery)
                {
                    logger.debug("Parameter Query will be run");
                    ParameterQuery paramQuery = (ParameterQuery) qb;
                    model.addAttribute("queryDescription", paramQuery.getQueryDescription());
                    model.addAttribute("querySQL", paramQuery.getQuery().trim());
                    model.addAttribute("beanId", beanId);
                    model.addAttribute("paramMap", paramQuery.getParamMap());

                    if (request.getParameter("pSubmit") != null) {
                        // execute pressed, get param values
                        Map paramValues = new HashMap();
                        Set<String> keys = paramQuery.getParamMap().keySet();
                        for (String param : keys) {
                            paramValues.put(param, (String) request.getParameter(param));
                        }

                        paramQuery.setParamValues(paramValues);

                        start = System.currentTimeMillis();
                        queryResult =
                                paramQuery.invoke(conn);
                        end = System.currentTimeMillis();

                        request.setAttribute("queryResults", queryResult);
                    }
                }
                else
                {
                    // do nothing here
                }
            }
        }
        finally
        {
          if (conn != null)
          {
              conn.close();
          }
        }

        model.addAttribute("refreshTime", refreshTime);
        model.addAttribute("beanId", beanId);
        model.addAttribute("querydate", new Date());

        double timeTaken = new Double(end - start).doubleValue();
        DecimalFormat df = new DecimalFormat("#.##");

        model.addAttribute("elapsedTime", df.format(timeTaken/1000));

        // This will resolve to /WEB-INF/jsp/displayquery.jsp
        return "displayquery";
    }

}
