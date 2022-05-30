<%--
  Created by IntelliJ IDEA.
  User: chuon
  Date: 5/30/2022
  Time: 2:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${ds}" var="cla">
    <h1>${cla.id},${cla.name}</h1>
</c:forEach>
</body>
</html>
