package com.chow.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestOriginParserDefinition implements RequestOriginParser {

    // 通过request获取来源标识， 进行区分
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String serviceName = request.getParameter("serviceName");
        if (StringUtils.isEmpty(serviceName)) {
            throw new RuntimeException("serviceName can not empty");
        }
        return serviceName;
    }
}
