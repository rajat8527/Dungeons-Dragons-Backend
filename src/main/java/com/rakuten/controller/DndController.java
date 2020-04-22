package com.rakuten.controller;

import com.rakuten.dto.DnDDTO;
import com.rakuten.model.DnD;
import com.rakuten.service.DnDService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DndController {

    @Autowired
    DnDService dnDService;

    @GetMapping(value = "/api/getCharacterData")
    public List<DnD> getCharacterData(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("X-Requested-With","XMLHttpRequest");
        response.setHeader("Origin","https://rakuten-dnd-ui.herokuapp.com");
        return dnDService.getAllData();
    }

    @PostMapping(value = "/api/saveCharacterData")
    public DnD saveCharacterData(@RequestBody DnDDTO dnDDTO, HttpServletResponse response) throws IOException, ParseException {
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("X-Requested-With","XMLHttpRequest");
        response.setHeader("Origin","https://rakuten-dnd-ui.herokuapp.com");
        return dnDService.saveCharacterData(dnDDTO);
    }

    @DeleteMapping(value = "/api/deleteAllData")
    public ResponseEntity<Object> deleteAllData(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("X-Requested-With","XMLHttpRequest");
        response.setHeader("Origin","https://rakuten-dnd-ui.herokuapp.com");
        dnDService.deleteAllData();
        return new ResponseEntity<>("All Data Deleted", HttpStatus.OK);
    }

}