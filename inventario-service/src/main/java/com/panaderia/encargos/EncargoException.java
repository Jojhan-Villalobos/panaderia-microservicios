package com.panaderia.encargos;

/**
 * Error propio del negocio de encargos (ej: "el cliente ya tiene un
 * encargo pendiente").
 *
 * Extiende RuntimeException (excepción "no chequeada"): quien llama a
 * registrarEncargo no está obligado a escribir try/catch ni "throws".
 * Tener una excepción propia permite distinguir un error de negocio de
 * cualquier otro error inesperado y atraparlo por separado más adelante.
 */
public class EncargoException extends RuntimeException{


    /**
     * @param message explicación del error; se recupera con getMessage()
     */
    public EncargoException(String message){
        // super(...) llama al constructor de RuntimeException, que es
        // quien guarda el mensaje
        super(message);
    }
}
