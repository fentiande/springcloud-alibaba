package com.chow.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExceptionHandlerPage implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        response.setContentType("application/json;charset=utf8");

        ResponseData responseData = null;

        if (e instanceof FlowException) {
            responseData = new ResponseData(1, "限流了");
        } else if (e instanceof DegradeException) {
            responseData = new ResponseData(1, "降级了");
        }

        response.getWriter().write(JSON.toJSONString(responseData));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ResponseData{
    private int code;
    private String message;
}
