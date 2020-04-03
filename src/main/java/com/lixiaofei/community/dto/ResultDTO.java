package com.lixiaofei.community.dto;

import com.lixiaofei.community.exception.CustomizeErrorCode;
import com.lixiaofei.community.exception.CustomizeException;

/**
 * status用于交换
 */

public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO stateOf(Integer code,String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.message = message;
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeException e) {
        return stateOf(e.getCode(),e.getMessage());
    }

    //可以通过传入CustomizeErrorCode，共用一套错误码
    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return stateOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultDTO ok(ResultDTO resultDTO) {
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功.");
        return resultDTO;
    }

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
}
