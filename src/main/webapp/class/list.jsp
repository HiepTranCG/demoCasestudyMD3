<%--
  Created by IntelliJ IDEA.
  User: Tran Hiep
  Date: 29/06/2022
  Time: 8:45 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
List Class
<c:forEach items="${ds}" var="cl">
    <h1>${cl.name}</h1>
</c:forEach>
</body>
</html>
