package com.world.exception;


import com.world.enums.ErrorType;
import com.world.vo.ErrorVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
	private final Integer status;
	private final String code;
	private final ErrorVo error;
	
	protected BaseException(Integer status, String code, String message, ErrorType errorType) {
		super(message);
		this.status = status;
		this.code = code;
		this.error = new ErrorVo().setMessage(message).setCode(errorType.getCode());
	}
	
	protected BaseException(ErrorType errorType, String message) {
		this(errorType.getStatus().value(), errorType.name(), message, errorType);
	}
	
	protected BaseException(ErrorType errorType) {
		this(errorType.getStatus().value(), errorType.name(), errorType.getMessage(), errorType);
	}
	
	protected BaseException(String message) {
		super(message);
		this.status = null;
		this.code = null;
		this.error = null;
	}
	
	public static BaseException create(String message) {
		return new BaseException(message);
	}
	
	public static BaseException create(ErrorType errorType) {
		return new BaseException(errorType);
	}
	
	public static BaseException create(ErrorType errorType, String message) {
		return new BaseException(errorType, message);
	}
}
