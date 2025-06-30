package cl.duoc.MicroservicioCupones.Service;

import cl.duoc.MicroservicioCupones.Model.Promocion;
import cl.duoc.MicroservicioCupones.Repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocionService {

    private final PromocionRepository promocionRepository;

    @Autowired
    public PromocionService(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    public List<Promocion> obtenerTodas() {
        return promocionRepository.findAll();
    }

    public Optional<Promocion> buscarPorId(Long id) {
        return promocionRepository.findById(id);
    }

    public Optional<Promocion> buscarPorNombre(String nombre) {
        return promocionRepository.findByNombre(nombre);
    }

    public Promocion guardar(Promocion promocion) {
        return promocionRepository.save(promocion);
    }

    public Optional<Promocion> actualizar(Long id, Promocion nueva) {
        return promocionRepository.findById(id).map(p -> {
            p.setNombre(nueva.getNombre());
            p.setDescripcion(nueva.getDescripcion());
            p.setDescuento(nueva.getDescuento());
            p.setFechaInicio(nueva.getFechaInicio());
            p.setFechaFin(nueva.getFechaFin());
            return promocionRepository.save(p);
        });
    }

    public boolean eliminar(Long id) {
        if (promocionRepository.existsById(id)) {
            promocionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
