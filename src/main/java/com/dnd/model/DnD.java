package com.dnd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "dnd")
public class DnD {
   private String _id;
   private String name;
   private int age;
   private String classes;
   private JSONArray subclasses;
   private String races;
   private JSONArray equipments;
   private JSONArray spells;
   private String imageUrl;

   public DnD(String name, int age, String classes, JSONArray subclasses, String races, JSONArray equipments, JSONArray spells, String imageUrl) {
      this.name = name;
      this.age = age;
      this.classes = classes;
      this.subclasses = subclasses;
      this.races = races;
      this.equipments = equipments;
      this.spells = spells;
      this.imageUrl = imageUrl;
   }

   public DnD(String name) {
      this.name = name;
   }
}
