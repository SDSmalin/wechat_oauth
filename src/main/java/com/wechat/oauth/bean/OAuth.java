package com.wechat.oauth.bean;

/**
 * 微信授权接口
 * @author malin
 */
public class OAuth {
	/**
	 * 公众号的唯一标识
	 */
	private String appid;
	/**
	 * 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
	 * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，
	 * 也能获取其信息 ）
	 */
	private String scope;
	/**
	 * 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 */
	private String state;
	/**
	 * 用于接收微信授权成功后返回的code值
	 */
	private String code;
	/**
	 * 授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
	 */
	private String redirect_uri;
	/**
	 * 协议
	 */
	private String protocol;
	/**
	 * 返回类型，请填写code
	 */
	private String response_type;

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

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}
}
