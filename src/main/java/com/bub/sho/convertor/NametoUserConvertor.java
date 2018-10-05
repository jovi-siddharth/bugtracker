package com.bub.sho.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bub.sho.entity.User;
import com.bub.sho.service.UserService;

@Component
public class NametoUserConvertor implements Converter<Object,User> {
	
	@Autowired
    UserService userService;
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public User convert(Object element) {
        String name = (String)element;
        User user= userService.findByUserName(name);
        System.out.println("user : "+user);
        return user;
    }

}
