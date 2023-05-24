package com.yuanlrc.campus_market.interceptor.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.yuanlrc.campus_market.bean.CodeMsg;
import com.yuanlrc.campus_market.entity.admin.Menu;
import com.yuanlrc.campus_market.entity.admin.User;
import com.yuanlrc.campus_market.util.MenuUtil;
import com.yuanlrc.campus_market.util.SessionUtil;
import com.yuanlrc.campus_market.util.StringUtil;

/**
 * 权限统一管理拦截器
 * @author Administrator
 *
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor{

	private Logger log = LoggerFactory.getLogger(AuthorityInterceptor.class);
	
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		String requestURI = request.getRequestURI();
		User loginedUser = SessionUtil.getLoginedUser();
		log.info("进入权限控制拦截器" + requestURI);
		List<Menu> authorities = loginedUser.getRole().getAuthorities();
		if(!MenuUtil.isExistUrl(requestURI, authorities)){
			//进入这里，表示权限不存在，首先判断是否是ajax请求
			if(StringUtil.isAjax(request)){
				//表示是ajax请求
				try {
					log.info("该请求无权限，已ajax方式返回提示，url=" + requestURI);
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(JSON.toJSONString(CodeMsg.ADMIN_NO_RIGHT));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
			//说明是普通的请求，可直接重定向到无权限提示页面
			try {
				log.info("该请求无权限，重定向到无权限提示页面，url=" + requestURI);
				response.sendRedirect("/system/no_right");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		log.info("该请求符合权限要求，放行" + requestURI);
		return true;
	}
}
