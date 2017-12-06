package nju.blockbuster.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConfigClass extends WebMvcConfigurerAdapter {
    public static final String PATH = "/Users/john/Pictures/blockbuster/common/";
    public static final String AVATAR_PATH = "/Users/john/Pictures/blockbuster/avatar/";
    public static final String AVATAR_NAME = "default.jpg";

    //供客户端使用的url前缀
    public static final String URL = "http://8080/picture/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/picture/**").addResourceLocations("file:"+PATH);
        registry.addResourceHandler("/avatar/**").addResourceLocations("file:"+AVATAR_PATH);
        super.addResourceHandlers(registry);
    }
}
