package com.wechat.oauth.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.oauth.bean.OAuth;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth2")
public class OAuthController {
	/**
	 * 微信授权地址
	 */
	private static final String AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";

	@RequestMapping("/authorize")
	public String oAuth(HttpServletRequest request, HttpServletResponse response, OAuth oAuth, @CookieValue(value = "redirect_uri", required = false) String backUrl) throws UnsupportedEncodingException {
		//设置访问协议
		oAuth.setProtocol(request.getScheme());
		//判断参数中是否包含code值,有则视为微信授权后的成功回调,否则为首次访问
		if(!StringUtils.hasText(oAuth.getCode())) {
			//拼接授权地址及参数，由可获取授权的服务器访问微信授权，成功获取code值后，再回传给真正需要授权的地址
			StringBuilder options = new StringBuilder();
			options.append(AUTH_URL);
			options.append("?appid=").append(oAuth.getAppid());
			//此处的地址依然为当前Controller的路径,让微信授权成功后,将code值回传给此处。
			options.append("&redirect_uri=").append(URLEncoder.encode(oAuth.getProtocol() + "://" + request.getServerName() + request.getRequestURI(), "UTF-8"));
			options.append("&response_type=").append(oAuth.getResponse_type());
			options.append("&scope=").append(oAuth.getScope());
			options.append("&state=").append(oAuth.getState());
			options.append("#wechat_redirect");
			//将用户正式需要授权的回调地址保存在cookie中,获取code值成功后回调
			Cookie cookie = new Cookie("redirect_uri", oAuth.getRedirect_uri());
			cookie.setPath("/");
			cookie.setDomain(getDomain(request));
			cookie.setMaxAge(60);
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
			return "redirect:" + options;
		}else {
			//如果参数中含有code值，进行如下操作
			if(backUrl != null) {
				backUrl = URLDecoder.decode(backUrl, "UTF-8");
				String link = backUrl.contains("?") ? "&" : "?";
				//重定向值需要获取授权的链接,并将code值传入
				return "redirect:" + backUrl + link + "code=" + oAuth.getCode() + "&state=" + oAuth.getState();
			}else {
				return null;
			}
		}
	}

	/**
	 * 获取服务域名
	 * @param request
	 * @return
	 */
	private String getDomain(HttpServletRequest request) {
		String serverName = request.getServerName();
		if(serverName.contains("www.")) {
			return serverName.substring(4);
		}
		return serverName;
	}
}
