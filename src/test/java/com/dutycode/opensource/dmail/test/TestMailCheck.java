package com.dutycode.opensource.dmail.test;

import org.junit.Test;

import com.dutycode.opensource.dmail.MailSetting;
import com.dutycode.opensource.dmail.util.MailCheckUtil;

public class TestMailCheck {

	@Test
	public void testMailCheck(){
		MailSetting ms = new MailSetting("testemail@dutycode.com", "1234Qwer", "25", "smtp.exmail.qq.com");
		String[] s = MailCheckUtil.removeUnvaliadEmail(new String[]{"zzh@dutycode.com", "530896339@qq.com"}, ms);
		System.out.println(s);
	}
}
