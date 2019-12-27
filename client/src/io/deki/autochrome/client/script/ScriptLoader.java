package io.deki.autochrome.client.script;

import io.deki.autochrome.api.script.Script;
import io.deki.autochrome.api.script.ScriptMeta;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Endre on 27.12.2019
 * @project AutoChrome
 **/
public class ScriptLoader {

    public List<ScriptEntry> loadAll() {
        List<ScriptEntry> scripts = new ArrayList<>();
        String path = new File(".").getAbsolutePath();
        List<URL> urls = getResourceFiles(path.substring(0, path.length() - 1));
        for (URL i : urls) {
            scripts.addAll(load(i.getPath().replace("file:", "")
                    .replace("!/", "")
                    .replace("/", "")));
        }
        return scripts;
    }

    private List<ScriptEntry> load(String x) {
        List<ScriptEntry> scripts = new ArrayList<>();
        try {
            JarFile jarFile = new JarFile(x);
            Enumeration<JarEntry> e = jarFile.entries();

            URL[] urls = {new URL("jar:file:" + x + "!/")};
            URLClassLoader loader = URLClassLoader.newInstance(urls);
            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if (je.isDirectory() || !je.getName().endsWith(".class")) {
                    continue;
                }
                String className = je.getName().substring(0, je.getName().length() - 6);
                className = className.replace('/', '.');
                Class clazz = loader.loadClass(className);
                if (!Script.class.isAssignableFrom(clazz)) {
                    continue;
                }
                for (Annotation i : clazz.getDeclaredAnnotations()) {
                    if (i.annotationType().equals(ScriptMeta.class)) {
                        scripts.add(new ScriptEntry(clazz, (ScriptMeta) i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scripts;
    }

    private List<URL> getResourceFiles(String path) {
        List<URL> filenames = new ArrayList<>();
        File[] listOfFiles = new File(path).listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String name = listOfFiles[i].getName();
                if (name.contains(".jar")) {
                    try {
                        filenames.add(new URL("jar:file:" + path + "/" + name + "!/"));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    if (filenames.size() > 0) {
                        System.out.println(filenames.get(filenames.size() - 1).getPath());
                    }
                }
            }
        }
        return filenames;
    }

}
