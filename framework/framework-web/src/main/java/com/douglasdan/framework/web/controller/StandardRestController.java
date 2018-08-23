package com.douglasdan.framework.web.controller;

import com.douglasdan.framework.common.error.BizRuntimeException;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dougals.Dan
 * @date 2018-08-23
 */

@SuppressWarnings("ALL")
@RestController
@Scope("request")
public class StandardRestController {

    @RequestMapping("/")
    public String helloworld() {
        return "Hello World";
    }

    @RequestMapping("/timeout/{timeout}")
    public String readTime(
            @PathVariable(name = "timeout") long timeout
    ) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Sleep "+timeout+" over";
    }

    @RequestMapping("/error/biz")
    public String bizError() {
        throw new BizRuntimeException().setMessage("mock biz exception");
    }

}
