package org.yidu.novel.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * 文件操作工具类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author tkts@qq.com
 */
public class FileUtils {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 文件路径协议
     * */
    private static final String PROTOCOL_FILE = "file";

    /**
     * 文件路径协议
     * */
    private static final String FILE_SCHEME = "file:";

    /**
     * 数字转换进制：16
     * */
    private static final int HEX = 16;

    /**
     * 文件分隔符
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * <p>
     * 将URL转为File，失败返回null.
     * </p>
     * 
     * @param url
     *            URL
     * @return 转换的文件对象
     */
    public static File fileFromURL(URL url) {
        if (url == null || !url.getProtocol().equals(PROTOCOL_FILE)) {
            return null;
        } else {
            String filename = url.getFile().replace('/', File.separatorChar);
            int pos = 0;
            while ((pos = filename.indexOf('%', pos)) >= 0) {
                if (pos + 2 < filename.length()) {
                    String hexStr = filename.substring(pos + 1, pos + 3);
                    char ch = (char) Integer.parseInt(hexStr, HEX);
                    filename = filename.substring(0, pos) + ch + filename.substring(pos + 3);
                }
            }
            return new File(filename);
        }
    }

    /**
     * <p>
     * 返回不带文件名的路径, 例如： http://xyz.net/foo/bar.xml 返回 http://xyz.net/foo/
     * </p>
     * 
     * @param url
     *            需要提取路径的URL
     * @return the path 提取出的路径
     */
    public static String getBasePath(URL url) {
        if (url == null) {
            return null;
        }

        String s = url.toString();
        if (s.startsWith(FILE_SCHEME) && !s.startsWith("file://")) {
            s = "file://" + s.substring(FILE_SCHEME.length());
        }

        if (s.endsWith("/") || StringUtils.isEmpty(url.getPath())) {
            return s;
        } else {
            return s.substring(0, s.lastIndexOf("/") + 1);
        }
    }

