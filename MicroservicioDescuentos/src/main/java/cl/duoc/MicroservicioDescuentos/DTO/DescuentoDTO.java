package cl.duoc.MicroservicioDescuentos.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa un descuento aplicado por código promocional")
public class DescuentoDTO {

    @Schema(description = "Código del descuento", example = "OFERTA10")
    private String codigo;

    @Schema(description = "Descripción del beneficio", example = "10% en toda la tienda")
    private String descripcion;

    @Schema(description = "Porcentaje del descuento", example = "0.1")
    private Double porcentaje;

    public DescuentoDTO() {}

    public DescuentoDTO(String codigo, String descripcion, Double porcentaje) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
}