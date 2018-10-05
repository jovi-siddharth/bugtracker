package com.bub.sho.tags;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TSFormatter extends SimpleTagSupport {
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public TSFormatter() {
		
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		try {
		getJspContext().getOut().write(new Date(Long.parseLong(value)).toString());
		}
		catch(Exception e) {
			e.printStackTrace();
			// stop page from loading further by throwing SkipPageException
			throw new SkipPageException("Exception in formatting " + value);
		}
	}

}
