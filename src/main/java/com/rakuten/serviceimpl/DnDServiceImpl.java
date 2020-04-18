package com.rakuten.serviceimpl;

import com.rakuten.model.DnD;
import com.rakuten.repository.DnDRepository;
import com.rakuten.service.DnDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DnDServiceImpl implements DnDService {
    @Autowired
    private DnDRepository dnDRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public DnD saveCharacterData(DnD dnD) {
        return dnDRepository.save(dnD);
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
