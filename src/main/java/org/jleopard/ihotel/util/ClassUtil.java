/**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-09-28  下午2:20
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */

package org.jleopard.ihotel.util;

import org.jleopard.ihotel.core.annotation.Controller;
import org.jleopard.util.DateUtil;
import org.jleopard.util.PathUtils;
import org.jleopard.util.StringUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取类路径
     */
    public static String getClassPath() {
        String classpath = "";
        URL resource = getClassLoader().getResource("");
        if (resource != null) {
            classpath = resource.getPath();
        }
        return classpath;
    }

    /**
     * 加载类（将自动初始化）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cls;
    }

    public static Set<Class<?>> scanPackage(String packagename) {
        Set<Class<?>> scls = new HashSet<>();
        if (StringUtil.isEmpty(packagename)) {
            String packagePath = System.getProperty("user.dir") + PathUtils.SEPARATOR + "src";
            addClass(scls, packagePath, packagename);
        } else {
            URL url = getClassLoader().getResource(packagename.replace(".", "/"));

            if (url != null) {
                String protocol = url.getProtocol();
                if (protocol.equals("file")) {
                    String packagePath = url.getPath().replaceAll("%20", "");
                    addClass(scls, packagePath, packagename);
                } else if (protocol.equals("jar")) {
                    try {
                        JarURLConnection jco = (JarURLConnection) url.openConnection();
                        if (jco != null) {
                            JarFile jf = jco.getJarFile();
                            if (jf != null) {
                                Enumeration<JarEntry> jes = jf.entries();
                                while (jes.hasMoreElements()) {
                                    JarEntry je = jes.nextElement();
                                    String jen = je.getName();
                                    if (jen.endsWith(".class")) {
                                        String classname = jen.substring(0, jen.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(scls, classname);
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return scls;
    }

    private static void doAddClass(Set<Class<?>> scls, String classname) {
        Class<?> cls = loadClass(classname, false);
        /*// 有controller注解加进去
        if (cls.isAnnotationPresent(Controller.class)){
            scls.add(cls);
        }*/
        scls.add(cls);
    }

    private static void addClass(Set<Class<?>> scls, String packagePath, String packagename) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {

                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fname = file.getName();
            if (file.isFile()) {
                String classname = fname.substring(0, fname.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packagename)) {
                    classname = packagename + "." + classname;
                }
                doAddClass(scls, classname);
            } else {
                String subpackagepath = fname;
                if (StringUtil.isNotEmpty(subpackagepath)) {
                    subpackagepath = packagePath + "/" + subpackagepath;
                }
                String subpackagename = fname;
                if (StringUtil.isNotEmpty(subpackagename)) {
                    subpackagename = packagename + "." + subpackagename;
                }
                addClass(scls, subpackagepath, subpackagename);
            }
        }
    }

    public static Object changeType(Field field, String str) {
        Object result = str;
        Class<?> type = field.getType();
        if (type == Long.class || type == long.class) {
            result = Long.valueOf(str);
        } else if (type == Integer.class || type == int.class) {
            result = Integer.valueOf(str);
        } else if (type == Byte.class || type == byte.class) {
            result = Byte.valueOf(str);
        } else if (type == Double.class || type == double.class) {
            result = Double.valueOf(str);
        } else if (type == Float.class || type == float.class) {
            result = Float.valueOf(str);
        } else if (type == java.util.Date.class) {
            result = DateUtil.parseDatetime(str);
        } else if (type == java.sql.Date.class) {
            result = java.sql.Date.valueOf(str);
        } else if (type == java.sql.Timestamp.class) {
            result = java.sql.Timestamp.valueOf(str);
        }

        return result;
    }
}
