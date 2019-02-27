package com.wechat.oauth.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.oauth.bean.OAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth2")
public class OAuthController {
	
	@RequestMapping("/authorize")
	public String oAuth(HttpServletRequest request, HttpServletResponse response, OAuth oAuth, @CookieValue(value = "redirect_uri", required = false) String backUrl) throws UnsupportedEncodingException {
		oAuth.setProtocol(request.getScheme());
		if("".equals(oAuth.getCode())) {
			String authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
			StringBuilder options = new StringBuilder();
			options.append(authUrl);
			options.append("?appid=").append(oAuth.getAppid());
			options.append("&redirect_uri=").append(URLEncoder.encode(oAuth.getProtocol() + "://" + request.getServerName() + request.getRequestURI(), "UTF-8"));
			options.append("&response_type=code");
			options.append("&scope=").append(oAuth.getScope());
			options.append("&state=").append(oAuth.getState());
			options.append("#wechat_redirect");
			Cookie cookie = new Cookie("redirect_uri", oAuth.getRedirect_uri());
			cookie.setPath("/");
			cookie.setDomain(getDomain(request));
			cookie.setMaxAge(60);
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
			return "redirect:" + options;
		}else {
			if(backUrl != null) {
				backUrl = URLDecoder.decode(backUrl, "UTF-8");
				String link = backUrl.contains("?") ? "&" : "?";
				return "redirect:" + backUrl + link + "code=" + oAuth.getCode() + "&state=" + oAuth.getState();
			}else {
				return null;
			}
		}
	}
	
	private String getDomain(HttpServletRequest request) {
		String serverName = request.getServerName();
		if(serverName.contains("www.")) {
			return serverName.substring(4);
		}
		return serverName;
	}
}
