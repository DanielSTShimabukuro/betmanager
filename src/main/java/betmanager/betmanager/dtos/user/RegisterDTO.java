package betmanager.betmanager.dtos.user;

import java.time.LocalDate;
import java.util.List;

import betmanager.betmanager.dtos.address.AddressDTO;

public record RegisterDTO(String username, String password, String email, String cpf, String telephone, String gener, String completeName, LocalDate birthday, List<String> nationalities, AddressDTO addressDTO) {

}
