package cl.duoc.MicroservicioDescuentos.Controller;

import cl.duoc.MicroservicioDescuentos.DTO.DescuentoDTO;
import cl.duoc.MicroservicioDescuentos.Service.DescuentoService;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

@RequestMapping("api/v1/descuento")
@Tag(name = "Descuentos", description = "Endpoints para validación de cupones y gestión de descuentos")
public class DescuentoController {

    private final DescuentoService service;

    public DescuentoController(DescuentoService service) {
        this.service = service;
    }

    @Operation(
        summary = "Validar un código de descuento",
        description = "Verifica si un cupón de descuento es válido y está activo en el sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cupón válido"),
        @ApiResponse(responseCode = "404", description = "Cupón inválido o inactivo")
    })
@GetMapping("/validar")
    public ResponseEntity<?> validar(
        @RequestParam String codigo) {
        Optional<DescuentoDTO> dto = service.validarDescuento(codigo);

    if (dto.isPresent()) {
        return ResponseEntity.ok(dto.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("mensaje", "Cupón inválido o expirado"));
    }
}
}