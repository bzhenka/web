<%--
  Created by IntelliJ IDEA.
  User: bozhenka
  Date: 07.11.2023
  Time: 01:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="results" scope="session" class="beans.ResultsList"/>
<core:forEach var="result" items="${results.results}">
    <tr>
        <td>${result.x}</td>
        <td>${result.y}</td>
        <td>${result.r}</td>
        <td>${result.hit? "Hit" : "Didnt hit"}</td>
        <td>${result.time}</td>
        <td>${result.duration}</td>
    </tr>
</core:forEach>