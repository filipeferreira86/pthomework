package com.playtech.support;

public class DataC {

	private String lang;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String comments;
	private String submitLocator;
	private String message;
	private String clearSelectionLocator;
	private String clearFormLocator;
	private String emailMessage;
	private String allertClearLocator;
	
	public DataC(String lang,  String name, String email, String address, String phone, String comments,
			String submitLocator, String clearSelectionLocator, String message, String clearFormLocator, String emailMessage, String allertClearLocator) {
		super();
		this.lang = lang;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.comments = comments;
		this.submitLocator = submitLocator;
		this.clearSelectionLocator = clearSelectionLocator;
		this.message = message;
		this.clearFormLocator = clearFormLocator; 
		this.emailMessage = emailMessage;
		this.allertClearLocator = allertClearLocator;;
		
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getComments() {
		return comments;
	}

	public String getSubmitLocator() {
		return submitLocator;
	}
	
	public String getClearSelectionLocator() {
		return clearSelectionLocator;
	}

	public String getMessage() {
		return message;
	}

	public String getLang() {
		return lang;
	}

	public String getName() {
		return name;
	}

	public String getClearFormLocator() {
		return clearFormLocator;
	}

	public Object getEmailMessage() {
		return emailMessage;
	}

	public String getAllertClearLocator() {
		return allertClearLocator;
	}
}
