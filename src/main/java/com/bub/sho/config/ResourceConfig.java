package com.bub.sho.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bub.sho.convertor.NametoStatusConvertor;
import com.bub.sho.convertor.NametoUserConvertor;
import com.bub.sho.convertor.RoleidtoRoleConvertor;
import com.bub.sho.convertor.ValtoPriorityConvertor;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
	
	@Autowired
	NametoUserConvertor nametoUserConvertor;
	
	@Autowired
	NametoStatusConvertor nametoStatusConvertor;
	
	@Autowired
	ValtoPriorityConvertor valtoPriorityConvertor;
	
	@Autowired
	RoleidtoRoleConvertor roleidtoRoleConvertor;
	
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/theme1/");
    }
	
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(nametoUserConvertor);
        registry.addConverter(nametoStatusConvertor);
        registry.addConverter(valtoPriorityConvertor);
        registry.addConverter(roleidtoRoleConvertor);
    }
	
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

}
