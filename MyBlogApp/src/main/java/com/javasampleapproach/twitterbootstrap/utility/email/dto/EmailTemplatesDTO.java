package com.javasampleapproach.twitterbootstrap.utility.email.dto;

public class EmailTemplatesDTO {
	
	private int templateId;
	
	private String type;
	
	private String subject;
	
	private String bodyContent;
	
	private String inputType;
	
	private String fromId;

	private boolean isDeleted = false;
	
	/**
	 * @return the bodyContent
	 */
	public String getBodyContent() {
		return bodyContent;
	}

	/**
	 * @param bodyContent the bodyContent to set
	 */
	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}

	/**
	 * @return the fromId
	 */
	public String getFromId() {
		return fromId;
	}

	/**
	 * @param fromId the fromId to set
	 */
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	/**
	 * @return the inputType
	 */
	public String getInputType() {
		return inputType;
	}

	/**
	 * @param inputType the inputType to set
	 */
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the templateId
	 */
	public int getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
