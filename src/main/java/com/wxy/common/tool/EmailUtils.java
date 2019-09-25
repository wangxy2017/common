package com.wxy.common.tool;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @Author wxy
 * @Date 19-7-29 上午10:06
 * @Description TODO
 **/
public class EmailUtils {

    /**
     * 文件分割符
     */
    public static final String FILE_PARTITION = "----------";

    /**
     * 发送邮件
     *
     * @param path     多个邮箱英文逗号“,”隔开
     * @param title    邮件主题
     * @param content  内容
     * @param fileList {"filename----------realpath"}
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static void sendEmail(String path, String title, String content, String[] fileList) throws MessagingException, UnsupportedEncodingException {//path是指你要发给哪个邮箱号，title是指你的邮件的标题。msg是指你的邮件的内容。

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议，即：邮件协议
        properties.put("mail.smtp.host", "smtp.sina.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("dujs_work@sina.com"));
        // 设置收件人邮箱地址
        //message.setRecipient(Message.RecipientType.TO, new InternetAddress(path));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(path));//多个收件人
        // 设置邮件标题
        message.setSubject(title);
        // 设置邮件内容
        if (fileList != null) {
            message.setContent(addFiles(content, fileList));
        } else {
            message.setText(content);
        }
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("dujs_work@sina.com", "3d72cd526b95df2f");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码（你可以进入你的邮箱的设置里面查看）
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    /**
     * 添加附件
     *
     * @param content
     * @param fileList
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    private static MimeMultipart addFiles(String content, String[] fileList) throws MessagingException, UnsupportedEncodingException {
        // linux 下 程序使用javamail1.4.4 发邮件带附件，若附件名过长，会被截断。
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
        //===================================添加附件和内容start==========================================
        MimeMultipart allPart = new MimeMultipart("mixed");//创建一个MimeMultipart组件，用来装载附件
        for (String fileInfo : fileList) {
            String[] str = fileInfo.split(FILE_PARTITION);
            String fileName = str[0];//附件名称
            String filePath = str[1];//附件绝对路径
            MimeBodyPart attachmentPart = new MimeBodyPart();//装载邮件附件
            FileDataSource fds = new FileDataSource(filePath);//根据附件绝对路径，得到数据源
            attachmentPart.setDataHandler(new DataHandler(fds));//得到附件本身，并装载进入attachmentPart(MimeBodyPart)
            String filename_new = MimeUtility.encodeText(fileName);
            filename_new = filename_new.replace("\\r", "").replace("\\n", "");
            attachmentPart.setFileName(filename_new);//得到文件名，同样装载进入attachmentPart(MimeBodyPart)，并且对文件名进行编码处理
            allPart.addBodyPart(attachmentPart);//附件也变成邮件的内容
        }
        MimeBodyPart bodyContent = new MimeBodyPart();
        bodyContent.setText(content);
        allPart.addBodyPart(bodyContent);
        return allPart;
        //====================================添加附件和内容end=========================================
    }
}
