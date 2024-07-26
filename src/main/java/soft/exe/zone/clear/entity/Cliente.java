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
@Table(name = "cliente")
public class Cliente
{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @Size(max = 16)
    private String nombre;

    @Column @Size(max = 32)
    private String apellidos;

    @Column @Size(max = 60)
    private String direccion;

    @Column @Size(max = 2)
    private String pais;

    @Column @Size(max = 20)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_credenciales")
    private Credenciales idCredenciales;

}
