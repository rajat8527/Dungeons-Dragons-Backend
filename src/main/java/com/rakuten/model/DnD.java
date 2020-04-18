package com.rakuten.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Document(collection = "dnd")
public class DnD {
   @NotBlank
   private String name;
   @NotBlank
   private int age;
   @NotNull
   private Object classes;
   @NotNull
   private Object races;
   @NotNull
   private Object equipments;
   private Object spells;
}
