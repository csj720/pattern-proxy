package com.csj.pattern.custom;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CSJProxy {
    public static final String ln = "\r\n";

    public static Object newProxyInstance(CSJClassLoader classLoader, Class<?>[] interfaces, CSJInvocationHandler h) {
        try {
            //1.动态的生产源代码.java文件
            String src = gernrateSrc(interfaces);

            //2.Java文件输出磁盘
            String filePath = CSJProxy.class.getResource("").getPath();
            File file = new File(filePath + "$Proxy0.java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(src);
            fileWriter.flush();
            fileWriter.close();

            //3.把生成的.java问编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

            //4.编译生成的.class问加载到JVM中
            Class<?> proxyClass = classLoader.findClass("$Proxy0");
            Constructor<?> constructor = proxyClass.getConstructor(CSJInvocationHandler.class);
            file.delete();

            //5.返回字节码重组以后的新的代理对象
            return constructor.newInstance(h);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String gernrateSrc(Class<?>[] interfaces) {

        StringBuffer sb = new StringBuffer();
        sb.append("package com.csj.pattern.custom;" + ln);
        sb.append("import com.csj.pattern.Person;" + ln);
        sb.append("import java.lang.reflect.Method;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
        sb.append("CSJInvocationHandler h;" + ln);
        sb.append("public $Proxy0(CSJInvocationHandler h) {" + ln);
        sb.append("this.h = h;" + ln);
        sb.append("}" + ln);

        for (Method method : interfaces[0].getMethods()) {
            sb.append("public " + method.getReturnType().getName() + " " + method.getName() + "() {" + ln);
            sb.append("try{" + ln);
            sb.append("Method method = " + interfaces[0].getName() + ".class.getMethod(\"" + method.getName() + "\", new Class[] {});" + ln);
            sb.append("this.h.invoke(this, method, null);" + ln);
            sb.append("} catch (Throwable e) {" + ln);
            sb.append("e.printStackTrace();" + ln);
            sb.append("}" + ln);
            sb.append("}" + ln);
        }


        sb.append("}" + ln);
        return sb.toString();
    }
}
