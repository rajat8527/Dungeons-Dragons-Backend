package com.rakuten.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "dnd")
public class DnD {
   private String name;
   private Integer age;
   private Object classes;
   private Object races;
   private Object equipments;
   private Object spells;
}
