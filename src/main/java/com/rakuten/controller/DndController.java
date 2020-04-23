package com.rakuten.controller;

import com.rakuten.dto.DnDDTO;
import com.rakuten.model.DnD;
import com.rakuten.service.DnDService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DndController {

    @Autowired
    DnDService dnDService;

    @GetMapping(value = "/api/getCharacterData")
    public List<DnD> getCharacterData(){
        return dnDService.getAllData();
    }

    @PostMapping(value = "/api/saveCharacterData")
    public DnD saveCharacterData(@RequestBody @Valid DnDDTO dnDDTO) throws IOException, ParseException {
        return dnDService.saveCharacterData(dnDDTO);
    }

    @DeleteMapping(value = "/api/deleteAllCharacters")
    public ResponseEntity<Object> deleteAllCharacters(){
        dnDService.deleteAllCharacters();
        return new ResponseEntity<>("All Data Deleted", HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/deleteCharacterById/{id}")
    public ResponseEntity<Object> deleteAllData(@PathVariable ("id") String id){
        dnDService.deleteCharacterById(id);
        return new ResponseEntity<>("Character with id :"+id+" is deleted", HttpStatus.OK);
    }

}