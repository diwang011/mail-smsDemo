package com.next.demo.mail;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendFileEmail {
	public static void main(String[] args) throws GeneralSecurityException {
		String userName = "xxx@qq.com";
		String password = "xxx";
		String from = "xxx@qq.com";
		String server = "localhost";
		String port = "smtp.qq.com";
		String to = "xxx@163.com";
		String bcc = "xxx@163.com";
		String subject = "test...";
		String content = "<h1>content...</h1>";
		// 指定发送邮件的主机为 smtp.qq.com
		String host = "smtp.qq.com"; // QQ 邮件服务器
		// 获取系统属性
		Properties properties = System.getProperties();
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password); // 发件人邮件用户名、密码
			}
		});
		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);
			// Set From: 头部头字段
			message.setFrom(new InternetAddress(from));
			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
			// Set Subject: 头部头字段
			message.setSubject("This is the Subject Line!");
			// 设置消息体
			message.setText("This is actual message");
			message.setContent(content, "text/html; charset=utf-8");
			// 发送消息
			Transport.send(message);
			System.out.println("Sent message successfully....from runoob.com");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
}