package com.example.security.controllers;


import com.example.security.entities.Role;
import com.example.security.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class RoleController {

  @Autowired
  private RoleService roleService;

  @PostMapping("/createNewRole")
  public Role createNewRole(@RequestBody Role role) {
    return roleService.createNewRole(role);

  }
}
