<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <c:if test="${!empty refreshTime}">
       <c:if test="${refreshTime != 'none'}">
           <meta http-equiv="refresh" content="${refreshTime}">
       </c:if>
    </c:if>
    <link rel="stylesheet" type="text/css" href="../css/greenplum.css" />
    <link rel="stylesheet" type="text/css" href="../css/print.css" media="print" />
    <title><fmt:message key="sqlfireweb.appname" /> Query Result</title>
</head>
<body>
<h2>${queryDescription}</h2>

<jsp:include page="toolbar.jsp" flush="true" />

<c:if test="${!empty paramMap}">
    <c:if test="${empty queryResults}">
        <div class="warning">
            Parameters must be set prior to executing this query
        </div>
    </c:if>
    <form method="get" action="executequeryreport">
        <input type="hidden" name="beanId" value="${param.beanId}" />
        <input type="hidden" name="refreshTime" value="${refreshTime}" />
        <fieldset>
            <legend>Query Parameter Form</legend>
            <table class="formlayout">
                <c:forEach var="item" items="${paramMap}">
                    <tr>
                        <td align="right">${item.value}</td>
                        <td>
                            <input type="text" name="${item.key}" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </fieldset>
        <fieldset class="tblFooters">
            <input type="submit" value="Execute" name="pSubmit" />
            <input type="reset" value="Reset" />
        </fieldset>
    </form>
</c:if>

<c:choose>
    <c:when test="${!empty queryResults}">
        <p />
        <div class="success">
            Query completed successfully at ${querydate}
        </div>
        <br />
        <c:if test="${!empty elapsedTime}">
            <div class="info">
                Elapsed Time ${elapsedTime} seconds
            </div>
            <br />
        </c:if>

        <!-- Add Refresh here -->
        <form method="get" action="executequeryreport">
            <input type="hidden" name="beanId" value="${param.beanId}" />
            <input type="hidden" name="paramMap" value="${paramMap}" />
            Refresh :
            <select name="refreshTime">
                <c:choose>
                    <c:when test="${refreshTime == 'none'}">
                        <option value="none" selected="selected">none</option>
                    </c:when>
                    <c:otherwise>
                        <option value="none">none</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${refreshTime == '10'}">
                        <option value="10" selected="selected">10 Seconds</option>
                    </c:when>
                    <c:otherwise>
                        <option value="10">10 Seconds</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${refreshTime == '20'}">
                        <option value="20" selected="selected">20 Seconds</option>
                    </c:when>
                    <c:otherwise>
                        <option value="20">20 Seconds</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${refreshTime == '30'}">
                        <option value="30" selected="selected">30 Seconds</option>
                    </c:when>
                    <c:otherwise>
                        <option value="30">30 Seconds</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${refreshTime == '60'}">
                        <option value="60" selected="selected">60 Seconds</option>
                    </c:when>
                    <c:otherwise>
                        <option value="60">60 Seconds</option>
                    </c:otherwise>
                </c:choose>
            </select>
            &nbsp;
            <input type="submit" value="Auto Refresh" name="pSubmit" />
        </form>
        <br />
        <br />

        <table id="table_results" class="data">
            <thead>
            <tr>
                <c:forEach var="columnName" items="${queryResults.columnNames}">
                    <th><c:out value="${columnName}"/></th>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" varStatus="loop" items="${queryResults.rows}">
                <tr class="${((loop.index % 2) == 0) ? 'even' : 'odd'}">
                    <c:forEach var="columnName" items="${queryResults.columnNames}">
                        <td><c:out value="${row[columnName]}"/></td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
</c:choose>

<br />
<jsp:include page="footer.jsp" flush="true" />

</body>
</html>
