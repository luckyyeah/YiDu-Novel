package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.dto.CategoryCountDTO;
import org.yidu.novel.entity.TArticle;

/**
 * 
 * <p>
 * 提供小说信息操作的服务
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public interface ArticleService {

    /**
     * 通过小说编号取得小说信息
     * 
     * @param articleno
     *            小说号
     * @return 文章详细
     */
    TArticle getByNo(final int articleno);

    /**
     * 通过小说编号删除小说信息
     * 
     * @param articleno
     *            小说编号
     */
    void deleteByNo(final int articleno);

    /**
     * 根据条件取得小说列表
     * 
     * @param searchBean
     *            检索条件
     * @return 小说列表
     */

    List<TArticle> find(final ArticleSearchBean searchBean);

    /**
     * 根据条件取得小说件数
     * 
     * @param searchBean
     *            检索条件
     * @return 小说件数
     */
    Integer getCount(final ArticleSearchBean searchBean);

    /**
     * 通过拼音查找小说
     * 
     * @param pinyin
     *            拼音
     * @return 小说
     */
    TArticle findByPinyinRegularRxpressions(final String pinyin);

    /**
     * 保存小说信息
     * 
     * @param article
     *            小说信息
     */
    void save(final TArticle article);

    /**
     * 更新点击量信息
     * 
     * @param articleno
     *            小说编号
     */
    void updateVisitStatistic(final int articleno);

    /**
     * 更新推荐量信息
     * 
     * @param articleno
     *            小说编号
     */
    void updateVoteStatistic(final int articleno);

    /**
     * 清除小说统计信息
     */
    void cleanStatistics();

    /**
     * 根据条件取得小说件数
     * 
     * @param searchBean
     *            检索条件
     * @return 小说件数
     */
    Integer getCountByJDBC(final ArticleSearchBean searchBean);

    /**
     * 根据小说分类和小说编号取得推荐列表<br>
     * 其实就是取同类的小说中当前小说的之后的指定的count本小说<br>
     * 将来算法可能会更新，暂时做成这样
     * 
     * @param category
     *            分类编号
     * @param articleno
     *            小说编号
     * @return 推荐列表
     */

    List<TArticle> findRecommendArticleList(final int category, final int articleno, final int count);

    /**
     * 根据关键和件数取得相关小说列表<br>
     * 
     * @param keys
     *            相似关键字
     * @param count
     *            件数
     * @return 推荐列表
     */

    List<TArticle> findRelativeArticleList(List<String> keys, String sortCol, boolean isAsc, int limitNum);

    /**
     * 根据小说分类取得随机推荐列表<br>
     * 将来算法可能会更新，暂时做成这样
     * 
     * @param count
     *            小说件数
     * @return 推荐列表
     */
    List<TArticle> findRandomRecommendArticleList(final int count);

    /**
     * 获取各个分类的小说数量<br>
     * 
     * @return 各分类的小说数量
     */
    List<CategoryCountDTO> getCountPerCategory();

}
