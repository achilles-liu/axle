package org.axle.core;

/**
 * axle-core
 * <description>extract response information.</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
public class ApiReceipt {
	/**
	 * 0 - success
	 * 1 - failure
	 */
	private int code;
	/**
	 * response's message
	 */
	private String message;
	/**
	 * response's data
	 */
	private Object data;
	
	private ApiReceipt(Builder builder) {
		this.code = builder.code;
		this.message = builder.message;
		this.data = builder.data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public static class Builder {
		private int code;
		private String message;
		private Object data;
		
		public Builder code(int code) {
			this.code = code;
			return this;
		}
		
		public Builder message(String message) {
			this.message = message;
			return this;
		}
		
		public Builder data(Object data) {
			this.data = data;
			return this;
		}
		
		public ApiReceipt builder() {
			return new ApiReceipt(this);
		}
	}
}
