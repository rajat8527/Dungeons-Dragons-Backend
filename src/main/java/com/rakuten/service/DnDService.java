package com.rakuten.service;

import com.rakuten.model.DnD;

import java.util.List;

public interface DnDService {
    DnD saveCharacterData(DnD dnD);
    List<DnD> getAllData();
}
