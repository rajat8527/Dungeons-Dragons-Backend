package com.dnd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@Data
public class DnDDTO implements Serializable {
  @NotBlank
  private String name;
  @NotNull
  private Integer age;
  @NotBlank
  private String classes;
  @NotBlank
  private String races;
}