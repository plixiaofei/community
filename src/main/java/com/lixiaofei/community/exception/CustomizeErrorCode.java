package com.lixiaofei.community.exception;

/**
 * 理解了一点，这是一个封装方法，通过接口调用，接口里有两个方法，通过这个类实现
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(1001,"您查看的问题不存在,请换一个看看~"),
    SERVER_WRONG(1002,"服务器炸裂了~"),
    TARGET_QUESTION_NOT_FOUND(1003,"目标问题不存在或被删除~"),
    USER_NO_LOGIN(1004,"用户未登录,请登陆后重试！"),
    COMMENT_TYPE_WRONG(1005,"评论类型错误"),
    COMMENT_NOT_FOUND(1006,"你回复的评论不存在或已被删除"),
    USER_NOT_FOUND(1007,"用户不存在或者不合法！");
    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
