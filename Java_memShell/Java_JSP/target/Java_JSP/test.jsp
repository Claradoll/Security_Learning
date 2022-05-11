<%@ page import="java.lang.reflect.Field" %><%--
  Created by IntelliJ IDEA.
  User: 34946
  Date: 2022/5/4
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Field reqF = request.getClass().getDeclaredField("request");
    reqF.setAccessible(true);
    Request req = (Request) reqF.get(request);
    StandardContext context = (StandardContext) req.getContext();
    out.println(context);
%>
</body>
</html>
