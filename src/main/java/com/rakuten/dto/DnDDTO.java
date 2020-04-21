package com.rakuten.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class DnDDTO implements Serializable {
  @NotBlank
  private String name;
  @NotBlank
  private Integer age;
  @NotBlank
  private String classes;
  @NotBlank
  private String races;
}