package br.com.zellner.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="USERS")
@Entity(name = "USER")
public class UserModel {
  @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "role")
  private String role;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
