package com.pivotal.rtiweb.reports;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;

import com.pivotal.rtiweb.main.RTIWebException;

public interface Query
{
    public Result invoke (Connection conn) throws RTIWebException, SQLException;
    public void setQueryDescription(String queryDescription);
    public void setQuery (String query);

}