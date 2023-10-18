package com.example.security.services;


import com.example.security.entities.Role;
import com.example.security.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

  @Autowired
  private RoleRepo roleRepo;

  public Role createNewRole(Role role) {
    return roleRepo.save(role);
  }
}
