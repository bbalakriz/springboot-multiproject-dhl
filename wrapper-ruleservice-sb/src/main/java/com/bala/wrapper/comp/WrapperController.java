package com.bala.wrapper.comp;

import com.bala.export.comp.ExportCompService;
import com.bala.military.comp.MilitaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WrapperController {

    @Autowired
    private ExportCompService ecService;

    @Autowired
    private MilitaryService mlService;

    @PostMapping("/exportcomp")
    private com.bala.export.comp.model.Item checkExportCompRules(@RequestBody com.bala.export.comp.model.Item item) {
        this.ecService.checkExportCompRules(item);
        return item;
    }

    @PostMapping("/military")
    private com.bala.military.comp.model.Item checkMilitaryRules(@RequestBody com.bala.military.comp.model.Item item) {
        this.mlService.checkMilitaryRules(item);
        return item;
    }
}
