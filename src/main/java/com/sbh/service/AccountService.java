package com.sbh.service;

import com.sbh.entities.AppRole;
import com.sbh.entities.AppUser;

public interface AccountService {

	public AppUser saveUser(String username, String password, String confirmedPassword);
	public AppRole saveRole(AppRole appRole);
	public AppUser loadUserByUsername(String username);
	public void addRoleToUser(String username, String rolename);
}
