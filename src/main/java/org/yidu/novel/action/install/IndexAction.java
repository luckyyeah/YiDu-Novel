package org.yidu.novel.action.install;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractInstallBaseAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 小说安装画面Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class IndexAction extends AbstractInstallBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = -5991997032217966675L;
    /**
     * 命名空间。
     */
    public static final String NAMESPACE = "install";
    /**
     * 功能名称。
     */
    public static final String NAME = "index";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;
    /**
     * 数据库链接前缀
     */
    private String prefixjdbc = "jdbc:postgresql://";
    /**
     * 安装锁文件地址
     */
    private static final String LOCK_FILE = ServletActionContext.getServletContext().getRealPath("/") + "/install.lock";
    /**
     * 网站标题
     */
    private String title;
    /**
     * 网站关键字
     */
    private String siteKeywords;
    /**
     * 网站描述
     */
    private String siteDescription;
    /**
     * 名称
     */
    private String name;
    /**
     * 网站url
     */
    private String url;
    /**
     * 版权信息
     */
    private String copyright;
    /**
     * 备案号
     */
    private String beianNo;
    /**
     * 统计代码
     */
    private String analyticscode;
    /**
     * 数据库Host
     */
    private String dbhost;
    /**
     * 数据库端口
     */
    private String dbport;
    /**
     * 数据库名称
     */
    private String dbname;
    /**
     * 数据库连接用户
     */
    private String dbusername;
    /**
     * 数据库连接密码
     */
    private String dbpassword;

    /**
     * 管理员用户名
     */
    private String username;
    /**
     * 管理员用密码
     */
    private String password;

    /**
     * 获取 prefixjdbc
     * 
     * @return prefixjdbc
     */
    public String getPrefixjdbc() {
        return prefixjdbc;
    }

    /**
     * 
     * 设置prefixjdbc
     * 
     * 
     * @param prefixjdbc
     *            prefixjdbc
     */
    public void setPrefixjdbc(String prefixjdbc) {
        this.prefixjdbc = prefixjdbc;
    }

    /**
     * 获取 title
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * 设置title
     * 
     * 
     * @param title
     *            title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 siteKeywords
     * 
     * @return siteKeywords
     */
    public String getSiteKeywords() {
        return siteKeywords;
    }

    /**
     * 
     * 设置siteKeywords
     * 
     * 
     * @param siteKeywords
     *            siteKeywords
     */
    public void setSiteKeywords(String siteKeywords) {
        this.siteKeywords = siteKeywords;
    }

    /**
     * 获取 siteDescription
     * 
     * @return siteDescription
     */
    public String getSiteDescription() {
        return siteDescription;
    }

    /**
     * 
     * 设置siteDescription
     * 
     * 
     * @param siteDescription
     *            siteDescription
     */
    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription;
    }

    /**
     * 获取 name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * 设置name
     * 
     * 
     * @param name
     *            name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 url
     * 
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * 设置url
     * 
     * 
     * @param url
     *            url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取 copyright
     * 
     * @return copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * 
     * 设置copyright
     * 
     * 
     * @param copyright
     *            copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * 获取 beianNo
     * 
     * @return beianNo
     */
    public String getBeianNo() {
        return beianNo;
    }

    /**
     * 
     * 设置beianNo
     * 
     * 
     * @param beianNo
     *            beianNo
     */
    public void setBeianNo(String beianNo) {
        this.beianNo = beianNo;
    }

    /**
     * 获取 analyticscode
     * 
     * @return analyticscode
     */
    public String getAnalyticscode() {
        return analyticscode;
    }

    /**
     * 
     * 设置analyticscode
     * 
     * 
     * @param analyticscode
     *            analyticscode
     */
    public void setAnalyticscode(String analyticscode) {
        this.analyticscode = analyticscode;
    }

    /**
     * 获取 dbhost
     * 
     * @return dbhost
     */
    public String getDbhost() {
        return dbhost;
    }

    /**
     * 
     * 设置dbhost
     * 
     * 
     * @param dbhost
     *            dbhost
     */
    public void setDbhost(String dbhost) {
        this.dbhost = dbhost;
    }

    /**
     * 获取 dbport
     * 
     * @return dbport
     */
    public String getDbport() {
        return dbport;
    }

    /**
     * 
     * 设置dbport
     * 
     * 
     * @param dbport
     *            dbport
     */
    public void setDbport(String dbport) {
        this.dbport = dbport;
    }

    /**
     * 获取 dbname
     * 
     * @return dbname
     */
    public String getDbname() {
        return dbname;
    }

    /**
     * 
     * 设置dbname
     * 
     * 
     * @param dbname
     *            dbname
     */
    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    /**
     * 获取 dbusername
     * 
     * @return dbusername
     */
    public String getDbusername() {
        return dbusername;
    }

    /**
     * 
     * 设置dbusername
     * 
     * 
     * @param dbusername
     *            dbusername
     */
    public void setDbusername(String dbusername) {
        this.dbusername = dbusername;
    }

    /**
     * 获取 dbpassword
     * 
     * @return dbpassword
     */
    public String getDbpassword() {
        return dbpassword;
    }

    /**
     * 
     * 设置dbpassword
     * 
     * 
     * @param dbpassword
     *            dbpassword
     */
    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    /**
     * 获取 username
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * 设置username
     * 
     * 
     * @param username
     *            username
     */
    public void setUsername(String username) {
        this.username = StringUtils.trim(username);
    }

    /**
     * 获取 password
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * 设置password
     * 
     * 
     * @param password
     *            password
     */
    public void setPassword(String password) {
        this.password = StringUtils.trim(password);
    }

    @SkipValidation
    @Override
    public String execute() {
        logger.debug("execute start.");

        File lockFile = new File(LOCK_FILE);
        if (lockFile.exists()) {
            addActionError(getText("errors.install.file.exist", new String[] { LOCK_FILE }));
            return ERROR;
        }

        // 设定文件初期读入
        try {
            PropertiesConfiguration jdbcConf = new PropertiesConfiguration("jdbc.properties");
            String dburl = jdbcConf.getString(YiDuConfig.JDBC_URL).substring(prefixjdbc.length());
            String[] dbinfo = StringUtils.split(dburl, ":");
            dbhost = dbinfo[0];
            dbinfo = StringUtils.split(dbinfo[1], "/");
            dbport = dbinfo[0];
            // 数据库名暂时固定
            dbname = "yidu";
            dbusername = jdbcConf.getString(YiDuConfig.JDBC_USERNAME);
            dbpassword = jdbcConf.getString(YiDuConfig.JDBC_PASSWORD);
            PropertiesConfiguration languageConf = new PropertiesConfiguration(Thread.currentThread()
                    .getContextClassLoader().getResource("language/package.properties"));
            title = languageConf.getString(YiDuConfig.TITLE);
            siteKeywords = languageConf.getString(YiDuConfig.SITEKEYWORDS);
            siteDescription = languageConf.getString(YiDuConfig.SITEDESCRIPTION);
            name = languageConf.getString(YiDuConfig.NAME);
            url = languageConf.getString(YiDuConfig.URL);
            copyright = languageConf.getString(YiDuConfig.COPYRIGHT);
            beianNo = languageConf.getString(YiDuConfig.BEIANNO);
            analyticscode = languageConf.getString(YiDuConfig.ANALYTICSCODE);

        } catch (ConfigurationException e) {
            logger.error(e);
        }
        logger.debug("execute normally end.");
        return INPUT;
    }

    /**
     * 安装界面内容保存
     * 
     * @return 模版名字
     */
    public String save() {

        File lockFile = new File(LOCK_FILE);
        if (lockFile.exists()) {
            addActionError(getText("errors.install.file.exist", new String[] { LOCK_FILE }));
            return ERROR;
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 链接数据库
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection(prefixjdbc + dbhost + ":" + dbport + "/postgres", dbusername, dbpassword);
            stmt = conn.createStatement();
            rs = null;
            // 检查数据库是否存在
            String checkDBsql = "SELECT 1 FROM pg_database WHERE datname = '" + dbname + "'";
            rs = stmt.executeQuery(checkDBsql);
            boolean dbExist = false;
            while (rs.next()) {
                dbExist = true;
            }
            // 存在的话，先删除
            if (dbExist) {
                stmt.execute("drop database " + dbname);
            }
            excuteSqlFromFile(conn, "01_createdb.sql", new Object[] { dbname });
            // 切换数据库
            conn = DriverManager.getConnection(prefixjdbc + dbhost + ":" + dbport + "/" + dbname, dbusername,
                    dbpassword);
            // 建表
            excuteSqlFromFile(conn, "02_createtable.sql", new Object[] {});
            excuteSqlFromFile(conn, "03_createindex.sql", new Object[] {});
            excuteSqlFromFile(conn, "04_master_data.sql", new Object[] { username, Utils.convert2MD5(password) });

            // 更新jdbc文件
            PropertiesConfiguration jdbcConf = new PropertiesConfiguration(Thread.currentThread()
                    .getContextClassLoader().getResource("jdbc.properties"));

            jdbcConf.setProperty(YiDuConfig.JDBC_URL, prefixjdbc + dbhost + ":" + dbport + "/" + dbname);
            jdbcConf.setProperty(YiDuConfig.JDBC_USERNAME, dbusername);
            jdbcConf.setProperty(YiDuConfig.JDBC_PASSWORD, dbpassword);

            File jdbcFile = new File(jdbcConf.getPath());
            OutputStream out = new FileOutputStream(jdbcFile);
            jdbcConf.save(out);

            // 更新语言文件
            PropertiesConfiguration languageConf = new PropertiesConfiguration(Thread.currentThread()
                    .getContextClassLoader().getResource("language/package.properties"));

            languageConf.setProperty(YiDuConfig.TITLE, title);
            languageConf.setProperty(YiDuConfig.SITEKEYWORDS, siteKeywords);
            languageConf.setProperty(YiDuConfig.SITEDESCRIPTION, siteDescription);
            languageConf.setProperty(YiDuConfig.NAME, name);
            languageConf.setProperty(YiDuConfig.URL, url);
            languageConf.setProperty(YiDuConfig.COPYRIGHT, copyright);
            languageConf.setProperty(YiDuConfig.BEIANNO, beianNo);
            languageConf.setProperty(YiDuConfig.ANALYTICSCODE, analyticscode);

            File languageFile = new File(languageConf.getPath());
            out = new FileOutputStream(languageFile);
            languageConf.save(out);

            YiDuConstants.yiduConf.setProperty(YiDuConfig.URI, url);
            File yiduConfFile = new File(YiDuConstants.yiduConf.getPath());
            out = new FileOutputStream(yiduConfFile);
            YiDuConstants.yiduConf.save(out);

            lockFile.createNewFile();

        } catch (Exception e) {
            addActionError(e.getMessage());
            logger.error(e.getMessage(), e);
            return ERROR;
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        addActionMessage(getText("messages.install.success"));
        return SUCCESS;
    }

    /**
     * 执行SQL文件
     * 
     * @param conn
     *            数据库连接
     * @param fileName
     *            文件名
     * @param params
     *            执行参数
     * @throws IOException
     *             IO异常
     * @throws SQLException
     *             SQL异常
     */
    private void excuteSqlFromFile(Connection conn, String fileName, Object... params) throws IOException, SQLException {

        // 新建数据库
        java.net.URL url = this.getClass().getResource(fileName);
        // 从URL对象中获取路径信息
        String realPath = url.getPath();

        File file = new File(realPath);
        // 指定文件字符集
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        String sql = new String();
        String line = new String();
        while ((line = br.readLine()) != (null)) {
            sql += line + "\r\n";
        }
        br.close();
        Statement stmt = conn.createStatement();
        sql = MessageFormat.format(sql, params);
        stmt.execute(sql);
    }

    @Override
    protected void loadData() {
    }

}
