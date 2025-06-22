package cl.duoc.MicroservicioDescuentos.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.duoc.MicroservicioDescuentos.DTO.DescuentoDTO;
import cl.duoc.MicroservicioDescuentos.Repository.DescuentoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DescuentoService {

    private final DescuentoRepository repository;

    public DescuentoService(DescuentoRepository repository) {
        this.repository = repository;
    }

    public Optional<DescuentoDTO> validarDescuento(String codigo) {
        return repository.findByCodigoAndActivoIsTrue(codigo)
                .map(desc -> new DescuentoDTO(
                        desc.getCodigo(),
                        desc.getDescripcion(),
                        desc.getPorcentaje()
                ));
    }
}