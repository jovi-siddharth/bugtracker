package com.bub.sho.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bub.sho.entity.Priority;
import com.bub.sho.entity.Role;
import com.bub.sho.service.UserService;

@Component
public class RoleidtoRoleConvertor implements Converter<Object,Role> {
	
	@Autowired
    UserService userService;
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Role convert(Object element) {
        String name = (String)element;
        Role role= userService.getRolebyName(name);
        System.out.println("role : "+role);
        return role;
    }

}
