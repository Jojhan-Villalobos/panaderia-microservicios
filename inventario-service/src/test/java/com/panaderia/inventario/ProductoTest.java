// Mismo paquete que Producto, pero en la carpeta src/test (verde en IntelliJ).
// Así las pruebas "viven al lado" de la clase que prueban sin mezclarse
// con el código que se entrega a la panadería.
package com.panaderia.inventario;

// @Test: la anotación de JUnit 5 que marca un método como prueba
import org.junit.jupiter.api.Test;

// BigDecimal: para crear precios y comparar el margen (dinero exacto)
import java.math.BigDecimal;

// import static: trae los MÉTODOS de la clase Assertions para usarlos sin
// escribir el nombre de la clase. Gracias a esto se escribe assertEquals(...)
// en vez de Assertions.assertEquals(...). El * trae todos.
import static org.junit.jupiter.api.Assertions.*;   // trae assertEquals, assertThrows, assertFalse...

// Pruebas automáticas de la clase Producto.
// No es public: JUnit 5 no lo exige, con que sea visible en el paquete basta.
// Se ejecuta con el botón ▶ verde al lado del nombre de la clase en IntelliJ.
class ProductoTest {

    // Apoyo (helper): crea una leche NUEVA cada vez que se llama.
    // Cada prueba arranca con su propia leche (stock 0, activa), así una
    // prueba no puede ensuciar el resultado de otra (pruebas independientes).
    // Precio 4500 y costo 3600 -> margen esperado 900.
    private Producto crearLeche() {
        return new Producto("Leche entera 1 L", TipoProducto.REVENTA,
                new BigDecimal("4500"), new BigDecimal("3600"));
    }

    // ===== EJEMPLO RESUELTO =====
    // @Test es una anotación: una etiqueta que le dice a JUnit
    // "este método es una prueba, ejecútalo". Sin ella, JUnit lo ignora.
    @Test
    void entradaYVentaActualizanElStock() {
        // Patrón Preparar / Actuar / Verificar (Arrange / Act / Assert)
        Producto leche = crearLeche();          // 1. Preparar: leche con stock 0
        leche.registrarEntrada(10);             // 2. Actuar: llegan 10 -> stock 10
        leche.descontarStock(3);                //    se venden 3      -> stock 7
        // assertEquals(esperado, real): el ESPERADO va primero.
        // Si falla, JUnit dice "expected: <7> but was: <...>".
        assertEquals(7, leche.getStock());      // 3. Verificar
    }

    // ===== TU TURNO =====

    // Regla: no se puede vender más de lo que hay (stock nunca negativo)
    @Test
    void noSePuedeVenderMasDeLoQueHay() {
        Producto leche = crearLeche();          // 1. Preparar: stock 0
        // assertThrows(TipoDeError.class, código) verifica que el código
        // LANCE ese error. Si no lo lanza, la prueba falla.
        // () -> ... es una lambda: "código guardado para ejecutar después".
        // Hace falta porque si se llamara leche.descontarStock(1) directo, la
        // excepción explotaría ANTES de que JUnit pudiera atraparla.
        // IllegalStateException: el dato (1) es válido, pero el estado
        // (stock 0) no permite la venta.
        assertThrows(IllegalStateException.class, () -> leche.descontarStock(1));
    }

    // Regla: margen = precio de venta - costo
    @Test
    void elMargenEsPrecioMenosCosto() {
        Producto leche = crearLeche(); // 1. Preparar
        BigDecimal margenLeche = leche.margen();   // 2. Actuar: 4500 - 3600
        // 3. Verificar. Cuidado: assertEquals con BigDecimal usa equals, que
        // compara también los decimales (escala): 900 ≠ 900.00.
        // Aquí funciona porque precio y costo no tienen decimales.
        assertEquals(new BigDecimal("900"), margenLeche );
    }

    // Regla del constructor: el precio de venta debe ser mayor a cero
    @Test
    void noSePuedeCrearConPrecioCero() {
        // Aquí lo que falla es el CONSTRUCTOR, por eso el new va dentro de la
        // lambda. IllegalArgumentException: el dato en sí (precio 0) es malo.
        // Si pasa, la leche nunca llega a existir.
        assertThrows(IllegalArgumentException.class, () -> new Producto("Leche entera 1 L",
                TipoProducto.REVENTA, new BigDecimal("0"), new BigDecimal("3600")));

    }

    // Regla: borrado lógico. Desactivar NO elimina, solo inhabilita.
    @Test
    void desactivarNoBorraElProducto() {
        Producto leche = crearLeche();             // 1. Preparar: leche activa
        leche.desactivar();                        // 2. Actuar
        assertFalse(leche.isActivo());             // 3. Verificar: quedó inhabilitada...
        assertEquals("Leche entera 1 L", leche.getNombre());   // ...pero conserva sus datos
    }

    // Regla de Jojhan: no se puede vender un producto desactivado
    @Test
    void noSePuedeVenderUnProductoDesactivado() {
        Producto leche = crearLeche();             // 1. Preparar
        // Se le da stock a propósito: así la ÚNICA razón para fallar es que
        // esté desactivada. Con stock 0 fallaría por "stock insuficiente" y la
        // prueba pasaría por la razón equivocada (falso positivo).
        leche.registrarEntrada(10);
        leche.desactivar();
        // 2 y 3. Actuar + Verificar: vender 5 debe lanzar el error
        assertThrows(IllegalStateException.class, () -> leche.descontarStock(5));
        // Verificar también que el stock NO cambió: como las validaciones
        // van antes del "stock -= cantidad", el objeto queda intacto.
        assertEquals(10,leche.getStock());
    }
}
