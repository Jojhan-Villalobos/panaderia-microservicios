// Mismo paquete que Cajero, pero en src/test: las pruebas viven al lado
// de la clase que prueban sin mezclarse con el código de producción.
package com.panaderia.encargos;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
// import static: permite escribir assertTrue(...) en vez de Assertions.assertTrue(...)
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


// Pruebas de la regla de negocio del Cajero: un cliente solo puede
// tener un encargo pendiente a la vez.
public class CajeroTest {


// Regla: al registrar el primer encargo, queda guardado como pendiente
@Test
public void registrarPrimerEncargoDejaPendiente() {

    // 1. Preparar: repositorio en memoria (vacío) y un cajero que lo usa.
    // Se usa la versión en memoria para no depender de una base de datos.
    RepositorioEncargoEnMemoria repositorio = new RepositorioEncargoEnMemoria();
    Cajero cajero = new Cajero(repositorio);
    // 2. Actuar: el cliente "1" encarga una torta
    cajero.registrarEncargo("1", "torta", new BigDecimal("30000"));
    // 3. Verificar: se le pregunta directamente al repositorio si quedó pendiente
    assertTrue(repositorio.existePendiente("1"));

}
// Regla: si el cliente ya tiene un encargo pendiente, el segundo se rechaza
@Test
public void segundoEncargoLanzaExcepcion() {

    // 1. Preparar: el cliente "1" ya tiene un encargo registrado
    RepositorioEncargoEnMemoria repositorio = new RepositorioEncargoEnMemoria();
    Cajero cajero = new Cajero(repositorio);
    cajero.registrarEncargo("1", "torta", new BigDecimal("30000"));
    // 2. Actuar: intenta un segundo encargo. Va dentro de una lambda para que
    // assertThrows pueda atrapar la excepción; además devuelve el error lanzado.
    EncargoException error = assertThrows(EncargoException.class, () -> cajero.registrarEncargo("1", "torta", new BigDecimal("30000")));
    // 3. Verificar: no solo que falle, sino que falle por la razón correcta
    assertEquals(Cajero.MENSAJE_ENCARGO_PENDIENTE, error.getMessage());
}

}
