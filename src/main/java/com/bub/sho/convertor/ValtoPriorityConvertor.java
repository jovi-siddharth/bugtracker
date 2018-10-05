package com.bub.sho.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bub.sho.entity.Priority;
import com.bub.sho.service.UserService;

@Component
public class ValtoPriorityConvertor implements Converter<Object,Priority> {
	
	@Autowired
    UserService userService;
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Priority convert(Object element) {
        Integer val = Integer.parseInt((String)element);
        Priority priority= userService.getPrioritybyVal(val);
        System.out.println("priority : "+priority);
        return priority;
    }

}
