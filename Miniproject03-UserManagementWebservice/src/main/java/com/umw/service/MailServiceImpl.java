package com.umw.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.umw.entity.UserInfo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;

@Service
@ConfigurationProperties(prefix="spring.mail")
@Data
public class MailServiceImpl {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine engine;
	
	private String username;
	
	public void sendActivationMail(UserInfo info) throws MessagingException
	{
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		helper.setTo(info.getEmailId());
		helper.setFrom(username);
		Context context=new Context();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", info.getName());
		map.put("emailId", info.getEmailId());
		map.put("link", "http://localhost:5000/umw/api/activationDetails/"+info.getEmailId());
		context.setVariables(map);
		helper.setText(engine.process("ActivationMailTemplate.html",context),true);
		helper.setSubject("User Activation mail");
		mailSender.send(message);
	}
	public void sendPasswordMail(UserInfo info) throws MessagingException
	{
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		helper.setTo(info.getEmailId());
		helper.setFrom(username);
		Context context=new Context();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", info.getName());
		map.put("password", info.getPassword());
		context.setVariables(map);
		helper.setText(engine.process("RecoverPasswordMailTemplate.html",context),true);
		helper.setSubject("Recover Password Mail");
		mailSender.send(message);
		
	}

}
