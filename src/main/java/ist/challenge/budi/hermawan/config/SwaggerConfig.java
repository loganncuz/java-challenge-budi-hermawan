package ist.challenge.budi.hermawan.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.List;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig implements WebMvcConfigurer {
    @Autowired
    private ObjectMapper objectMapper;// created elsewhere
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().filter(c -> c instanceof MappingJackson2HttpMessageConverter).forEach(c -> {
            ((MappingJackson2HttpMessageConverter) c).setObjectMapper(this.objectMapper);
        });
    }
    @Bean
    public Docket api() {
        System.out.println("Docket");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ist.challenge.budi.hermawan"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                ;
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"Spring Boot REST API for Documentation\"")
                .version("1.0.0")
//                .license("Apache License Version 2.0")
//                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Logan Ncuz", "", "logan_ncuz@yahooo.co.id"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("**/**")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/swagger-ui/")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");


    }




}
