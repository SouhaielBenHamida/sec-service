package com.sbh.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbh.dao.AppRoleRepository;
import com.sbh.dao.AppUserRepository;
import com.sbh.entities.AppRole;
import com.sbh.entities.AppUser;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{


	private AppUserRepository appUserRepository;

	private AppRoleRepository appRoleRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.appUserRepository = appUserRepository;
		this.appRoleRepository = appRoleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public AppUser saveUser(String username, String password, String confirmedPassword) {
		AppUser appUser = appUserRepository.findByUsername(username);
		if(appUser != null) throw new RuntimeException("User already existe !");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirme your password !");
		appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setActived(true);
		appUser.setPassword(bCryptPasswordEncoder.encode(password));
		appUserRepository.save(appUser);
		addRoleToUser(username, "USER");
		return appUser;
	}

	@Override
	public AppRole saveRole(AppRole appRole) {
		return appRoleRepository.save(appRole);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		AppUser appUser = appUserRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findByRoleName(rolename);
		appUser.getRoles().add(appRole);
		
	}

}
