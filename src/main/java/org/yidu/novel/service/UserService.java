package org.yidu.novel.service;

import java.util.Date;
import java.util.List;

import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.entity.TChapterOrder;
import org.yidu.novel.entity.TUser;

/**
 * 
 * <p>
 * 提供用户信息操作的服务
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public interface UserService {

    /**
     * 根据帐号密码取得用户信息，如果取不到就返回NULL
     * 
     * @param loginid
     *            用户ID
     * @param password
     *            密码
     * @return 用户信息
     */
    TUser findByLoginInfo(final String loginid, final String password);

    /**
     * 根据帐号密码取得用户信息，如果取不到就返回NULL<br>
     * 为了在拦截器里使用，临时添加，将来应该和上面合并
     * 
     * @param loginid
     *            登录ID
     * @param password
     *            密码
     * @return 用户信息
     */
    TUser findByLoginInfoByJDBC(String loginid, String password);

    /**
     * 根据QQ的OpenID取得用户信息，如果取不到就返回NULL
     * 
     * @param openid
     *            QQ的openid
     * @return 用户信息
     */
    TUser findByOpenid(final String openid);

    /**
     * 更新用户的最后登录时间
     * 
     * @param userno
     *            用户编号
     * @param lastLoginDate
     *            最后登录时间
     */
    void updateLastLoginDate(int userno, Date lastLoginDate);

    /**
     * 取得条件用户列表
     * 
     * @param searchBean
     *            检索条件
     * @return 用户信息列表
     */
    List<TUser> find(final UserSearchBean searchBean);

    /**
     * 根据用户号取得用户信息，如果取不到就返回NULL<br>
     * 
     * @param userno
     *            用户编号
     * @return 用户信息
     */
    TUser getByNo(final int userno);

    /**
     * 根据用户号删除用户信息
     * 
     * @param userno
     *            用户编号
     */
    void deleteByNo(final int userno);

    /**
     * 保存用户信息
     * 
     * @param user
     *            用户信息
     */
    void save(final TUser user);
    
    /**
     * 保存用户信息
     * 
     * @param user
     *            用户信息
     */
    void save(final TChapterOrder tChapterOrder);

    /**
     * 取得条件用户件数
     * 
     * @param searchBean
     *            检索条件
     * @return 件数
     */
    int getCount(final UserSearchBean searchBean);
    
    /**
     * 锁表
     *
     */
    void lockTUser();


}
