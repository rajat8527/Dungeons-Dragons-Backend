package com.dnd.repository;

import com.dnd.model.DnD;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface DnDRepository extends MongoRepository<DnD, String>{
}