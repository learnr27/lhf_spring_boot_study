package com.lhf.springboot.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @ClassName: ExceptionUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/2 15:49
 */
public class ExceptionUtil extends RuntimeException{
    private String[] args;
    private String code;
    private String errMsg;
    private boolean isExcetion;
    private ExceptionUtil exceptionUtil;

    public ExceptionUtil(String code) {
        super(code);
        this.checkCodeNull(code);
        this.code = code;
    }

    public ExceptionUtil(String code, String[] args) {
        super(code + (args == null ? "" : ":" + Arrays.asList(args)));
        this.checkCodeNull(code);
        this.args = args;
        this.code = code;
    }

    public ExceptionUtil(String code, Throwable e) {
        this(code, (String[])null, e);
    }

    public ExceptionUtil(String code, String[] args, Throwable e) {
        super(code + (args == null ? "" : ":" + Arrays.asList(args)), e);
        this.checkCodeNull(code);
        this.args = args;
        this.code = code;
        if (e instanceof ExceptionUtil) {
            ExceptionUtil currException = (ExceptionUtil)e;
            if ((this.exceptionUtil = currException.getFirstBaseRunTimeException()) == null) {
                this.exceptionUtil = currException;
            }

            this.isExcetion = this.exceptionUtil.isExcetion();
        } else {
            this.isExcetion = true;
        }

    }

    private void checkCodeNull(String code) {
        if (StringUtils.isBlank(code)) {
            throw new ExceptionUtil("sys.BaseRunTimeException.code.null");
        }
    }

    public String[] getArgs() {
        return this.args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isExcetion() {
        return this.isExcetion;
    }

    public void setExcetion(boolean excetion) {
        this.isExcetion = excetion;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public ExceptionUtil getFirstBaseRunTimeException() {
        return this.exceptionUtil;
    }
}
