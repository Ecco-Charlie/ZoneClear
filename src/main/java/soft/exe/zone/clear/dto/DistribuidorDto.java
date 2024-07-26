package soft.exe.zone.clear.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DistribuidorDto
{
    @Size(max = 16)
    private String nombre;

    @Size(max = 13)
    private String telefono;

    @Size(max = 2)
    private String pais;

    private byte[] foto;

    private Long idCredenciales;

}
