package com.wechat.oauth.bean;

/**
 * 微信授权接口
 * @author malin
 */
public class OAuth {

	private String appid;

	private String scope;

	private String state;

	private String code;

	private String redirect_uri;

	private String protocol;

	public OAuth(){
		appid = "";
		scope = "";
		state = "";
		code = "";
		redirect_uri = "";
		protocol = "";
	}

	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

}
