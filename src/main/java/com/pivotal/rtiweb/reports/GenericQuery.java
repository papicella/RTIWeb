package com.pivotal.rtiweb.reports;

import com.pivotal.rtiweb.main.RTIWebException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;

public class GenericQuery extends QueryBase 
{
	  protected static Logger logger = Logger.getLogger("controller");
	
	  @Override
	  public Result invoke(Connection conn) throws RTIWebException, SQLException
	  {
	    logger.info("GenericQuery - invoke called");
	    return super.invoke(conn);
	  }
	
	  @Override
	  public String getQuery()
	  {
	    return super.getQuery();
	  }
	
	  @Override
	  public String getQueryDescription()
	  {
	    return super.getQueryDescription();
	  }
}
