package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.dto.ChapterDTO;
import org.yidu.novel.entity.TChapter;

/**
 * 
 * <p>
 * 提供章节信息操作的服务
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public interface ChapterService {
    /**
     * 通过章节编号取得章节信息
     * 
     * @param chapterno
     *            章节编号
     * @return 章节信息
     */
    TChapter getByNo(final int chapterno);

    /**
     * 判断该章节是否是VIP章节
     * 
     * @param articleno
     *            小说编号
     * @param chapterno
     *            章节编号
     * @param vipstartno
     *            VIP开始章节编号
     * @return VIP标示
     */
    public boolean checkVipByNo(final int articleno,final int chapterno,final int vipstartno);
    /**
     * 通过章节编号删除章节信息
     * 
     * @param chapterno
     *            章节编号
     */
    void deleteByNo(final int chapterno);

    /**
     * 保存章节信息
     * 
     * @param chapter
     *            章节信息
     */
    void save(final TChapter chapter);

    /**
     * 根据条件取得章节列表
     * 
     * @param searchBean
     *            检索条件
     * @return 章节列表
     */
    List<TChapter> find(final ChapterSearchBean searchBean);
    
    /**
     * 根据条件取得章节列表
     * 
     * @param searchBean
     *            检索条件
     * @return 章节列表
     */
    List<ChapterDTO> findWithPinyin(final ChapterSearchBean searchBean);

    /**
     * 根据条件取得章节件数
     * 
     * @param searchBean
     *            检索条件
     * @return 章节件数
     */
    Integer getCount(final ChapterSearchBean searchBean);

    /**
     * 根据小说编号获取最新章节信息
     * 
     * @param articleno
     *            小说编号
     * @return 最新章节信息
     */
    TChapter getLastChapter(final int articleno);

    /**
     * 根据小说编号获取章节数
     * 
     * @param articleno
     *            小说编号
     * @return 章节数
     */
    Integer getChapterCount(final int articleno);

    /**
     * 根据小说编号和章节编号和标致获取章下一个章节信息
     * 
     * @param articleno
     *            小说编号
     * @param chapterno
     *            章节编号
     * @param isNext
     *            TRUE的话，下一章；false的话，上一章
     * @return 章节信息
     */
    TChapter getNextChapter(final int articleno, final int chapterno, final boolean isNext);

    /**
     * 获取一个区间段内的章节, 用于全文阅读
     * 
     * @param articleno
     *            小说编号
     * @param chapterno
     *            开始章节编号
     * @param toChapterno
     *            结束章节编号
     * @return 章节列表
     */
    List<TChapter> getChapterInSegement(Integer articleno, Integer chapterno, Integer toChapterno);

    /**
     * 通过小说编号删除章节信息
     * 
     * @param articleno
     *            小说编号
     */
    void deleteAllByArticlno(final int articleno);

}
