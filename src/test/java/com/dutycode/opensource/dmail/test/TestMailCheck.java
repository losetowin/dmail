package com.dutycode.opensource.dmail.test;

import org.junit.Test;

import com.dutycode.opensource.dmail.MailSetting;
import com.dutycode.opensource.dmail.util.MailCheckUtil;

public class TestMailCheck {

	@Test
	public void testMailCheck(){
		MailSetting ms = new MailSetting("test@example.com", "pwd", "465", "host");
		try {
			String[]	s = MailCheckUtil.removeUnvaliadEmail(new String[]{"zzh@dutycode.com", "530896339@qq.com"}, ms);
			for (String ss : s){
				System.out.println(ss);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
