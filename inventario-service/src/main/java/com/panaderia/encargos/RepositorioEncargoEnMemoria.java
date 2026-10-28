package com.panaderia.encargos;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del repositorio que guarda los encargos en una lista
 * dentro de la memoria del programa.
 *
 * Sirve para las pruebas y para avanzar sin base de datos: al cerrar el
 * programa, los encargos se pierden.
 */
public class RepositorioEncargoEnMemoria implements RepositorioEncargo {

    // La "base de datos" de mentiras: una lista que arranca vacía.
    // final: la lista no se reemplaza por otra, pero sí se le pueden agregar elementos.
    private final List<Encargo> encargos = new ArrayList<>();



    // @Override: confirma que este método cumple lo que pide la interfaz.
    // Si el nombre o los parámetros no coincidieran, el compilador avisaría.
    @Override
    public boolean existePendiente(String documentoCliente) {
        // Se recorre la lista buscando un encargo del mismo cliente.
        // Como Encargo todavía no tiene estado (pendiente/entregado), por
        // ahora CUALQUIER encargo guardado cuenta como pendiente.
        for (Encargo encargo : encargos) {
            // Los String se comparan con equals (contenido), nunca con ==
            if (encargo.getDocumentoCliente().equals(documentoCliente)) {
                return true;   // apenas se encuentra uno, no hace falta seguir buscando
            }
        }
        return false;   // se revisó toda la lista y no había ninguno
    }

    @Override
    public void guardar(Encargo encargo) {
        encargos.add(encargo);   // se agrega al final de la lista
    }
}
