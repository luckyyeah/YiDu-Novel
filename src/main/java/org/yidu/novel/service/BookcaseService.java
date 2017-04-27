package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.BookcaseSearchBean;
import org.yidu.novel.dto.BookcaseDTO;
import org.yidu.novel.entity.TBookcase;

/**
 * 
 * <p>
 * 提供书签操作的服务
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public interface BookcaseService {
    /**
     * 通过书签编号取得书签信息
     * 
     * @param bookcaseno
     *            书签编号
     * @return 书签信息
     */
    TBookcase getByNo(final int bookcaseno);

    /**
     * 通过书签编号删除书签信息
     * 
     * @param bookcaseno
     *            书签编号
     */
    void deleteByNo(final int bookcaseno);

    /**
     * 保存书签信息
     * 
     * @param bookcase
     *            书签信息
     */
    void save(final TBookcase bookcase);

    /**
     * 根据条件用户编号和小说编号查询书签
     * 
     * @param userno
     *            用户编号
     * @param articleno
     *            小说编号
     * @return 书签件数
     */
    TBookcase getByArticlenoAndUserno(final int userno, final int articleno);

    /**
     * 根据条件取得书签件数
     * 
     * @param searchBean
     *            检索条件
     * @return 书签件数
     */
    Integer getCount(final BookcaseSearchBean searchBean);

    /**
     * 根据条件取得书签列表
     * 
     * @param searchBean
     *            检索条件
     * @return 书签列表
     */
    List<TBookcase> find(final BookcaseSearchBean searchBean);

    /**
     * 根据条件取得书签列表
     * 
     * @param searchBean
     *            检索条件
     * @return 书签列表
     */
    List<BookcaseDTO> findWithArticleInfo(final BookcaseSearchBean searchBean);

    /**
     * 根据书签编号和用户编号批量删除书签信息
     * 
     * @param bookcasenos
     *            检索条件
     * @param userno
     *            用户编号
     */
    void batchDeleteByNo(final String bookcasenos, final int userno);

    /**
     * 根据小说编号和用户编号删除书签信息
     * 
     * @param articleno
     *            小说编号
     * @param userno
     *            用户编号
     */
    void deleteByArticleno(int articleno, int userno);

}
