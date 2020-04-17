package com.rakuten.controller;

import com.rakuten.model.DnD;
import com.rakuten.service.DnDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DndController {

    @Autowired
    private DnDService dnDService;

    @GetMapping(value = "/api/getCharacterData")
    public List<DnD> getCharacterData(){
       return dnDService.getAllData();
    }

}