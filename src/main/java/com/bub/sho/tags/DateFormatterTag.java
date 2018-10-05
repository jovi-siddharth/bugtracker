package com.bub.sho.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DateFormatterTag extends SimpleTagSupport {
	
	private String format;
	private Date date;
	private SimpleDateFormat simpleDateFormat;
	
	public DateFormatterTag() {
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("format is:" + format);
		System.out.println("Date is:" + date);
		try {
		simpleDateFormat = new SimpleDateFormat(format);
		getJspContext().getOut().write(simpleDateFormat.format(date));
		}
		catch(Exception e) {
			e.printStackTrace();
			// stop page from loading further by throwing SkipPageException
			throw new SkipPageException("Exception in formatting " + date
					+ " with format " + format);
		}
	}
		
		
}
