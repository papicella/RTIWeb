<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="sqlfireweb.appname" /> - Welcome Page</title>
<link rel="stylesheet" type="text/css" href="css/greenplum.css" />
<link rel="stylesheet" type="text/css" href="css/print.css" media="print" />
</head>
<body>

<h2>RTI*Web - Welcome Page</h2>

<a href="RTIWEB/home" target="_top" title="Home">
  <img class="icon" src="./themes/original/img/b_home.png" width="16" height="16" alt="Home" />
  Home Page
</a>
<p />

<div class="success">
Connected to Greenplum using JDBC URL <b>${sessionScope.url}</b>
</div>

<br />
<center>

</center>

<p />

<table>
    <tr>
        <td>
            <img src="./themes/original/img/greenplum.png" alt="greenplum"/>
        </td>
        <td align="justify">
            Pivotal Greenplum Database (Pivotal GPDB) manages, stores and analyzes terabytes to petabytes of data in
            large-scale analytic data warehouses around the world. Using the purpose-built Pivotal GPDB platform,
            your organization can experience 10x, 100x or even 1000x better performance over traditional RDBMS
            products. Pivotal GPDB extracts the data you need to determine which modern applications will best
            serve customers in context, at the right location, and during the right activities using a shared-nothing,
            massively parallel processing (MPP) architecture and flexible column- and row-oriented storage. It also
            leverages fully parallel communication with external databases and Hadoop to continually harness data.
        </td>
    </tr>
</table>

<br />

<fieldset>
    <legend>Getting Started</legend>
    <table class="formlayout">
        <tr>
            <td>
                <a href="RTIWEB/displayqueryreports">
                    <img class="icon" src="./themes/original/img/b_props.png" width="16" height="16" alt="Greenplum Reports" />
                    Greenplum Reports
                </a>
            </td>
        </tr>
    </table>
</fieldset>
<p />

<jsp:include page="footer.html" flush="true" />

</body>
</html>