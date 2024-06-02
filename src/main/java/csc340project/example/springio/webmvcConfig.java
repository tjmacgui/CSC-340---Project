package csc340project.example.springio;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webmvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**") // URL pattern to match CSS files
                .addResourceLocations("classpath:/static/css/") // Location of CSS files in the classpath
                .setCachePeriod(3600) // Cache period in seconds (optional)
                .resourceChain(true); // Enable resource chain optimization (optional)
    }
}
