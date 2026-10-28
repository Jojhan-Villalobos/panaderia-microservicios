package com.panaderia.encargos;

// BigDecimal: decimales exactos, el tipo correcto para manejar dinero
import java.math.BigDecimal;

/**
 * El cajero es quien atiende al cliente y registra sus encargos
 * (por ejemplo, una torta para el sábado).
 *
 * Es un "servicio de dominio": no guarda datos propios, aplica la regla de
 * negocio (un cliente no puede tener dos encargos pendientes) y le pide al
 * repositorio que guarde el encargo.
 */
public class Cajero {

// Mensaje como constante pública: así el test compara contra la MISMA
// constante y no contra un texto copiado que podría quedar desactualizado.
public static final String MENSAJE_ENCARGO_PENDIENTE = "El cliente ya tiene un encargo pendiente";

// El cajero depende de la INTERFAZ, no de una clase concreta.
// Hoy recibe la versión en memoria; mañana podría ser una con base de
// datos y el Cajero no tendría que cambiar ni una línea.
// final: una vez asignado en el constructor, no se puede reemplazar.
private final RepositorioEncargo  repositorioEncargo;

    /**
     * Inyección de dependencias por constructor: el cajero no crea su
     * repositorio, se lo entregan desde afuera (en el test, o luego Spring).
     *
     * @param repositorioEncargo dónde se consultan y guardan los encargos
     */
    public Cajero(RepositorioEncargo repositorioEncargo) {
        this.repositorioEncargo = repositorioEncargo;
    }


    /**
     * Registra un encargo nuevo para un cliente.
     *
     * @param documentoCliente   documento que identifica al cliente
     * @param descripcionEncargo qué pidió, ej: "torta de chocolate"
     * @param valor              precio del encargo
     * @throws EncargoException si el cliente ya tiene un encargo pendiente
     */
    public void registrarEncargo(String  documentoCliente, String descripcionEncargo, BigDecimal valor) {
        // Regla de negocio: primero se valida y solo si pasa se guarda.
        // Si el cliente ya tiene un encargo pendiente, se corta aquí con una
        // excepción y el repositorio queda intacto.
        if (this.repositorioEncargo.existePendiente(documentoCliente)){
            throw new EncargoException(MENSAJE_ENCARGO_PENDIENTE);
        }
        // Pasó la validación: se crea el encargo y se guarda
        Encargo encargo = new Encargo(documentoCliente, descripcionEncargo, valor);
        repositorioEncargo.guardar(encargo);
    }
}
