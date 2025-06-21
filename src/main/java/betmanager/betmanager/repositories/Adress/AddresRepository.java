package betmanager.betmanager.repositories.Adress;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import betmanager.betmanager.models.adress.Address;

public interface AddresRepository extends JpaRepository<Address, String> {
  Optional<Address> findAddressById(String id);
  Optional<Address> findAddressByCep(String cep);
}
