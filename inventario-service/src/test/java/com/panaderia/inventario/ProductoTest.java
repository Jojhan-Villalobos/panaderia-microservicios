package com.panaderia.inventario;

import org.hibernate.validator.internal.util.ReflectionHelper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;   // trae assertEquals, assertThrows, assertFalse...

// Pruebas automáticas de la clase Producto
class ProductoTest {

    // Apoyo: crea una leche nueva para cada prueba
    private Producto crearLeche() {
        return new Producto("Leche entera 1 L", TipoProducto.REVENTA,
                new BigDecimal("4500"), new BigDecimal("3600"));
    }

    // ===== EJEMPLO RESUELTO =====
    @Test
    void entradaYVentaActualizanElStock() {
        Producto leche = crearLeche();          // 1. Preparar
        leche.registrarEntrada(10);             // 2. Actuar
        leche.descontarStock(3);
        assertEquals(7, leche.getStock());      // 3. Verificar
    }

    // ===== TU TURNO =====

    @Test
    void noSePuedeVenderMasDeLoQueHay() {
        Producto leche = crearLeche();          // 1. Preparar
        assertThrows(IllegalStateException.class, () -> leche.descontarStock(1));
    }

    @Test
    void elMargenEsPrecioMenosCosto() {
        Producto leche = crearLeche(); // 1. Preparar
        BigDecimal margenLeche = leche.margen();
        assertEquals(new BigDecimal("900"), margenLeche );
    }

    @Test
    void noSePuedeCrearConPrecioCero() {
        assertThrows(IllegalArgumentException.class, () -> new Producto("Leche entera 1 L",
                TipoProducto.REVENTA, new BigDecimal("0"), new BigDecimal("3600")));

    }

    @Test
    void desactivarNoBorraElProducto() {
        Producto leche = crearLeche();
        leche.desactivar();
        assertFalse(leche.isActivo());
        assertEquals("Leche entera 1 L", leche.getNombre());
    }

    @Test
    void noSePuedeVenderUnProductoDesactivado() {
        Producto leche = crearLeche();
        leche.registrarEntrada(10);
        leche.desactivar();
        assertThrows(IllegalStateException.class, () -> leche.descontarStock(5));
        assertEquals(10,leche.getStock());
    }
}