package com.sbh.sec;

public interface SecurityParam {

	public static final String JWT_HEADER_NAME ="Authorization";
	public static final String SECRET ="sou@gmail.com";
	public static final long EXPIRATION = 10*24*3600*1000;
	public static final String HEADER_PREFIX ="Bearer ";
}
