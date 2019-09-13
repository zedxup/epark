package com.udsp.sdc.icbc.comm.exception;

/**
 * 自定义异常
 * 
 * @author huangjl
 *
 */
public class FrameException extends RuntimeException {

	private static final long serialVersionUID = 8635877082160088777L;

	/**
	 * 错误代码
	 */
	private final ExceptionCode code;

	/**
	 * 额外的异常信息
	 */
	private String exMsg;

	/**
	 * 根据code构造异常
	 * 
	 * @param code
	 *            异常项
	 */
	public FrameException(ExceptionCode code) {
		super(code.getName());
		this.code = code;
	}

	/**
	 * 根据code和额外信息构造异常
	 * 
	 * @param code
	 *            异常项
	 * @param exMsg
	 *            额外异常信息
	 */
	public FrameException(ExceptionCode code, String exMsg) {
		super(code.getName());
		this.code = code;
		this.exMsg = exMsg;
	}

	public void setExMsg(String exMsg) {
		this.exMsg = exMsg;
	}

	public String getExMsg() {
		return this.exMsg;
	}

	public ExceptionCode getCode() {
		return this.code;
	}

	@Override
	public String toString() {
		final String localMsg = "(" + code.getIndex() + ":" + code.getName() + (exMsg != null ? " - " + exMsg : "")
				+ ")";
		return super.toString() + localMsg;
	}
}
