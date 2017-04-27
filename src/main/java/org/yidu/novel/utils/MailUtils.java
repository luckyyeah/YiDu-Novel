package org.yidu.novel.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

/**
 * 
 * <p>
 * 邮件工具类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class MailUtils {
    /**
     * logger
     */
    protected static Log logger = LogFactory.getLog(MailUtils.class);

    /**
     * 发邮件处理
     * 
     * @param toAddr
     *            邮件地址
     * @param content
     *            邮件内容
     * @return 成功标识
     */
    public static boolean sendMail(String toAddr, String title, String content, boolean isHtmlFormat) {

        final String username = YiDuConstants.yiduConf.getString(YiDuConfig.MAIL_SMTP_USERNAME);
        final String password = YiDuConstants.yiduConf.getString(YiDuConfig.MAIL_SMTP_PASSWORD);

        Properties props = new Properties();
        props.put("mail.smtp.auth", YiDuConstants.yiduConf.getBoolean(YiDuConfig.MAIL_SMTP_AUTH, true));
        props.put("mail.smtp.starttls.enable",
                YiDuConstants.yiduConf.getBoolean(YiDuConfig.MAIL_SMTP_STARTTLS_ENABLE, true));
        props.put("mail.smtp.host", YiDuConstants.yiduConf.getString(YiDuConfig.MAIL_SMTP_HOST));
        props.put("mail.smtp.port", YiDuConstants.yiduConf.getInt(YiDuConfig.MAIL_SMTP_PORT, 25));

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(YiDuConstants.yiduConf.getString(YiDuConfig.MAIL_SMTP_FROM)));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddr));
            message.setSubject(title);
            if (isHtmlFormat) {
                message.setContent(content, "text/html");
            } else {
                message.setText(content);
            }
            Transport.send(message);

        } catch (MessagingException e) {
            logger.warn(e);
            return false;
        }
        return true;

    }

}
