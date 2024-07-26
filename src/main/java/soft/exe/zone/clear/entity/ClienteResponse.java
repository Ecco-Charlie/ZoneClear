package soft.exe.zone.clear.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteResponse
{

    private Long id;

    @Size(max = 16)
    private String nombre;

    @Size(max = 32)
    private String apellidos;

    @Size(max = 60)
    private String direccion;

    @Size(max = 2)
    private String pais;

    @Size(max = 13)
    private String telefono;

    @Email
    private String email;

}
