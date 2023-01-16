package br.com.alura.mvc.mudi;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.alura.mvc.mudi.interceptor.InterceptadorDeAcessos;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	WebRequestInterceptor  web = new WebRequestInterceptor() {
		
		@Override
		public void preHandle(WebRequest request) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void postHandle(WebRequest request, ModelMap model) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterCompletion(WebRequest request, Exception ex) throws Exception {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new InterceptadorDeAcessos(web)).addPathPatterns("/**");
	}
}