    /**
     * 
     * <p>
     * 返回不带文件名的路径, 例如：file:///D:/xyz.net/foo/bar.xml 返回 file:///D:/xyz.net/foo/
     * </p>
     * 
     * @param path
     *            路径
     * @return String
     */
    public static String getBasePath(String path) {
        URL url;
        try {
            url = getURL(null, path);
            return getBasePath(url);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * <p>
     * 返回不带路径的文件名, 例如：file:///D:/xyz.net/foo/bar.xml 返回 bar.xml
     * </p>
     * 
     * @param url
     *            需要提取文件的URL
     * @return 提取出来的文件名
     */
    public static String getFileName(URL url) {
        if (url == null) {
            return null;
        }

        String path = url.getPath();

        if (path.endsWith("/") || StringUtils.isEmpty(path)) {
            return null;
        } else {
            return path.substring(path.lastIndexOf("/") + 1);
        }
    }

    /**
     * 
     * <p>
     * 返回不带路径的文件名, 例如：file:///D:/xyz.net/foo/bar.xml 返回 bar.xml
     * </p>
     * 
     * @param path
     *            路径
     * @return String
     */
    public static String getFileName(String path) {
        URL url;
        try {
            url = getURL(null, path);
            return FileUtils.getFileName(url);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * <p>
     * 将指定文件转换为URL
     * </p>
     * 
     * @param file
     *            需要转换的文件
     * @return url
     * @throws MalformedURLException
     *             URL异常
     */
    public static URL toURL(File file) throws MalformedURLException {
        return file.toURI().toURL();
    }

    /**
     * <p>
     * 查找文件路径， 查找优先级： 当前工作目录 > 当前classpath > 系统classpath
     * </p>
     * 
     * @param base
     *            文件基础路径
     * @param name
     *            文件名
     * 
     * @return 文件路径
     */
    public static URL locate(String base, String name) {
        if (logger.isDebugEnabled()) {
            logger.debug("定位配置文件: 基础路径  [{}],文件名[{}]", new Object[] { base, name });
        }

        if (name == null) {
            return null;
        }

        URL url = locateFromURL(base, name);

        // 通过绝对路径加载文件
        if (url == null) {
            File file = new File(name);
            if (file.isAbsolute() && file.exists()) {
                // already absolute?
                try {
                    url = toURL(file);
                    logger.debug("通过绝对路径加载配置： " + name);
                } catch (MalformedURLException e) {
                    logger.warn("无法获取文件路径", e);
                }
            }
        }

        // 通过相对路径加载文件
        if (url == null) {
            try {
                File file = constructFile(base, name);
                if (file != null && file.exists()) {
                    url = toURL(file);
                }

                if (url != null) {
                    logger.debug("从{}加载配置 ", file);
                }
            } catch (MalformedURLException e) {
                logger.warn("无法通过文件获取URL", e);
            }
        }

        if (url == null) {
            try {
                File file = constructFile(System.getProperty("user.home"), name);
                if (file != null && file.exists()) {
                    url = toURL(file);
                }

                if (url != null) {
                    logger.debug("Loading configuration from the home path " + file);
                }

            } catch (MalformedURLException e) {
                logger.warn("Could not obtain URL from file", e);
            }
        }

        // attempt to load from classpath
        if (url == null) {
            url = locateFromClasspath(name);
        }
        return url;
    }

    /**
     * <p>
     * 在类路径中查找资源, 查找路径 程序上下文环境 > 系统类路径
     * </p>
     * 
     * @param resourceName
     *            资源名
     * @return 资源名对应的资源路径URL， 如果资源未找到则返回 <b>null</b>
     */
    public static URL locateFromClasspath(String resourceName) {
        URL url = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader != null) {
            url = loader.getResource(resourceName);

            if (url != null) {
                logger.debug("Locate file from the context classpath (" + resourceName + ")");
            }
        }

        if (url == null) {
            url = ClassLoader.getSystemResource(resourceName);

            if (url != null) {
                logger.debug("Locate file from the system classpath (" + resourceName + ")");
            }
        }
        return url;
    }

    /**
     * 
     * <p>
     * 获取指定文件名对应文件的绝对路径
     * </p>
     * 
     * @param resourceName
     *            资源名
     * @return 资源文件
     */
    public static File locateAbsolutePathFromClasspath(String resourceName) {
        URL url = locateFromClasspath(resourceName);
        return fileFromURL(url);
    }

    /**
     * <p>
     * 通过基础路径和文件名构造文件对象.
     * </p>
     * 
     * @param basePath
     *            根目录
     * @param fileName
     *            文件名
     * @return 构造出的文件对象
     */
    public static File constructFile(String basePath, String fileName) {
        File file;

        File absolute = null;
        if (fileName != null) {
            absolute = new File(fileName);
        }

        if (StringUtils.isEmpty(basePath) || (absolute != null && absolute.isAbsolute())) {
            file = new File(fileName);
        } else {
            StringBuilder fName = new StringBuilder();
            fName.append(basePath);

            if (!basePath.endsWith(File.separator)) {
                fName.append(File.separator);
            }

            if (fileName.startsWith("." + File.separator)) {
                fName.append(fileName.substring(2));
            } else {
                fName.append(fileName);
            }

            file = new File(fName.toString());
        }

        return file;
    }

    /**
     * <p>
     * 检查文件路径是否存在， 不存在则创建
     * </p>
     * 
     * @param file
     *            文件
     */
    public static void createPath(File file) {
        if (file != null) {
            if (!file.exists()) {
                File parent = file.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }
            }
        }
    }

    /**
     * 
     * <p>
     * 通过指定的路径和文件名获取URL， 对URL是否可读不做检查
     * </p>
     * 
     * @param basePath
     *            跟目录
     * @param fileName
     *            文件名
     * @return URL
     * @throws MalformedURLException
     *             URL异常
     */
    public static URL getURL(String basePath, String fileName) throws MalformedURLException {
        File f = new File(fileName);
        if (f.isAbsolute()) {
            return toURL(f);
        }

        try {
            if (basePath == null) {
                return new URL(fileName);
            } else {
                URL base = new URL(basePath);
                return new URL(base, fileName);
            }
        } catch (MalformedURLException uex) {
            return FileUtils.toURL(FileUtils.constructFile(basePath, fileName));
        }
    }

    /**
     * 
     * <p>
     * 通过指定的基础路径和文件名获取URL， 获取到的URL一定是可读的
     * </p>
     * 
     * @param basePath
     *            基础路径， 必须存在， 否则抛异常
     * @param fileName
     *            文件名， 必须存在， 否则抛异常
     * @return URL
     */
    public static URL locateFromURL(String basePath, String fileName) {
        try {
            URL url;
            if (basePath == null) {
                return new URL(fileName);
            } else {
                URL baseURL = new URL(basePath);
                url = new URL(baseURL, fileName);

                // check if the file exists
                InputStream in = null;
                try {
                    in = url.openStream();
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }
                return url;
            }
        } catch (IOException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Could not locate file " + fileName + " at " + basePath + ": " + e.getMessage());
            }
            return null;
        }
    }

    /**
     * 
     * <p>
     * 读取多线程配置文件
     * </p>
     * 
     * @param fileName
     *            文件名
     * @return 获得的参数
     */
    public static List<String[]> readRunArgs(String fileName) {
        URL url = locateFromClasspath(fileName);
        File file = fileFromURL(url);

        List<String[]> list = new ArrayList<String[]>();

        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                if (StringUtils.isNotBlank(lineTxt)) {
                    // #开头为注释行， 略过
                    if (!lineTxt.startsWith("#")) {
                        list.add(lineTxt.split("\\s"));
                    }
                }
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 按照目标站编码读取文件
     * 
     * @param fileName
     *            文件名
     * @return 读取文件内容
     */
    public static String readFile(String fileName) {
        return readFile(fileName, "UTF-8");
    }

    /**
     * 根据指定编码读取文件
     * 
     * @param fileName
     *            文件名
     * @param charset
     *            字符集
     * @return 文件字符串
     */
    public static String readFile(String fileName, String charset) {
        StringBuffer sb = new StringBuffer();
        File file = new File(fileName);
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), charset);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                sb.append(lineTxt + System.getProperty("line.separator"));
            }
            read.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return sb.toString();
    }

    /**
     * 向指定文件中写入内容
     * 
     * @param destFile
     *            要写入的文件
     * @param content
     *            要写入的内容
     * @param append
     *            是否追加， true为追加内容
     */
    public static void writeFile(File destFile, String content, boolean append) {
        writeFile(destFile, content, "UTF-8", append);
    }

    /**
     * 向指定文件中写入内容
     * 
     * @param destFile
     *            要写入的文件
     * @param content
     *            要写入的内容
     * @param charset
     *            编码
     * @param append
     *            是否追加， true为追加内容
     */
    public static void writeFile(File destFile, String content, String charset, boolean append) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFile, append), charset));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 
     * <p>
     * 删除文件
     * </p>
     * 
     * @param path
     *            文件路径
     * @return 成功标识
     */
    public static boolean deleteFile(String path) {
        return deleteFile(new File(path));
    }

    /**
     * 
     * <p>
     * 删除文件
     * </p>
     * 
     * @param file
     *            文件
     * @return 删除成功标识
     */
    public static boolean deleteFile(File file) {
        boolean result = false;
        if (file.exists()) {
            result = file.delete();
        }
        return result;
    }

    /**
     * 
     * <p>
     * 下载文件
     * </p>
     * 
     * @param remotePath
     *            目标文件
     * @param localPath
     *            本地文件
     */
    public static void download(String remotePath, String localPath) {
        URL url = null;
        try {
            url = new URL(remotePath);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();

            FileOutputStream fos = new FileOutputStream(localPath);

            byte[] buffer = new byte[1024];
            int byteread = 0;
            while ((byteread = inStream.read(buffer)) != -1) {
                fos.write(buffer, 0, byteread);
            }
            fos.close();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
