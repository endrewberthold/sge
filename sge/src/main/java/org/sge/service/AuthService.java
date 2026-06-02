package org.sge.service;

import org.sge.dtos.LoginRequestDTO;
import org.sge.dtos.RegisterRequestDTO;
import org.sge.entity.Client;
import org.sge.entity.User;
import org.sge.enums.AuthProvider;
import org.sge.enums.Role;
import org.sge.repository.ClientRepository;
import org.sge.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequestDTO dto) {

        if(userRepository.findByEmail(dto.email()).isPresent()){
            throw new RuntimeException("E-mail já cadastrado.");
        }

        User user = new User();

        user.setEmail(dto.email());
        user.setPassword(
                passwordEncoder.encode(dto.password())
        );
        user.setRole(Role.CLIENT);
        user.setProvider(AuthProvider.LOCAL);

        user = userRepository.save(user);

        Client client = new Client();

        client.setName(dto.name());
        client.setDocument(dto.document());
        client.setPhone(dto.phone());
        client.setUser(user);

        clientRepository.save(client);
    }

    public void login(LoginRequestDTO dto)
    {
    User user = userRepository.findByEmail(dto.email())
            .orElseThrow(() ->
                    new RuntimeException("Usuário não encontrado."));

    boolean validPassword =
            passwordEncoder.matches(
                    dto.password(),
                    user.getPassword()
            );

    if(!validPassword){
        throw new RuntimeException("Senha inválida.");
    }

    }
}
