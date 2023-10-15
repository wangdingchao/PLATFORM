package com.example.platform.utils;



import com.example.platform.controller.req.ReqInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @author liujiaming
 */
//@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//    @Autowired
    ReqInterceptor reqInterceptor;


    // 暂时只给人事档案提供
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(reqInterceptor).addPathPatterns(
                "/employeeFile/**",
                "/recruiters/**",
                "/positionLevel/**",
                "/empValues/**",
                "/dealer/fine/**",
                "/dealer/position/**",
                "/dealer/organization/**",
                "/user/**",
                "/dealer/employee/**",
                "/dealer/salaryFile/**",
                "/dealer/positionSalary/**"
        );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        // swagger  静态资源放行
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
