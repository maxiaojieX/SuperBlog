package com.ma.mail;

public class SendMail {
	
	public void sendMail(String email,String code) {
		MailSenderInfo mailInfo = new MailSenderInfo();      
	     mailInfo.setMailServerHost("smtp.163.com");      
	     mailInfo.setMailServerPort("25");      
	     mailInfo.setValidate(true);      
	     mailInfo.setUserName("15239131507@163.com");      
	     mailInfo.setPassword("ma000000");//您的邮箱密码      
	     mailInfo.setFromAddress("15239131507@163.com");      
	     mailInfo.setToAddress(email);    
	     mailInfo.setSubject("小杰论坛");      
	     mailInfo.setContent("【小杰论坛】您的验证码为:"+code); 
	        //这个类主要来发送邮件     
	     SimpleMailSender sms = new SimpleMailSender();     
	     sms.sendTextMail(mailInfo);//发送文体格式
	}

}
