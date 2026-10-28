package com.panaderia.encargos;


/**
 * Contrato de "dónde se guardan los encargos".
 *
 * Es una interfaz: dice QUÉ se puede hacer, pero no CÓMO. Cada
 * implementación decide el cómo (una lista en memoria, una base de datos...).
 * Gracias a esto el {@link Cajero} no sabe ni le importa dónde quedan
 * guardados los encargos.
 */
public interface RepositorioEncargo {

    /**
     * @param documentoCliente documento del cliente a consultar
     * @return true si el cliente ya tiene un encargo pendiente
     */
    boolean existePendiente(String documentoCliente);

    /**
     * Guarda un encargo nuevo.
     *
     * @param encargo el encargo a guardar
     */
    void guardar(Encargo encargo);

}
