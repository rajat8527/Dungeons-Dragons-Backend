package com.rakuten.model;

import lombok.Data;
import org.json.simple.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "dnd")
public class DnD {
   private String name;
   private int age;
   private String classes;
   private JSONArray subclasses;
   private String races;
   private JSONArray equipments;
   private JSONArray spells;
   private String imageUrl;
}
