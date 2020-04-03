package com.lixiaofei.community.controllerAdvice;

import com.alibaba.fastjson.JSON;
import com.lixiaofei.community.dto.ResultDTO;
import com.lixiaofei.community.exception.CustomizeErrorCode;
import com.lixiaofei.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandle{
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable e, Model model, HttpServletRequest request,HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)) {
            //返回JSON
            ResultDTO resultDTO;
            if(e instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SERVER_WRONG);
            }
            try {
                response.setCharacterEncoding("utf-8");
                response.setStatus(200);
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioE) {
            }
            return null;
        } else {
            //错误页面跳转
            if(e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SERVER_WRONG);
            }
            return new ModelAndView("error");
        }
    }
}
