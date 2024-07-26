package soft.exe.zone.clear.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "distribuidor")
public class Distribuidor
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @Size(max = 16)
    private String nombre;

    @Column @Size(max = 13)
    private String telefono;

    @Column @Size(max = 2)
    private String pais;

    @Column(length = 16777215)
    private byte[] foto;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_credenciales")
    private Credenciales idCredenciales;

}
