package com.azaoui.hibernate1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Contact {

  @Id
  @GeneratedValue
  private Long   id;

  private String name;

  private String email;

  private String phone;
}
