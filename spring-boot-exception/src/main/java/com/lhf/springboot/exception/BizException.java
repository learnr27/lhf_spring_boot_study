package com.lhf.springboot.exception;

/**
 * @ClassName: BizException
 * @Author: liuhefei
 * @Description: 业务异常类
 * @Date: 2019/7/26 16:33
 */
public class BizException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(BaseErrorInfoInterface errorInfoInterface) {
        super(errorInfoInterface.getErrorCode());
        this.errorCode = errorInfoInterface.getErrorCode();
        this.errorMsg = errorInfoInterface.getErrorMsg();
    }

    public BizException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getErrorCode(), cause);
        this.errorCode = errorInfoInterface.getErrorCode();
        this.errorMsg = errorInfoInterface.getErrorMsg();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMessage() {
        return errorMsg;
    }

    /**
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
