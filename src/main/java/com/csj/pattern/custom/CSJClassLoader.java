package com.csj.pattern.custom;

import java.io.*;

public class CSJClassLoader extends ClassLoader{
    private File classPaathFile;

    public CSJClassLoader(){
        String classPath = CSJClassLoader.class.getResource("").getPath();
        this.classPaathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = CSJClassLoader.class.getPackage().getName() + "." + name;

        if (classPaathFile != null ){
            File classFile = new File(classPaathFile, name.replaceAll("\\.", "/") + ".class");
            if (classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte [] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1){
                        out.write(buff, 0, len);
                    }
                    return defineClass(className, out.toByteArray(), 0, out.size());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(null != in){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(out != null){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


        return super.findClass(name);
    }
}
