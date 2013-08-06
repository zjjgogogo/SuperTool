package com.jamesjaw.supertool.data;

public class MessageItem implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	private int id;

	private int type;

	private int protocol;

	private String phone;

	private String body;
	
	private String date;
	
	private int read;
	
	public MessageItem()
	{
		
	}
 
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



	public int getProtocol() {
		return protocol;
	}



	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public int getRead() {
		return read;
	}



	public void setRead(int read) {
		this.read = read;
	}



	public String toString()
	{
		return

		"id = " + id + ";" +

		"type = " + type + ";" +

		"protocol = " + protocol + ";" +

		"phone = " + phone + ";" +

		"body = " + body +
		
		"read = " + read +
		
		"date = " + date;

	}

}