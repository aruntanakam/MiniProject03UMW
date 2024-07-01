package com.umw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.umw.commons.CommonUtils;
import com.umw.entity.UserInfo;
import com.umw.model.ActivateUserDetails;
import com.umw.model.LoginUserInput;
import com.umw.model.UserRegisterDetails;
import com.umw.repository.IUserManagementRepository;

import jakarta.mail.MessagingException;

@Service
public class UserManagementServiceImpl implements IUserManagementService {

	@Autowired
	private IUserManagementRepository repo;

	@Autowired
	private MailServiceImpl mailService;

	@Override
	public String registeruser(UserRegisterDetails details) throws MessagingException {

		UserInfo info = new UserInfo();

		BeanUtils.copyProperties(details, info);

		info.setStatus("inactive");
		info.setPassword(CommonUtils.generateTempPassword());

		UserInfo u = repo.save(info);

		if (u != null && u.getUserId() != null) {

			mailService.sendActivationMail(info);

			return "User Registered successfully with id " + u.getUserId();
		}

		return "user registration failed";
	}

	@Override
	public ActivateUserDetails getUserInfo(String mailId) {

		UserInfo info = repo.findByEmailId(mailId);

		if (info != null && info.getEmailId().equals(mailId)) {
			ActivateUserDetails details = new ActivateUserDetails();
			details.setEmailId(mailId);
			details.setTempPassword(info.getPassword());
			return details;
		} else {
			throw new RuntimeException("There is no user with mail Id:" + mailId);
		}

	}

	public String activateUser(ActivateUserDetails details) {
		UserInfo info = UserInfo.builder().emailId(details.getEmailId()).password(details.getTempPassword()).build();
		info.setEmailId(details.getEmailId());
		info.setPassword(details.getTempPassword());

		List<UserInfo> list = repo.findAll(Example.of(info));
		if (!CollectionUtils.isEmpty(list)) {
			info = list.get(0);
			info.setPassword(details.getPassword());
			info.setStatus("active");
			repo.save(info);
			return "user account activation successful and password also got updated";
		} else {
			return "user account activation failed";
		}
	}

	@Override
	public String validateUserLogin(LoginUserInput input) throws MessagingException {

		List<UserInfo> list = repo.findAll(
				Example.of(UserInfo.builder().emailId(input.getEmailId()).password(input.getPassword()).build()));

		if (!CollectionUtils.isEmpty(list)) {
			UserInfo info = list.get(0);
			if (info.getStatus().equals("active")) {
				return "Login successfull "+info.getName();

			}
			else
			{
				mailService.sendActivationMail(info);
				return " Login failed as user account status is inactive,please activate the account using the link sent to your registered mailId";
			}

		} else {
			return "Invalid emailId or password";
		}

		
	}

	@Override
	public String recoverPassword(String emailId) throws MessagingException {
		
		UserInfo info=repo.findByEmailId(emailId);
		
		if(info !=null && info.getEmailId().equals(emailId))
		{
		
			mailService.sendPasswordMail(info);
			
			return "password sent successfully to mail id:"+emailId;
		}
		
		else
		{
			return "There is no user with mail id:"+emailId;
		}
		
	}
	
	public List<UserRegisterDetails> getUsers()
	{
	   return repo.findAll().stream().map(info->{
		   UserRegisterDetails details=new UserRegisterDetails();
		   BeanUtils.copyProperties(info, details);
		   return details;
	   }).toList();
	}
	
	public UserRegisterDetails getUserDetails(Integer id)
	{
		Optional<UserInfo> opt=repo.findById(id);
		if(opt.isPresent())
		{
			 UserRegisterDetails details=new UserRegisterDetails();
			   BeanUtils.copyProperties(opt.get(), details);
			   return details;
		}
		else
		{
			throw new RuntimeException("There is no user with Id:"+opt.get().getUserId());
		}
	}
	
	public String deleteUser(Integer id)
	{
		Optional<UserInfo> opt=repo.findById(id);
		if(opt.isPresent())
		{
			 repo.deleteById(id);
			 return "user with Id:"+id+" deleted successfully";
		}
		else
		{
			throw new RuntimeException("There is no user with  Id:"+id);
		}
	}
	
	public String updateUser(UserRegisterDetails details)
	{
		Optional<UserInfo> opt=repo.findById(details.getUserId());
		if(opt.isPresent())
		{
			UserInfo info=opt.get();
			BeanUtils.copyProperties(details, info);
			 repo.save(info);
			 return "user with mailId:"+details.getEmailId()+" updated successfully";
			 
		}
		else
		{
			throw new RuntimeException("There is no user with Id:"+details.getUserId());
		}
	}
	
	public String alterStatus(Integer id,String status)
	{
		Optional<UserInfo> opt=repo.findById(id);
		if(opt.isPresent())
		{
			UserInfo info=opt.get();
			info.setStatus(status);
			repo.save(info);
			return "user with id:"+id+" status changed to:"+info.getStatus();
		} 
		else
		{
			throw new RuntimeException("There is no user with  Id:"+id);
		}
		}
		
	}


