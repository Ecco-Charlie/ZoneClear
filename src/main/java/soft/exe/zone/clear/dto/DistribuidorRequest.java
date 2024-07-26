package soft.exe.zone.clear.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class DistribuidorRequest
{

    @Email
    private String email;

    private String password;

    @Size(max = 16)
    private String nombre;

    @Size(max = 13)
    private String telefono;

    @Size(max = 2)
    private String pais;


    private MultipartFile foto;

}
