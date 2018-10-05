package com.bub.sho.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bub.sho.entity.Status;
import com.bub.sho.service.UserService;

@Component
public class NametoStatusConvertor implements Converter<Object,Status> {
	
	@Autowired
    UserService userService;
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Status convert(Object element) {
        String name = (String)element;
        Status status= userService.getStatusbyName(name);
        System.out.println("status : "+status);
        return status;
    }

}
