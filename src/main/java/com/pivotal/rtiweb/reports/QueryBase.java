package com.pivotal.rtiweb.reports;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;

import com.pivotal.rtiweb.main.RTIWebException;

import com.pivotal.rtiweb.utils.QueryUtil;

public abstract class QueryBase implements Query 
{

	  private String queryDescription;
	  private String query;
	  
	  public QueryBase()
	  {
	    super();
	  }

	  public void setQueryDescription(String queryDescription)
	  {
	    this.queryDescription = queryDescription;
	  }

	  public String getQueryDescription()
	  {
	    return queryDescription;
	  }

	  public void setQuery(String query)
	  {
	    this.query = query;
	  }

	  public String getQuery()
	  {
	    return query;
	  }
	  
	  public Result invoke(Connection conn) throws RTIWebException, SQLException
	  {
	    Result res = null;
	    res= QueryUtil.runQuery(conn, query, -1);
	    return res;
	  }

}
