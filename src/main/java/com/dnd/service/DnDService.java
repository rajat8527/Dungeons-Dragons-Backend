package com.dnd.service;

import com.dnd.dto.DnDDTO;
import com.dnd.model.DnD;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface DnDService {
    DnD saveCharacterData(DnDDTO dnDDTO) throws IOException, ParseException;
    List<DnD> getAllData();
    void deleteAllCharacters();
    void deleteCharacterById(String id);
}
