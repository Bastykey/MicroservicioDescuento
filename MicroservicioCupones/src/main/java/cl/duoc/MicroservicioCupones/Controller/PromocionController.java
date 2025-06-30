package cl.duoc.MicroservicioCupones.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import cl.duoc.MicroservicioCupones.Assembler.PromocionAssembler;
import cl.duoc.MicroservicioCupones.DTO.PromocionDTO;
import cl.duoc.MicroservicioCupones.Model.Promocion;
import cl.duoc.MicroservicioCupones.Service.PromocionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/promociones")
@Tag(name = "Promociones", description = "Gestión de promociones y descuentos")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @Autowired
    private PromocionAssembler promocionAssembler;

    @Operation(summary = "Obtener todas las promociones")
    @ApiResponse(responseCode = "200", description = "Listado de promociones")
    @GetMapping
    public CollectionModel<EntityModel<PromocionDTO>> obtenerTodas() {
        List<EntityModel<PromocionDTO>> promociones = promocionService.obtenerTodas().stream()
                .map(promocionAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(promociones);
    }

    @Operation(summary = "Buscar promoción por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Promoción encontrada",
            content = @Content(schema = @Schema(implementation = PromocionDTO.class))),
        @ApiResponse(responseCode = "404", description = "Promoción no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PromocionDTO>> buscarPorId(@PathVariable Long id) {
        Optional<Promocion> promocion = promocionService.buscarPorId(id);
        return promocion.map(p -> ResponseEntity.ok(promocionAssembler.toModel(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar promoción por nombre")
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EntityModel<PromocionDTO>> buscarPorNombre(@PathVariable String nombre) {
        Optional<Promocion> promocion = promocionService.buscarPorNombre(nombre);
        return promocion.map(p -> ResponseEntity.ok(promocionAssembler.toModel(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nueva promoción")
    @PostMapping
    public ResponseEntity<EntityModel<PromocionDTO>> crear(@RequestBody Promocion promocion) {
        Promocion creada = promocionService.guardar(promocion);
        return ResponseEntity.status(HttpStatus.CREATED).body(promocionAssembler.toModel(creada));
    }

    @Operation(summary = "Actualizar promoción")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PromocionDTO>> actualizar(@PathVariable Long id, @RequestBody Promocion promocion) {
        Optional<Promocion> actualizada = promocionService.actualizar(id, promocion);
        return actualizada.map(p -> ResponseEntity.ok(promocionAssembler.toModel(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar promoción")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = promocionService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
