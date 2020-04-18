package com.rakuten.controller;

import com.rakuten.model.DnD;
import com.rakuten.service.DnDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DndController {

    @Autowired
    private DnDService dnDService;

    @GetMapping(value = "/api/getCharacterData")
    public List<DnD> getCharacterData(){
       return dnDService.getAllData();
    }

    @PostMapping(value = "/api/saveCharacterData")
    public DnD saveCharacterData(@RequestBody DnD dnD){
        return dnDService.saveCharacterData(dnD);
    }

}