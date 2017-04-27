package org.yidu.novel.bean;

public class SystemConfigSearchBean extends BaseSearchBean {

    private int configno;

    private String name;

    private String title;
    private String type;

    private String catname;

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return configno
     */
    public int getConfigno() {
        return configno;
    }

    /**
     * <p>
     * TODO confignoを設定。
     * </p>
     * 
     * @param configno
     *            configno
     */
    public void setConfigno(int configno) {
        this.configno = configno;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * <p>
     * TODO nameを設定。
     * </p>
     * 
     * @param name
     *            name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>
     * TODO titleを設定。
     * </p>
     * 
     * @param title
     *            title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * <p>
     * TODO typeを設定。
     * </p>
     * 
     * @param type
     *            type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return catname
     */
    public String getCatname() {
        return catname;
    }

    /**
     * <p>
     * TODO catnameを設定。
     * </p>
     * 
     * @param catname
     *            catname
     */
    public void setCatname(String catname) {
        this.catname = catname;
    }
}
