package Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/context")
public class Context_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        ServletContext servletContext = getServletContext();

        Enumeration<String> initParamerNames = servletContext.getInitParameterNames();
        while(initParamerNames.hasMoreElements()){
            String ParamerName = initParamerNames.nextElement();
            String Paramer = servletContext.getInitParameter(ParamerName);
            writer.write(ParamerName+"的值为："+Paramer+"<br/>");
        }

        writer.close();
    }
}
