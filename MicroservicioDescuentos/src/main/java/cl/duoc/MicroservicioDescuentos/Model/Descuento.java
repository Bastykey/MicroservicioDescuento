package cl.duoc.MicroservicioDescuentos.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String descripcion;
    private Double porcentaje; // ejemplo: 0.10 para 10%
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean activo;

    
}