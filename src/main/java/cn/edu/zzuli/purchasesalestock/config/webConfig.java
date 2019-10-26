package cn.edu.zzuli.purchasesalestock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class webConfig implements WebMvcConfigurer {
    /*
     * url路径映射
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
        registry.addViewController("/details.html").setViewName("details.html");
    }

    /*
     *  资源文件映射
     *
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /*
     * 拦截器配置
     * 先留着
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        // springboot2.0之前静态资源都是 配置好的
        // 但是到了 springboot2.0的时候 一些配置失效了 还是自己配置 一下
        //registry.addInterceptor(new LoginUserInterceptor()).addPathPatterns("/**").excludePathPatterns("/",
        //		"/login.html", "/login", "/login/user", "/static/**", "/i18ntest", "/i18n/**");

        //registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * 允许跨域访问,
     * 如果前端不实用框架，该配置注掉
     * @param registry

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(false).maxAge(3600);
    }
     */
}
