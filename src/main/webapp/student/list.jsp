<%--
  Created by IntelliJ IDEA.
  User: chuon
  Date: 5/30/2022
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${st}" var="std">
    <h1>${std.name},${std.age},${std.classroom.name}</h1>
</c:forEach>
</body>
</html>
