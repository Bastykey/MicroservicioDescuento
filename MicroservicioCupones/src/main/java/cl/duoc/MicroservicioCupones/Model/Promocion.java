package cl.duoc.MicroservicioCupones.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double descuento; // porcentaje (ej: 10.0 = 10%)
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
