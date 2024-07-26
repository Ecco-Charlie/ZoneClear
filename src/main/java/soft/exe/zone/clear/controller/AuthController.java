package soft.exe.zone.clear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.exe.zone.clear.dao.CredencialesDao;
import soft.exe.zone.clear.dao.CredencialesDetailsService;
import soft.exe.zone.clear.dto.AuthLoginRequest;
import soft.exe.zone.clear.dto.AuthResponse;
import soft.exe.zone.clear.dto.ClienteRequest;
import soft.exe.zone.clear.dto.DistribuidorRequest;

@RequestMapping("/auth")
@RestController
public class AuthController
{

    @Autowired
    private CredencialesDetailsService credencialesDetailsService;

    @Autowired
    private CredencialesDao credencialesDao;

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute AuthLoginRequest authLoginRequest)
    {
        AuthResponse authResponse = credencialesDetailsService.login(authLoginRequest);

        if (authResponse.getToken() == null)
        {
            return ResponseEntity.status(406).body(authResponse);
        }

        return ResponseEntity.ok(authResponse);

    }


    @PostMapping("/register/cliente")
    public ResponseEntity<?> clienteRegister(@ModelAttribute ClienteRequest clienteRequest)
    {
        return ResponseEntity.ok(credencialesDetailsService.clientRegister(clienteRequest));
    }


    @PostMapping("/register/distribuidor")
    public ResponseEntity<?> distribuidorRegister(@ModelAttribute DistribuidorRequest distribuidorRequest)
    {
        return ResponseEntity.ok(credencialesDetailsService.registerDistribuidor(distribuidorRequest));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findEmailById(@PathVariable Long id)
    {
        return ResponseEntity.ok(credencialesDao.getEmailById(id).get());
    }

}
