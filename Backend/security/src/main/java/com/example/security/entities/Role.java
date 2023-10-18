package com.example.security.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role {

  @Id
  @Column(name = "role_name")
  private String roleName;
  @Column(name = "role_description")
  private String roleDescription;

}
