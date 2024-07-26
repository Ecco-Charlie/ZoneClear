package soft.exe.zone.clear.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import soft.exe.zone.clear.config.jwt.JwtUtils;
import soft.exe.zone.clear.dto.AuthLoginRequest;
import soft.exe.zone.clear.dto.AuthResponse;
import soft.exe.zone.clear.dto.ClienteRequest;
import soft.exe.zone.clear.dto.DistribuidorRequest;
import soft.exe.zone.clear.entity.*;

import java.io.IOException;
import java.util.Optional;

@Service
public class CredencialesDetailsService implements UserDetailsService
{

    @Autowired
    private CredencialesDao credencialesDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private DistribuidorDao distribuidorDao;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Credenciales u = credencialesDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado { " + username + " }"));

        UserDetails userDetails = User
                .withUsername(username)
                .password(u.getPassword())
                .roles(u.getRole().getRol().name())
                .build();

        return userDetails;
    }

    public AuthResponse login(AuthLoginRequest authLoginRequest)
    {

        if (authLoginRequest.getEmail().isEmpty() || authLoginRequest.getPassword().isEmpty())
        {
            return AuthResponse.builder()
                    .message("Campos vacios")
                    .build();
        }

        UserDetails user = null;

        try
        {
            user = this.loadUserByUsername(authLoginRequest.getEmail());

        }catch (UsernameNotFoundException e)
        {
            return AuthResponse.builder()
                    .message("Usuario no encontrado")
                    .build();
        }

        if (!passwordEncoder.matches(
                authLoginRequest.getPassword(),
                user.getPassword()
        ))
        {
            return AuthResponse.builder()
                    .message("Password incorrecta")
                    .build();
        }

        String token = jwtUtils.createToken(user);

        return AuthResponse.builder()
                .message("Login Successful")
                .token(token)
                .build();
    }

    public AuthResponse clientRegister(ClienteRequest clienteRequest)
    {

        Optional<Credenciales> u = credencialesDao.findByEmail(clienteRequest.getEmail());

        if (u.isPresent())
        {
            return AuthResponse.builder()
                    .message("El email ya esta registrado")
                    .build();
        }

        Credenciales newUser = Credenciales.builder()
                .email(clienteRequest.getEmail())
                .password(passwordEncoder.encode(clienteRequest.getPassword()))
                .role(ERole.builder().rol(Role.CLIENTE).build())
                .build();

        credencialesDao.save(newUser);

        String token = jwtUtils.createToken(newUser);

        Cliente clienteDto = Cliente.builder()
                .nombre(clienteRequest.getNombre())
                .apellidos(clienteRequest.getApellidos())
                .direccion(clienteRequest.getDireccion())
                .telefono(clienteRequest.getTelefono())
                .pais(clienteRequest.getPais())
                .idCredenciales(newUser)
                .build();

        clienteDao.save(clienteDto);

        return AuthResponse.builder()
                .message("Registro Successful")
                .token(token)
                .build();
    }

    public AuthResponse registerDistribuidor(DistribuidorRequest distribuidorResponse)
    {

        Optional<Credenciales> u = credencialesDao.findByEmail(distribuidorResponse.getEmail());

        if (u.isPresent())
        {
            return AuthResponse.builder()
                    .message("El email ya esta registrado")
                    .build();
        }

        Credenciales newUser = Credenciales.builder()
                .email(distribuidorResponse.getEmail())
                .password(passwordEncoder.encode(distribuidorResponse.getPassword()))
                .role(ERole.builder().rol(Role.DISTRIBUIDOR).build())
                .build();

        credencialesDao.save(newUser);

        String token = jwtUtils.createToken(newUser);

        Distribuidor distribuidorDto = null;

        try
        {
            distribuidorDto = Distribuidor.builder()
                    .nombre(distribuidorResponse.getNombre())
                    .telefono(distribuidorResponse.getTelefono())
                    .pais(distribuidorResponse.getPais())
                    .foto(distribuidorResponse.getFoto().getBytes())
                    .idCredenciales(newUser)
                    .build();
        }catch (IOException e)
        {
            System.out.println(e);
            return AuthResponse.builder()
                    .message("Registro incorrecto")
                    .token(token)
                    .build();
        }

        distribuidorDao.save(distribuidorDto);

        return AuthResponse.builder()
                .message("Registro Successful")
                .token(token)
                .build();
    }

}
