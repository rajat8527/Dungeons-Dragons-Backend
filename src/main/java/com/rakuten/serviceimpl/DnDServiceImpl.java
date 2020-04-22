package com.rakuten.serviceimpl;

import com.rakuten.constants.Constants;
import com.rakuten.dto.DnDDTO;
import com.rakuten.exception.CharacterClassNotFoundException;
import com.rakuten.model.DnD;
import com.rakuten.repository.DnDRepository;
import com.rakuten.service.CallExternalAPIService;
import com.rakuten.service.DnDService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@CacheConfig
public class DnDServiceImpl implements DnDService {
    @Autowired
    DnDRepository dnDRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CallExternalAPIService callExternalAPIService;


    @Override
    public DnD saveCharacterData(DnDDTO dnDDTO) throws IOException, ParseException {
        DnD dnD = new DnD();
        BeanUtils.copyProperties(dnDDTO, dnD);
        dnD.setSubclasses(fetchSubclasses(dnD));
        dnD.setEquipments(fetchEquipments(dnD));
        dnD.setSpells(fetchSpells(dnD));
        dnD.setImageUrl(fetchImageUrl(dnD));
        return dnDRepository.save(dnD);
    }

    private JSONArray fetchEquipments(DnD dnD) throws IOException, ParseException {
        String inputUrl = Constants.DND_BASE_URL + dnD.getClasses()+"/starting-equipment";
        StringBuilder sb = callExternalAPIService.callExternalAPI(inputUrl);
        JSONObject equipmentObject = (JSONObject) new JSONParser().parse(sb.toString());
        return  (JSONArray) equipmentObject.get("starting_equipment");
    }

    private JSONArray fetchSpells(DnD dnD) throws IOException, ParseException {
        String inputUrl = Constants.DND_BASE_URL + dnD.getClasses()+"/spells";
        StringBuilder sb = callExternalAPIService.callExternalAPI(inputUrl);
        JSONObject spellsObject = (JSONObject) new JSONParser().parse(sb.toString());
        return  (JSONArray) spellsObject.get("results");
    }

    private JSONArray fetchSubclasses(DnD dnD) throws IOException, ParseException {
        String inputUrl = Constants.DND_BASE_URL + dnD.getClasses()+"/subclasses";
        StringBuilder sb = callExternalAPIService.callExternalAPI(inputUrl);
        if(sb.length() == 0){
            throw new CharacterClassNotFoundException("Provided character class : "+ dnD.getClasses()+ " not found, please try a valid class", HttpStatus.NOT_FOUND);
        }
        JSONObject subclassesObject = (JSONObject) new JSONParser().parse(sb.toString());
        return  (JSONArray) subclassesObject.get("results");
    }

    private String fetchImageUrl(DnD dnD){
       switch (dnD.getClasses()){
           case "barbarian":
               return Constants.BARBARIAN_IMAGE;
           case "bard":
               return Constants.BARD_IMAGE;
           case "cleric":
               return Constants.CLERIC_IMAGE;
           case "druid":
               return Constants.DRUID_IMAGE;
           case "wizard":
               return Constants.WIZARD_IMAGE;
           case "ranger":
               return Constants.RANGER_IMAGE;
           case "rogue":
               return Constants.ROGUE_IMAGE;
           case "warlock":
               return Constants.WARLOCK_IMAGE;
           case "sorcerer":
               return Constants.SORCERER_IMAGE;
           case "paladin":
               return Constants.PALADIN_IMAGE;
           case "fighter":
               return Constants.FIGHTER_IMAGE;
           case "monk":
               return Constants.MONK_IMAGE;
           default:
               return "No Image Found";
       }
    }

    @Override
    public List<DnD> getAllData() {
        return mongoTemplate.findAll(DnD.class);
    }

    @Override
    public void deleteAllData() {
        dnDRepository.deleteAll();
    }
}
