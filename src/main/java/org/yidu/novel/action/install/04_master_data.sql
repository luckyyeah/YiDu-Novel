INSERT INTO t_system_block( blockid,blockname,TYPE,sortcol,isasc,limitnum,target) VALUES (''last_update_list'',''最新更新列表'',10,''lastupdate'',false,15,6);
INSERT INTO t_system_block( blockid,blockname,TYPE,sortcol,isasc,limitnum,target) VALUES (''last_insert_list'',''最新入库'',10,''postdate'',false,15,6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES (''friend_link'',''友情链接'',30,''<a href="http://www.51yd.org" target="_blank">易读小说系统</a>'',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES (''index_yanqing_tuijian'',''首页言情推荐'',20,''1,2,3,4'',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES (''index_xuanhuan_tuijian'',''首页玄幻推荐'',20,''1,2,3,4'',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES (''index_junshi_tuijian'',''首页军事推荐'',20,''1,2,3,4'',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES (''index_kongbu_tuijian'',''首页恐怖灵异推荐'',20,''1,2,3,4'',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES (''index_wuxia_tuijian'',''首页武侠修真推荐'',20,''1,2,3,4'',6);
INSERT INTO t_system_block( blockid, blockname, type, sortcol, isasc, limitnum, target, deleteflag) VALUES (''last_update_list_mobile'',''手机页面更新列表'',10,''lastupdate'',FALSE,6,6,FALSE);
INSERT INTO t_system_block( blockid, blockname, type, isasc, content,  target, deleteflag) VALUES (''index_hot_list_mobile'',''手机页热点'',20,FALSE,''58755,58754,58753'',6,FALSE);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag ) VALUES (''info_random_recommand_list'', ''简介页随机推荐列表'', 40, 6, 2, false);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag) VALUES (''chapterList_randomrecommand_list'', ''章节列表页随机推荐列表'', 40, 6, 3, false);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag) VALUES (''reader_random_recommand_list'', ''阅读页随机推荐列表'', 40, 6, 4, false);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag) VALUES (''reader_recommand_list'', ''阅读页推荐列表'', 50, 6, 4, false);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag) VALUES (''info_recommand_list'', ''简介页推荐列表'', 50, 6, 2, false);
INSERT INTO t_system_block( blockid, blockname, type, limitnum, target, deleteflag) VALUES (''chapterList_recommand_list'', ''章节列表页推荐列表'', 50, 6, 3, false);

INSERT INTO t_user(loginid, password,type,deleteflag,activedflag) VALUES (''{0}'', ''{1}'', 30 ,false,true);
