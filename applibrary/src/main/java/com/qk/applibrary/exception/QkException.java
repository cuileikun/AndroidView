package com.qk.applibrary.exception;

/**
 * 异常父类
 */
public class QkException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public QkException() {
		super();
	}

	public QkException(String msg) {
		super(msg);
	}

	public QkException(Throwable ex) {
		super(ex);
	}

	public QkException(String msg, Throwable ex) {
		super(msg,ex);
	}

}
