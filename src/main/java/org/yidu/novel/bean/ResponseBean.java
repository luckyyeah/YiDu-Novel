package org.yidu.novel.bean;


public class ResponseBean<T> {

    private int status;

    private T dataObj;

    public ResponseBean() {
    }

    public ResponseBean(int status, T dataObj) {
        this.status = status;
        this.dataObj = dataObj;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return statusを戻します。
     */
    public int getStatus() {
        return status;
    }

    /**
     * <p>
     * TODO statusを設定。
     * </p>
     * 
     * @param status
     *            statusを設定。
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * <p>
     * TODO を取得。
     * </p>
     * 
     * @return dataObjを戻します。
     */
    public T getDataObj() {
        return dataObj;
    }

    /**
     * <p>
     * TODO dataObjを設定。
     * </p>
     * 
     * @param articleList
     *            dataObjを設定。
     */
    public void setDataObj(T articleList) {
        this.dataObj = articleList;
    }

}
