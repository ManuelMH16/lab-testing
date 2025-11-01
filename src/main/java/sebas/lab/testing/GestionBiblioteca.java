package sebas.lab.testing;

import java.util.ArrayList;
import java.util.List;

public class GestionBiblioteca {
    
    private List<String> librosDisponibles;
    private static final int CAPACIDAD_MAXIMA = 100;
    
    public GestionBiblioteca() {
        this.librosDisponibles = new ArrayList<>();
    }
    

    public double calcularPrecioConDescuento(double precioBase, double porcentajeDescuento) {
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio base no puede ser negativo");
        }
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100");
        }
        return precioBase - (precioBase * porcentajeDescuento / 100);
    }
    
    public boolean estaDisponible(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return false;
        }
        return librosDisponibles.contains(titulo.trim());
    }
    
    public boolean agregarLibro(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return false;
        }
        if (librosDisponibles.size() >= CAPACIDAD_MAXIMA) {
            return false;
        }
        if (librosDisponibles.contains(titulo.trim())) {
            return false;
        }
        return librosDisponibles.add(titulo.trim());
    }
    
    public String obtenerCategoriaLector(int librosLeidos) {
        if (librosLeidos < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        if (librosLeidos == 0) {
            return "Principiante";
        } else if (librosLeidos <= 10) {
            return "Intermedio";
        } else if (librosLeidos <= 50) {
            return "Avanzado";
        } else {
            return "Experto";
        }
    }
    
    public List<String> obtenerLibrosDisponibles() {
        return new ArrayList<>(librosDisponibles);
    }
    
    public int getCantidadLibros() {
        return librosDisponibles.size();
    }
}
