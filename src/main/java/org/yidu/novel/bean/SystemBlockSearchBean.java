package org.yidu.novel.bean;

public class SystemBlockSearchBean extends BaseSearchBean {

    private int blockno;
    private String blockname;
    private Short type;
    private String sortcol;
    private Boolean isasc;
    private Integer limitnum;
    private String content;
    private Short target;
    private Short[] targets;

    public int getBlockno() {
        return blockno;
    }

    public void setBlockno(int blockno) {
        this.blockno = blockno;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getSortcol() {
        return sortcol;
    }

    public void setSortcol(String sortcol) {
        this.sortcol = sortcol;
    }

    public Boolean getIsasc() {
        return isasc;
    }

    public void setIsasc(Boolean isasc) {
        this.isasc = isasc;
    }

    public Integer getLimitnum() {
        return limitnum;
    }

    public void setLimitnum(Integer limitnum) {
        this.limitnum = limitnum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Short getTarget() {
        return target;
    }

    public void setTarget(Short target) {
        this.target = target;
    }

    /**
     * 获取targets
     * 
     * @return targets
     */
    public Short[] getTargets() {
        return targets;
    }

    /**
     * 
     * 设置targets
     * 
     * 
     * @param targets
     *            targets
     */
    public void setTargets(Short... targets) {
        this.targets = targets;
    }

}
