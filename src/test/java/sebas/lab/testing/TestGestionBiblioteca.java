package sebas.lab.testing;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import static org.mockito.Mockito.*;

public class TestGestionBiblioteca {
    
    private GestionBiblioteca biblioteca;
    
    @Before
    public void setUp() {
        biblioteca = new GestionBiblioteca();
    }
    
    @Test
    public void testCalcularPrecioSinDescuento() {
        double precioBase = 100.0;
        double descuento = 0.0;
        double resultado = biblioteca.calcularPrecioConDescuento(precioBase, descuento);
        assertEquals(100.0, resultado, 0.01);
    }
    
    @Test
    public void testCalcularPrecioCon50PorCientoDescuento() {
        double precioBase = 100.0;
        double descuento = 50.0;
        double resultado = biblioteca.calcularPrecioConDescuento(precioBase, descuento);
        assertEquals(50.0, resultado, 0.01);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalcularPrecioConDescuentoMayorA100() {
        biblioteca.calcularPrecioConDescuento(100.0, 150.0);
    }
    
    @Test
    public void testLibroNoDisponible() {
        boolean disponible = biblioteca.estaDisponible("El Quijote");
        assertFalse(disponible);
    }
    
    @Test
    public void testLibroDisponibleDespuesDeAgregar() {
        biblioteca.agregarLibro("Cien años de soledad");
        boolean disponible = biblioteca.estaDisponible("Cien años de soledad");
        assertTrue(disponible);
    }
    
    @Test
    public void testAgregarLibroExitosamente() {
        boolean resultado = biblioteca.agregarLibro("1984");
        assertTrue(resultado);
        assertTrue(biblioteca.estaDisponible("1984"));
    }
    
    @Test
    public void testAgregarLibroDuplicado() {
        biblioteca.agregarLibro("Harry Potter");
        boolean resultado = biblioteca.agregarLibro("Harry Potter");
        assertFalse(resultado);
    }
    
    @Test
    public void testCategoriaLectorPrincipiante() {
        String categoria = biblioteca.obtenerCategoriaLector(0);
        assertEquals("Principiante", categoria);
    }
    
    @Test
    public void testCategoriaLectorIntermedio() {
        String categoria = biblioteca.obtenerCategoriaLector(5);
        assertEquals("Intermedio", categoria);
    }
    
    @Test
    public void testCategoriaLectorAvanzado() {
        String categoria = biblioteca.obtenerCategoriaLector(25);
        assertEquals("Avanzado", categoria);
    }
    
    @Test
    public void testObtenerLibrosDisponiblesNuncaRetornaNull() {
        List<String> libros = biblioteca.obtenerLibrosDisponibles();
        assertNotNull(libros);
    }
    
    @Test
    public void testObtenerLibrosDisponiblesContieneLibrosAgregados() {
        biblioteca.agregarLibro("El Principito");
        biblioteca.agregarLibro("Don Quijote");
        List<String> libros = biblioteca.obtenerLibrosDisponibles();
        assertEquals(2, libros.size());
        assertTrue(libros.contains("El Principito"));
        assertTrue(libros.contains("Don Quijote"));
    }
    
    @Test
    public void testGetUserNameUsuarioExiste() {
        UserRepository mockRepository = mock(UserRepository.class);
        User usuario = new User("123", "Juan");
        when(mockRepository.findById("123")).thenReturn(usuario);
        UserService userService = new UserService(mockRepository);
        String nombre = userService.getUserName("123");
        assertEquals("Juan", nombre);
        verify(mockRepository, times(1)).findById("123");
    }
    
    @Test
    public void testGetUserNameUsuarioNoExiste() {
        UserRepository mockRepository = mock(UserRepository.class);
        when(mockRepository.findById("999")).thenReturn(null);
        UserService userService = new UserService(mockRepository);
        try {
            userService.getUserName("999");
            fail("Debería lanzar IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("User not found", e.getMessage());
        }
        verify(mockRepository, times(1)).findById("999");
    }
}
