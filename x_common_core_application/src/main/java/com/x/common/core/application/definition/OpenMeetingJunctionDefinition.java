package com.x.common.core.application.definition;

import com.x.base.core.gson.GsonPropertyObject;

public class OpenMeetingJunctionDefinition extends LoadableDefinition {

	public static OpenMeetingJunctionDefinition INSTANCE;

	public static final String FILE_NAME = "openMeetingJunctionDefinition.json";

	private String server;
	private Integer port;
	private String host;
	private String user;
	private String pass;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
