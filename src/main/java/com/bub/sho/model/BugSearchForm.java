package com.bub.sho.model;

import javax.validation.constraints.NotNull;

public class BugSearchForm {
	
	@NotNull(message="is required")
	private String keyWord;
	
	public BugSearchForm() {
		
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Override
	public String toString() {
		return "BugSearchForm [keyWord=" + keyWord + "]";
	}

	public BugSearchForm(@NotNull(message = "is required") String keyWord) {
		super();
		this.keyWord = keyWord;
	}
	
	

}
