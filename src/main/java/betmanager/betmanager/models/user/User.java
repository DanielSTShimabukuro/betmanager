package betmanager.betmanager.models.user;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import betmanager.betmanager.dtos.user.RegisterDTO;
import betmanager.betmanager.models.adress.Address;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  
  @Column(nullable = false, unique = true)
  @NotBlank(message = "Invalid username.")
  private String username;
  
  @Column(nullable = false, updatable = false, unique = true)
  @NotBlank(message = "Invalid cpf.")
  private String cpf;
  
  @Column(nullable = false)
  @NotBlank(message = "Invalid telephone.")
  private String telephone;
  
  @Column(nullable = false)
  @NotBlank(message = "Invalid password.")
  private String password;
  
  @Column(nullable = false)
  @NotBlank(message = "Invalid gener.")
  private String gener;
  
  @Column(nullable = false)
  @NotBlank(message = "Invalid completeName.")
  private String completeName;
  
  @Column(nullable = false, unique = true)
  @NotBlank(message = "Invalid email.")
  @Email
  private String email;
  
  @Column(nullable = false, updatable = false)
  @NotNull
  private LocalDate birthday;
  
  @CreationTimestamp
  private Instant createdAt;

  @Column(nullable = false)
  @NotNull
  private Instant updatedAt;

  @Column(nullable = false)
  @NotNull
  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Column(nullable = false)
  @NotBlank
  @ElementCollection
  private List<String> nationalities;

  @NotNull
  @ManyToOne
  @JoinColumn(nullable = false, name = "address_id")
  private Address address;

  public User(RegisterDTO data) {
    this.username = data.username();
    this.cpf = data.cpf();
    this.telephone = data.telephone();
    this.gener = data.gener();
    this.completeName = data.completeName();
    this.email = data.email();
    this.birthday = data.birthday();
    this.updatedAt = Instant.now();
    this.nationalities = data.nationalities();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == UserRole.USER) return List.of(new SimpleGrantedAuthority("USER"));

    return List.of(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN"));
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

  @PreUpdate
  public void preUpdate() {
    updatedAt = Instant.now();
  }
}
