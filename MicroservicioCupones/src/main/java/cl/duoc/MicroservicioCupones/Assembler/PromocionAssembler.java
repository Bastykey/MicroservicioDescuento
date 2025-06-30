package cl.duoc.MicroservicioCupones.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import cl.duoc.MicroservicioCupones.Controller.PromocionController;
import cl.duoc.MicroservicioCupones.DTO.PromocionDTO;
import cl.duoc.MicroservicioCupones.Model.Promocion;

@Component
public class PromocionAssembler implements RepresentationModelAssembler<Promocion, EntityModel<PromocionDTO>> {

    @Override
    public EntityModel<PromocionDTO> toModel(Promocion promocion) {
        PromocionDTO dto = new PromocionDTO(
                promocion.getId(),
                promocion.getNombre(),
                promocion.getDescripcion(),
                promocion.getDescuento(),
                promocion.getFechaInicio(),
                promocion.getFechaFin()
        );

        return EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PromocionController.class)
                        .buscarPorId(promocion.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PromocionController.class)
                        .obtenerTodas()).withRel("promociones"));
    }
}
