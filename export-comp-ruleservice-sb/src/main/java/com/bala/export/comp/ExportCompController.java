package com.bala.export.comp;

import com.bala.export.comp.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class ExportCompController {
    
    @Autowired
    private ExportCompService ecService;

    @PostMapping("/exportcomp")
    private Item checkExportCompRules(@RequestBody Item item) {       
        this.ecService.checkExportCompRules(item);
        return item;
    }
}
