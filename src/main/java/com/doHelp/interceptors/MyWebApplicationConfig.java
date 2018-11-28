package com.doHelp.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebApplicationConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")// 拦截所有url
				.excludePathPatterns("/login", "/logout", "/toRegister", "/register", "/managerLogin" ,"/doRemarks", "/toManagerLogin","/static/**"
 							);// 不需要拦截的url
	}
  //  拦截器这里不能写成 "classpath:/static/**" 不然会没效果！！！
}	
