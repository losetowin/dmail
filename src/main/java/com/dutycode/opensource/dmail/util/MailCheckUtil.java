package com.dutycode.opensource.dmail.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import com.dutycode.opensource.dmail.MailSetting;
import com.sun.mail.smtp.SMTPTransport;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64EncoderStream;

public class MailCheckUtil {
	public static String[] removeUnvaliadEmail(String[] addresses, MailSetting mailSetting) {
		List<String> list = Arrays.asList(addresses);
		return removeUnvaliadEmail(list, mailSetting);
	}

	public static String[] removeUnvaliadEmail(List<String> addresses, MailSetting mailSetting) {

		ArrayList<String> validateAddresses = new ArrayList<String>();

		SMTPTransport smptTrans = null;

		try {
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
			Properties properties = new Properties();
			properties.put("mail.smtp.host", mailSetting.getHost());
			properties.put("mail.smtp.port", mailSetting.getPort());
			Session sendSession = Session.getDefaultInstance(properties);
			smptTrans = (SMTPTransport) sendSession.getTransport("smtp");
			smptTrans.connect(mailSetting.getHost(),
					Integer.valueOf(mailSetting.getPort()),
					mailSetting.getUsername(), mailSetting.getPassword());
			// 发送EHLO命令
			int ehlocode = smptTrans.simpleCommand("EHLO "
					+ mailSetting.getHost());
			if (ehlocode != 250 && ehlocode != 251) {
				// ehlo失败，返回空值
				return null;
			}
			int authcode = smptTrans.simpleCommand("AUTH LOGIN");
			if (authcode != 334) {
				return null;
			}
			int c1 = smptTrans.simpleCommand(new String(BASE64EncoderStream
					.encode(ASCIIUtility.getBytes(mailSetting.getUsername()))));
			if (c1 != 334) {
				return null;
			}
			int c2 = smptTrans.simpleCommand(new String(BASE64EncoderStream
					.encode(ASCIIUtility.getBytes(mailSetting.getPassword()))));
			if (c2 != 235) {
				return null;
			}
			// 发送mail from命令
			String mailFromCmd = "MAIL FROM:<" + mailSetting.getUsername()
					+ ">";
			int code = smptTrans.simpleCommand(mailFromCmd);
			if (code != 250 && code != 251) {
				System.out.println("连接smtp失败");
				return null;
			} else {
				for (String addr : addresses) {
					String rcptCmd = "RCPT TO:<" + addr + ">";
					int rcptcode = smptTrans.simpleCommand(rcptCmd);
					if (rcptcode != 250 && rcptcode != 251) {
						System.out.println("邮箱不存在" + addr);
					} else {
						validateAddresses.add(addr);
					}
				}

			}

			// 断开与服务器之间链接
			// String quitCmd = "QUIT ";
			// int quitCode = smptTrans.simpleCommand(quitCmd);

			// System.out.println("quit-->" + quitCode);

			smptTrans.close();

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		String[] result = validateAddresses
				.toArray(new String[validateAddresses.size()]);
		return result;

	}

}
