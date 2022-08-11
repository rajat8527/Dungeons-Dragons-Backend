package com.dnd.controller;

import com.dnd.dto.DnDDTO;
import com.dnd.model.DnD;
import com.dnd.service.DnDService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/character")
@RestController
public class DndController {

    @Autowired
    DnDService dnDService;

    @GetMapping(value = "/")
    public ResponseEntity<List<DnD>> getCharacterData(){
        return ResponseEntity.status(HttpStatus.OK).body(dnDService.getAllData());
    }

    @PostMapping(value = "/")
    public ResponseEntity<DnD> saveCharacterData(@RequestBody @Valid DnDDTO dnDDTO) throws IOException, ParseException {
        return ResponseEntity.status(HttpStatus.CREATED).body(dnDService.saveCharacterData(dnDDTO));
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<Object> deleteAllCharacters(){
        dnDService.deleteAllCharacters();
        return ResponseEntity.status(HttpStatus.OK).body("All Data Deleted");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAllData(@PathVariable ("id") String id){
        dnDService.deleteCharacterById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Character with id :"+id+" is deleted");
    }

}