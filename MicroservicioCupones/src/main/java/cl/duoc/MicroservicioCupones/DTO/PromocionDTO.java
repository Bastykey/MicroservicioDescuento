package cl.duoc.MicroservicioCupones.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PromocionDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double descuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
