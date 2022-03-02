package com.chow.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

@Component
public class CommonBlockhandler {

    public static String blockHandler(String name, BlockException e) {
        return "BlockException";
    }
}
