package com.lhf.springboot.result;

/**
 * @ClassName: ResultCode
 * @Author: liuhefei
 * @Description: 状态码枚举
 * 这样的好处就把错误类型归类到某个区间内，如果区间不够，可以设计成4位数。
 * #1000～1999 区间表示参数错误
 * #2000～2999 区间表示用户错误
 * #3000～3999 区间表示接口异常
 * 这样前端开发人员在得到返回值后，根据状态码就可以知道，大概什么错误，再根据message相关的信息描述，可以快速定位。
 * @Date: 2019/12/24 9:46
 */
public enum ResultCode {
      /*成功状态码*/
      SUCCESS(1, "成功"),

      /*参数错误：1001-1999*/
      PARAM_IS_INVALID(1001,"参数无效"),
      PARAM_IS_BLANK(1002, "参数为空"),
      PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
      PARAM_NOT_COMPLETE(1004, "参数缺失"),

      /*用户错误：2001-2999*/
      USER_NOT_LOGGED_IN(2001, "用户未登录，访问的路径需要验证，请先登录"),
      USER_LOGIN_ERROR(2002, "账户不存在或密码错误"),
      USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用"),
      USER_NOT_EXIST(2004, "用户不存在"),
      USER_HAS_EXISTED(2005, "用户已存在")

    ;

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
