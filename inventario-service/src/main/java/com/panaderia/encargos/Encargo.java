package com.panaderia.encargos;

// BigDecimal: decimales exactos, el tipo correcto para manejar dinero
import java.math.BigDecimal;

/**
 * Un pedido que un cliente deja hecho para recoger después
 * (ej: "torta de cumpleaños para el sábado").
 *
 * Por ahora solo guarda datos; la regla "un solo encargo pendiente por
 * cliente" la aplica el {@link Cajero}.
 */
public class Encargo {

    // Atributos privados: solo se leen con los getters de abajo
    private String documentoCliente;     // identifica a quién pertenece el encargo
    private String descripcionEncargo;   // qué se pidió, ej: "torta"
    private BigDecimal valor;            // precio del encargo (dinero: BigDecimal)

    /**
     * Crea un encargo con todos sus datos.
     *
     * @param documentoCliente   documento del cliente
     * @param descripcionEncargo descripción de lo que se pidió
     * @param valor              precio del encargo
     */
    public Encargo(String documentoCliente, String descripcionEncargo, BigDecimal valor) {
        this.documentoCliente = documentoCliente;
        this.descripcionEncargo = descripcionEncargo;
        this.valor = valor;
    }

    // --- Getters: permiten LEER, no modificar (no hay setters) ---

    public String getDocumentoCliente() {
        return documentoCliente;
    }

    public String getDescripcionEncargo() {
        return descripcionEncargo;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
