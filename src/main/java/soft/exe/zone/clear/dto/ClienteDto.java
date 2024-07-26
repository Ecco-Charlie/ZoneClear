package soft.exe.zone.clear.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDto
{

    @Size(max = 16)
    private String nombre;

    @Size(max = 32)
    private String apellidos;

    @Size(max = 30)
    private String direccion;

    @Size(max = 2)
    private String pais;

    @Size(max = 13)
    private String telefono;

    private Long idCredenciales;

}
