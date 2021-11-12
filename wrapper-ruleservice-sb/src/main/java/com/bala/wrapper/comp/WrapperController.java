package com.bala.wrapper.comp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WrapperController {

    @GetMapping("/wrapperv1")
    private String testNewPath() {
        return "Successfully invoked the wrapper service";
    }
}
