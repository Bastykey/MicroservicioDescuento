package cl.duoc.MicroservicioCupones.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.duoc.MicroservicioCupones.Model.Promocion;
import cl.duoc.MicroservicioCupones.Repository.PromocionRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PromocionServiceTest {

    private PromocionRepository promocionRepository;
    private PromocionService promocionService;

    @BeforeEach
    void setUp() {
        promocionRepository = Mockito.mock(PromocionRepository.class);
        promocionService = new PromocionService(promocionRepository); // ✅ usa constructor
    }

    @Test
    void testObtenerTodas() {
        Promocion p1 = new Promocion(1L, "Promo A", "10% en perfumes", 10.0, LocalDate.now(), LocalDate.now().plusDays(10));
        Promocion p2 = new Promocion(2L, "Promo B", "15% en sets", 15.0, LocalDate.now(), LocalDate.now().plusDays(5));
        when(promocionRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Promocion> resultado = promocionService.obtenerTodas();

        assertEquals(2, resultado.size());
        verify(promocionRepository).findAll();
    }

    @Test
    void testBuscarPorId() {
        Promocion promo = new Promocion(1L, "Promo A", "Descuento", 10.0, LocalDate.now(), LocalDate.now());
        when(promocionRepository.findById(1L)).thenReturn(Optional.of(promo));

        Optional<Promocion> resultado = promocionService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Promo A", resultado.get().getNombre());
    }

    @Test
    void testBuscarPorNombre() {
        Promocion promo = new Promocion(1L, "Black Friday", "Grandes descuentos", 50.0, LocalDate.now(), LocalDate.now());
        when(promocionRepository.findByNombre("Black Friday")).thenReturn(Optional.of(promo));

        Optional<Promocion> resultado = promocionService.buscarPorNombre("Black Friday");

        assertTrue(resultado.isPresent());
        assertEquals(50.0, resultado.get().getDescuento());
    }

    @Test
    void testGuardar() {
        Promocion promo = new Promocion(null, "Cyber", "25% en todo", 25.0, LocalDate.now(), LocalDate.now());
        when(promocionRepository.save(promo)).thenReturn(promo);

        Promocion guardada = promocionService.guardar(promo);

        assertEquals("Cyber", guardada.getNombre());
        verify(promocionRepository).save(promo);
    }

    @Test
    void testActualizar() {
        Promocion existente = new Promocion(1L, "Antigua", "Desc", 5.0, LocalDate.now(), LocalDate.now());
        Promocion nueva = new Promocion(1L, "Nueva", "50%", 50.0, LocalDate.now(), LocalDate.now());

        when(promocionRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(promocionRepository.save(any(Promocion.class))).thenReturn(nueva);

        Optional<Promocion> resultado = promocionService.actualizar(1L, nueva);

        assertTrue(resultado.isPresent());
        assertEquals("Nueva", resultado.get().getNombre());
    }

    @Test
    void testEliminarTrue() {
        when(promocionRepository.existsById(1L)).thenReturn(true);

        boolean eliminado = promocionService.eliminar(1L);

        assertTrue(eliminado);
        verify(promocionRepository).deleteById(1L);
    }

    @Test
    void testEliminarFalse() {
        when(promocionRepository.existsById(100L)).thenReturn(false);

        boolean eliminado = promocionService.eliminar(100L);

        assertFalse(eliminado);
        verify(promocionRepository, never()).deleteById(any());
    }
}
