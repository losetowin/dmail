package com.dutycode.opensource.dmail;

public class MailSetting {

	public MailSetting() {
	};

	public MailSetting(String username, String password, String port,
			String host, String mailFromUsername) {
		this.host = host;
		this.mailFromUserName = mailFromUsername;
		this.password = password;
		this.port = port;
		this.username = username;
	}

	public MailSetting(String username, String password, String port,
			String host) {
		this(host, password, port, host, null);
	}

	private String username;
	private String password;
	private String port;
	private String host;
	private String mailFromUserName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getMailFromUserName() {
		return mailFromUserName;
	}

	public void setMailFromUserName(String mailFromUserName) {
		this.mailFromUserName = mailFromUserName;
	}

}
