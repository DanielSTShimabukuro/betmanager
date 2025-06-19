package betmanager.betmanager.models.adress;

import java.util.Set;

import betmanager.betmanager.dtos.address.AddressDTO;
import betmanager.betmanager.models.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "addresses")
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  @NotBlank(message = "Invalid cep.")
  private String cep;

  @Column(nullable = false)
  @NotBlank(message = "Invalid street.")
  private String street;

  private int numberStreet;

  private String comp;

  @Column(nullable = false)
  @NotBlank(message = "Invalid city.")
  private String city;
  
  @Column(nullable = false)
  @NotBlank(message = "Invalid state.")
  private String state;

  @Column(nullable = false)
  @NotNull
  @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
  public Set<User> users;

  public Address(AddressDTO data) {
    this.cep = data.cep();
    this.street = data.street();
    this.numberStreet = data.numberStreet();
    this.comp = data.comp();
    this.city = data.city();
    this.state = data.state();
  }
}
