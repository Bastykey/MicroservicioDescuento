package cl.duoc.MicroservicioDescuentos.Repository;

import cl.duoc.MicroservicioDescuentos.Model.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    Optional<Descuento> findByCodigoAndActivoIsTrue(String codigo);
}