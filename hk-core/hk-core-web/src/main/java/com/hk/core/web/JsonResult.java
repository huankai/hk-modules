package com.hk.core.web;

import com.hk.commons.util.StringUtils;

/**
 * Json返回结果
 * 
 * @author huangkai
 * @date 2017年9月27日上午11:09:08
 */
public final class JsonResult {

	public enum Status {

		SUCCESS(10200, "请求成功"),

		FAILURE(-1, "请求失败"),

		NOT_FOUND(10404, "访问资源不存在"),

		SERVER_ERROR(10500, "未知错误");

		private int status;

		private String message;

		Status(int status, String message) {
			this.status = status;
			this.message = message;
		}

		public int getStatus() {
			return status;
		}

		public String getMessage() {
			return message;
		}
	}

	/**
	 * 返回数据
	 */
	private Object data;

	/**
	 * 返回状态
	 */
	private Status status;

	/**
	 * 返回消息信息
	 */
	private String message;

	public JsonResult() {
		this(Status.SUCCESS);
	}

	public JsonResult(boolean success) {
		this(success ? Status.SUCCESS : Status.FAILURE);
	}

	public JsonResult(boolean success, String message) {
		this(success ? Status.SUCCESS : Status.FAILURE, message);
	}

	public JsonResult(Object data) {
		this(Status.SUCCESS, data);
	}

	public JsonResult(boolean success, Object data) {
		this(success ? Status.SUCCESS : Status.FAILURE, null, data);
	}

	public JsonResult(Status status) {
		this(status, null, null);
	}

	public JsonResult(Status status, String message) {
		this(status, message, null);
	}

	public JsonResult(Status status, Object data) {
		this(status, null, data);
	}

	public JsonResult(Status status, String message, Object data) {
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return StringUtils.isEmpty(message) ? status.getMessage() : message;
	}

	public int getStatus() {
		return status.getStatus();
	}

}
