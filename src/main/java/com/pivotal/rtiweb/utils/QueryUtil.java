package com.pivotal.rtiweb.utils;

import com.pivotal.rtiweb.beans.Interface;
import com.pivotal.rtiweb.beans.InterfaceList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

public class QueryUtil
{
    static public Result runQuery (Connection conn, String query, int maxrows) throws SQLException
    {
        Statement stmt  = null;
        ResultSet rset  = null;
        Result    res   = null;

        try
        {
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);

	      /*
	       * Convert the ResultSet to a
	       * Result object that can be used with JSTL tags
	       */
            if (maxrows == -1)
            {
                res = ResultSupport.toResult(rset);
            }
            else
            {
                res = ResultSupport.toResult(rset, maxrows);
            }
        }
        finally
        {
            JDBCUtil.close(stmt);
            JDBCUtil.close(rset);
        }

        return res;
    }

    static public InterfaceList interfaceCount (Connection conn) throws SQLException
    {
        List<Interface> interfaces = new ArrayList<Interface>();
        Statement stmt  = null;
        ResultSet rset  = null;
        try
        {
            stmt = conn.createStatement();
            rset = stmt.executeQuery(Constants.interfaceCount);

            while (rset.next())
            {
                Interface newInterface = new Interface();
                newInterface.setInterfaceName(rset.getString(1));
                newInterface.setCount(rset.getString(2));

                interfaces.add(newInterface);
            }

        }
        finally
        {
            JDBCUtil.close(stmt);
            JDBCUtil.close(rset);
        }

        return new InterfaceList(interfaces);
    }
}
