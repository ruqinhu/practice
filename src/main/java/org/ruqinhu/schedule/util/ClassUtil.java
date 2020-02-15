package org.ruqinhu.schedule.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClassUtil {

    public static List<Class<?>> getClasses(String pack) {
        String packageName = pack;
        String packageDirName = packageName.replace(".", "/");

        List<Class<?>> retClass = new ArrayList<>();
        Enumeration<URL> dirs;
        try{
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while(dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                if ("file".equals(url.getProtocol())) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    File dir = new File(filePath);
                    if (!dir.exists() || !dir.isDirectory()) {
                        return null;
                    }
                    //获取包下所有文件，包括目录
                    File[] dirFiles = dir.listFiles(new FileFilter() {
                        @Override
                        public boolean accept(File file) {
                            return file.isDirectory() || file.getName().endsWith(".class");
                        }
                    });
                    for (File file:dirFiles) {
                        if (file.isDirectory()) {
                            getClasses(file.getAbsolutePath());
                        }else {
                            String className = file.getName().substring(0, file.getName().length() - 6);
                            retClass.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                        }
                    }
                }else if ("jar".equals(url.getProtocol())) {

                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //过滤掉类名中存在$的类（因为文件中有内部类，所以类名会有$)
        retClass = retClass.stream().filter(aClass -> !aClass.getName().contains("$")).collect(Collectors.toList());
        return retClass;
    }

}
