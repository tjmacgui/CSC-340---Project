package csc340project.example.springio;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAsync
public class webmvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**") // URL pattern to match CSS files
                .addResourceLocations("classpath:/static/") // Location of CSS files in the classpath
                .setCachePeriod(3600) // Cache period in seconds (optional)
                .resourceChain(true); // Enable resource chain optimization (optional)
    }
}
