package com.zy.oes.common.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * author:         MoZhu
 * date:           2023/3/4 17:45
 * description:    description
 */
public class MailUtil {
    private static final Logger log = LoggerFactory.getLogger(MailUtil.class);
    private final static String SenderEmail = "tpai18522947960@163.com";//开启授权码的邮箱
    private final static String senderCode = "OAYMRZLWIYSWUYZD";//授权码
    public static final String emailSMTPHost = "smtp.163.com";//服务器地址


    /**
     * 发送邮件
     *
     * @param receiveMailAccount 收件人
     * @param ccMailAccounts     抄送人
     * @param bccMailAccount     密送人
     * @param subject            主题
     * @param content            内容
     * @param filePath           附件路径
     * @param fileName           附件名
     */
    public static void sendMail(String receiveMailAccount, List<String> ccMailAccounts, String bccMailAccount,
                                String subject, String content, String filePath, String fileName) {

        try {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");// 使用的协议
            props.setProperty("mail.smtp.host", emailSMTPHost);// 发件人的邮箱的SMTP服务器地址
            props.setProperty("mail.smtp.auth", "true");// 需要请求认证;
            props.setProperty("mail.smtp.timeout", "60000");
            props.setProperty("mail.smtp.ssl.enable", "true");

            Session session = Session.getInstance(props);//得到会话对象实例
            session.setDebug(true);//是否打印详细日志
            MimeMessage message = createMimeMessage(session, receiveMailAccount, ccMailAccounts, bccMailAccount, subject, content, filePath, fileName);//获取邮件对象（封装了一个方法）
            Transport transport = session.getTransport();
            transport.connect(emailSMTPHost, "xxxx", senderCode);//连接发送人的邮箱账户
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
            // 7. 关闭连接
            transport.close();

            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("发送邮件失败");
        }
    }

    /**
     * 创建邮件
     *
     * @param session 会话
     * @param receiveMailAccount 收件人
     * @param ccMailAccounts 抄送人
     * @param bccMailAccount 密送人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件路径
     * @param fileName 附件名
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String receiveMailAccount, List<String> ccMailAccounts, String bccMailAccount,
                                                String subject, String content, String filePath, String fileName) throws Exception {

        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(SenderEmail, "发件人", "UTF-8"));
        // 3. 设置收件人、抄送人、密送人
        //MimeMessage.RecipientType.TO：收件类型；MimeMessage.RecipientType.CC：抄送类型；MimeMessage.RecipientType.BCC：密送类型
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "收件人", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(getAddress(ccMailAccounts), "抄送人", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(bccMailAccount, "密送人", "UTF-8"));
        // 4. Subject: 邮件主题
        message.setSubject(subject, "UTF-8");
        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(content, "text/html;charset=UTF-8");

        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart file = new MimeBodyPart();
        DataHandler handler = new DataHandler(new FileDataSource(filePath));
        file.setDataHandler(handler);
        //对文件名进行编码，防止出现乱码
        String document = MimeUtility.encodeWord(fileName, "utf-8", "B");
        file.setFileName(document);
        multipart.addBodyPart(file);
        message.setContent(multipart);
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }

    //邮箱地址转换
    private static String getAddress(List<String> mailList) throws AddressException {
        Address[] address = new InternetAddress[mailList.size()];
        for (int i = 0; i < mailList.size(); i++) {
            address[i] = new InternetAddress(mailList.get(i));
        }
        return Arrays.toString(address);
    }

}
