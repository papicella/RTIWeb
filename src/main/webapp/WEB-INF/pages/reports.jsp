<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="../css/greenplum.css" />
    <link rel="stylesheet" type="text/css" href="../css/print.css" media="print" />
    <title><fmt:message key="sqlfireweb.appname" /> Reports</title>
</head>
<body>

<h2><fmt:message key="sqlfireweb.appname" /> Reports</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<div class="notice">
    Click on query icon to execute a query
</div>

<br />

<c:set var="i" value="1" />

<!-- Display header of query categories -->
<c:forEach var="row" items="${headerNames}">
    <c:if test="${i != 1}">
        &nbsp;|&nbsp;
    </c:if>
    <a href="#${i}">${row}</a>
    <c:set var="i" value="${i + 1}" />
</c:forEach>

<p />
<c:set var="i" value="1" />

<c:if test="${!empty queries}">
    <c:forEach var="entry" items="${queries}">
        <c:set var="map" value="${entry}" />
        <a name="${i}"></a>
        <table id="table_results" class="data">
            <thead>
            <tr>
                <th> Action </th>
                <th> Description </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${map}">
                <tr class="odd">
                    <td align="center">
                        <a href="executequeryreport?beanId=${item.key}" title="Execute Query">
                            <img
                                    class="icon"
                                    src="../themes/original/img/b_sql.png"
                                    width="16"
                                    height="16"
                                    alt="Run Report" />
                        </a>
                    </td>
                    <td>${item.value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br />
        <c:set var="i" value="${i + 1}" />
    </c:forEach>
    <p />
</c:if>

<br />
<jsp:include page="footer.jsp" flush="true" />

</body>
</html>
