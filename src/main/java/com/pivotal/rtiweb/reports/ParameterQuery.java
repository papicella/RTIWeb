package com.pivotal.rtiweb.reports;

import com.pivotal.rtiweb.main.RTIWebException;
import org.apache.log4j.Logger;

import com.pivotal.rtiweb.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;


public class ParameterQuery extends QueryBase 
{
	protected static Logger logger = Logger.getLogger("controller");

	  private Map paramMap;
	  private Map paramValues;
	  
	  @Override
	  public Result invoke(Connection conn) throws RTIWebException, SQLException
	  {
		logger.info("ParamaterQuery - invoke called");
	    Result res = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset  = null;

	    try
	    {
	      pstmt = conn.prepareStatement(getQuery());

	      // TODO: read params, create PreparedStatement, set variables, runquery
	      @SuppressWarnings("unchecked")
		  Set<String> keys = getParamMap().keySet();
	      int i = 0;
	      for (String param: keys)
	      {
	    	i++;
	        pstmt.setString(Integer.parseInt(param), (String) paramValues.get(param));
	      }

	      rset = pstmt.executeQuery();
	      res = ResultSupport.toResult(rset);
	    }
	    finally
	    {
	      // close all resources
	      JDBCUtil.close(rset);
	      JDBCUtil.close(pstmt);
	    }
	    
	    return res;
	  }

	  public void setParamMap(Map paramMap)
	  {
	    this.paramMap = paramMap;
	  }

	  public Map getParamMap()
	  {
	    return paramMap;
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

	  public void setParamValues(Map paramValues)
	  {
	    this.paramValues = paramValues;
	  }

	  public Map getParamValues()
	  {
	    return paramValues;
	  }
}
