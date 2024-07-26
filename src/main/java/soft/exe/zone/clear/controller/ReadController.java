package soft.exe.zone.clear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.exe.zone.clear.dao.ClienteDao;
import soft.exe.zone.clear.entity.Cliente;

@RestController
@RequestMapping("/read")
public class ReadController
{

    @Autowired
    private ClienteDao clienteDao;


    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> findClienteById(
            @PathVariable Long id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    )
    {

        Cliente cliente = clienteDao.findById(id).orElseThrow();

        return null;
    }

}
