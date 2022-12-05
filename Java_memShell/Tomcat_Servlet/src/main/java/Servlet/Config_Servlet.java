package Servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/config")
public class Config_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        ServletConfig servletConfig = getServletConfig();
        String name = servletConfig.getServletContext().getClass().getName();
        System.out.println(name);
        PrintWriter writer = resp.getWriter();
        writer.write("Servlet名称为："+name);
        writer.close();
    }
}
