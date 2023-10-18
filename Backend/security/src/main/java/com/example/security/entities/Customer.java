package com.example.security.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "Customer")
@Data
public class Customer implements UserDetails {


  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "customer_id")
  private int customerId;

  @Column(name = "email")
  private String email;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "company_name")
  private String companyName;

  @Column(name = "customer_password")
  private String customerPassword;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "CUSTOMER_ROLE", joinColumns = {@JoinColumn(name = "CUSTOMER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
  private Set<Role> role;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return customerPassword;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return "Customer{" + "customer_id=" + customerId + ", email='" + email + '\'' + ", customer_name='" + customerName + '\'' + ", company_name='" + companyName + '\'' + ", customerPassword='" + customerPassword + '\'' + ", role=" + role + '}';
  }
}
