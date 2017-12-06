package nju.blockbuster.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConfigClass extends WebMvcConfigurerAdapter {
    public static final String PATH = "/Users/gyue/Pictures/MyPicture/";

    //供客户端使用的url前缀
    public static final String URL = "/picture/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/picture/**").addResourceLocations("file:"+PATH);
        super.addResourceHandlers(registry);
    }
}
