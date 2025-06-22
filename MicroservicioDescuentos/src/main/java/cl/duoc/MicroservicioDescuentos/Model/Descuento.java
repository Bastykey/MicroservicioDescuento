package cl.duoc.MicroservicioDescuentos.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
@Schema(description = "Entidad persistente que representa un descuento en la base de datos")
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Código único del descuento", example = "OFERTA10")
    private String codigo;

    @Schema(description = "Texto descriptivo del descuento", example = "10% para clientes nuevos")
    private String descripcion;

    @Schema(description = "Porcentaje aplicado al total", example = "0.10")
    private Double porcentaje;

    @Schema(description = "Fecha de inicio", example = "2025-06-22")
    private LocalDate fechaInicio;

    @Schema(description = "Fecha de expiración", example = "2025-07-01")
    private LocalDate fechaFin;

    @Schema(description = "Estado de activación del descuento", example = "true")
    private Boolean activo;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(Double porcentaje) { this.porcentaje = porcentaje; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}