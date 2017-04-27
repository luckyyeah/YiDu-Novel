package org.yidu.novel.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.ChapterDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.utils.Utils;

/**
 * <p>
 * 章节阅读
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class ReaderAction extends AbstractPublicBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 808868850208875182L;

    /**
     * 功能名称。
     */
    public static final String NAME = "reader";

    /**
     * 访问URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;
    /**
     * 小说号
     */
    private int articleno;
    /**
     * 章节号
     */
    private int chapterno;

    /**
     * 分段阅读 - 截止章节号
     */
    private int toChapterno;

    /**
     * 章节内容
     */
    private ChapterDTO chapter;

    /**
     * 小说内容
     */
    private TArticle article;

    /**
     * 全文阅读内容
     */
    private List<ChapterDTO> fullReadChapterList;

    /**
     * 获取articleno
     * 
     * @return articleno
     */
    public int getArticleno() {
        return articleno;
    }

    /**
     * 
     * 设置articleno
     * 
     * 
     * @param articleno
     *            articleno
     */
    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    /**
     * 获取chapterno
     * 
     * @return chapterno
     */
    public int getChapterno() {
        return chapterno;
    }

    /**
     * 
     * 设置chapterno
     * 
     * 
     * @param chapterno
     *            chapterno
     */
    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    /**
     * 获取toChapterno
     * 
     * @return toChapterno
     */
    public int getToChapterno() {
        return toChapterno;
    }

    /**
     * 
     * 设置toChapterno
     * 
     * 
     * @param toChapterno
     *            toChapterno
     */
    public void setToChapterno(int toChapterno) {
        this.toChapterno = toChapterno;
    }

    /**
     * 获取chapter
     * 
     * @return chapter
     */
    public ChapterDTO getChapter() {
        return chapter;
    }

    /**
     * 
     * 设置chapter
     * 
     * 
     * @param chapter
     *            chapter
     */
    public void setChapter(ChapterDTO chapter) {
        this.chapter = chapter;
    }

    /**
     * 获取fullReadChapterList
     * 
     * @return fullReadChapterList
     */
    public List<ChapterDTO> getFullReadChapterList() {
        return fullReadChapterList;
    }

    /**
     * 
     * 设置fullReadChapterList
     * 
     * 
     * @param fullReadChapterList
     *            fullReadChapterList
     */
    public void setFullReadChapterList(List<ChapterDTO> fullReadChapterList) {
        this.fullReadChapterList = fullReadChapterList;
    }

    /**
     * 获取article
     * 
     * @return article
     */
    public TArticle getArticle() {
        return article;
    }

    /**
     * 
     * 设置article
     * 
     * 
     * @param article
     *            article
     */
    public void setArticle(TArticle article) {
        this.article = article;
    }

    @Override
    public String getTempName() {
        if (toChapterno != 0 && toChapterno > chapterno) {
            return "rangereader";
        }
        return "reader";
    }

    @Override
    protected void loadData() {
        logger.debug("loadData start.");
        if (toChapterno != 0 && toChapterno > chapterno) {
            fullReadChapterList = new ArrayList<ChapterDTO>();
            List<TChapter> segChapterList = chapterService.getChapterInSegement(articleno, chapterno, toChapterno);
            if (segChapterList == null || segChapterList.size() == 0) {
                addActionError(getText("errors.not.exsits.chapter"));
                setNotFound(true);
            } else {
                for (TChapter tchapter : segChapterList) {
                    ChapterDTO chapterDto = new ChapterDTO();
                    BeanUtils.copyProperties(tchapter, chapterDto);
                    if (chapterDto != null && chapterDto.getChapterno() != 0) {
                        chapterDto.setContent(Utils.getContext(chapterDto, true,
                                YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PSEUDO, false)));
                    }

                    fullReadChapterList.add(chapterDto);
                }
                chapter = new ChapterDTO();
                BeanUtils.copyProperties(segChapterList.get(0), chapter);
                chapter.setChaptername(chapter.getChaptername() + " - "
                        + segChapterList.get(segChapterList.size() - 1).getChaptername());
            }
        } else {
            ChapterDTO chapterDto = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_CHAPTER_PREFIX,
                    chapterno);
            if (chapterDto == null || chapterDto.getChapterno() == 0) {
                TChapter tchapter = chapterService.getByNo(chapterno);
                if (tchapter != null && tchapter.getChapterno() != 0) {
                    TChapter nextchapter = chapterService.getNextChapter(tchapter.getArticleno(), chapterno, true);
                    TChapter prechapter = chapterService.getNextChapter(tchapter.getArticleno(), chapterno, false);
                    chapterDto = new ChapterDTO();
                    BeanUtils.copyProperties(tchapter, chapterDto);
                    if (nextchapter != null) {
                        chapterDto.setNextChapterno(nextchapter.getChapterno());
                    }
                    if (prechapter != null) {
                        chapterDto.setPreChapterno(prechapter.getChapterno());
                    }
                    CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_CHAPTER_PREFIX, chapterno, chapterDto);

                } else {
                    addActionError(getText("errors.not.exsits.chapter"));
                    setNotFound(true);
                    return;
                }
            }
            chapter = chapterDto;
            chapter.setContent(Utils.getContext(chapter, true,
                    YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PSEUDO, false)));
        }

        // 更新统计信息
        if (chapter != null && chapter.getArticleno() != 0) {
            article = CacheManager.getObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX,
                    chapter.getArticleno());
            if (article == null || article.getArticleno() == 0) {
                article = articleService.getByNo(chapter.getArticleno());
                CacheManager.putObject(CacheManager.CacheKeyPrefix.CACHE_KEY_ARTICEL_PREFIX, articleno, article);
            }
            if (article == null || article.getArticleno() == 0) {
                addActionError(getText("errors.not.exsits.chapter"));
                return;
            }
            articleService.updateVisitStatistic(chapter.getArticleno());
        }
        // 设置拼音信息
        chapter.setPinyin(article.getPinyin());
        logger.debug("loadData normally end.");
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_READER;
    }

    @Override
    protected String getBlockKey() {
        return CacheManager.CacheKeyPrefix.CACHE_KEY_READER_BLOCK + articleno;
    }

    @Override
    protected Short getBlockTarget() {
        return YiDuConstants.BlockTarget.READER_PAGE;
    }

    /**
     * 取得单本用singleBookServerName
     * 
     * @return 单本用singleBookServerName
     */
    public String getSingleBookServerName() {
        return YiDuConstants.singleBookServerName.get();
    }

    @Override
    protected int getRecommondArticleno() {
        if (Utils.isDefined(article)) {
            return article.getArticleno();
        }
        return 0;
    }

    @Override
    protected int getRecommondCategory() {
        if (Utils.isDefined(article) && Utils.isDefined(article.getCategory())) {
            return article.getCategory();
        } else {
            return 0;
        }
    }

    @Override
    protected String getRelativeArticleName() {
        if (Utils.isDefined(article)) {
            return article.getArticlename();
        }
        return null;
    }

    @Override
    protected String getAuthor() {
        if (Utils.isDefined(article)) {
            return article.getAuthor();
        }
        return null;
    }
}
