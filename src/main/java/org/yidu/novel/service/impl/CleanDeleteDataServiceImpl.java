package org.yidu.novel.service.impl;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.service.CleanDeleteDataService;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 理删除数据服务实装类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class CleanDeleteDataServiceImpl extends HibernateSupportServiceImpl implements CleanDeleteDataService {

    @Override
    public void cleanDeleteData() {
        int keepdays = YiDuConstants.yiduConf.getInt(YiDuConfig.KEEP_DELETE_DATA_DAYS, 5);

        // 清理txt文件和img文件
        // 按小说删除txt文件和封面文件
        String getDeleteArtcleSql = "select articleno from t_article where deleteflag and modifytime < (now() -  INTERVAL ''{0} days'')";
        List<Integer> articlenoList = yiduJdbcTemplate.queryForList(
                MessageFormat.format(getDeleteArtcleSql, new Object[] { keepdays }), Integer.class);
        for (Integer articleno : articlenoList) {
            Utils.deleteDirectory(Utils.getTextDirectoryPathByArticleno(articleno));
            Utils.deleteDirectory(Utils.getArticlePicPath(articleno));
        }

        // 按章节删除txt文件
        String getDeleteChapterSql = "select * from t_chapter where deleteflag and modifytime < (now() -  INTERVAL ''{0} days'') ";
        if (Utils.isDefined(articlenoList)) {
            getDeleteChapterSql = getDeleteChapterSql + " and articlno not in (" + StringUtils.join(articlenoList, ",")
                    + ")";
        }

        List<TChapter> chapterList = yiduJdbcTemplate.query(
                MessageFormat.format(getDeleteChapterSql, new Object[] { keepdays }),
                new BeanPropertyRowMapper<TChapter>(TChapter.class));
        for (TChapter chapter : chapterList) {
            Utils.deleteFile(Utils.getTextFilePathByChapterno(chapter.getArticleno(), chapter.getChapterno()));
        }
        // 清理数据库数据
        Set<String> tableSet = new HashSet<String>();
        tableSet.add("t_user");
        tableSet.add("t_review");
        tableSet.add("t_chapter");
        tableSet.add("t_system_block");
        tableSet.add("t_bookcase");
        tableSet.add("t_article");
        tableSet.add("t_message");
        tableSet.add("t_credit_history");
        String deleteSql = "delete from {0} where deleteflag and modifytime < (now() -  INTERVAL ''{1} days'')";
        for (String table : tableSet) {
            yiduJdbcTemplate.execute(MessageFormat.format(deleteSql, new Object[] { table, keepdays }));
        }
    }
}
