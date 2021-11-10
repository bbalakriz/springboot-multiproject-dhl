package com.bala.military.comp;

import com.bala.military.comp.model.Item;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class MilitaryController {
 
    private MilitaryService mlService;
 
    public MilitaryController(MilitaryService service) {
        this.mlService = service;
    }
 
    @PostMapping("/military")
    private Item checkMilitaryRules(@RequestBody Item item) {       
        this.mlService.checkMilitaryRules(item);
        return item;
    }
}
