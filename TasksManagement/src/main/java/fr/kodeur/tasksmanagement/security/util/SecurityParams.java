package fr.kodeur.tasksmanagement.security.util;

public interface SecurityParams {

	public static final String JWT_HEADER_NAME = "Authorization";
	public static final String JWT_SECRET = "mySecret";
	public static final long JWT_EXPIRATION_TIME = 10*24*3600*1000;
	public static final String JWT_HEADER_PREFIX = "Bearer ";
}
