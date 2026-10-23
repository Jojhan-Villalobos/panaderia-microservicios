package com.panaderia.inventario;

// BigDecimal: clase de Java para manejar decimales exactos (ideal para dinero)
import java.math.BigDecimal;

/**
 * Representa un producto que vende la panadería (la "etiqueta del estante").
 *
 * Es una clase de dominio "rica": no solo guarda datos, también contiene
 * las reglas de negocio (validaciones y movimientos de stock). Así ningún
 * otro código puede dejar un Producto en un estado inválido.
 */
public class Producto {

    // --- Atributos: privados, nadie de afuera los cambia directamente ---
    // Al ser private, la única forma de modificarlos es a través de los
    // métodos de esta clase, que validan antes de cambiar algo (encapsulamiento).
    private Long id;                  // lo asignará la base de datos; null mientras no se guarde
    private String nombre;            // ej: "Leche entera 1 L"
    private TipoProducto tipo;        // REVENTA o ELABORADO
    private BigDecimal precioVenta;   // dinero: siempre BigDecimal, nunca double
    private BigDecimal costo;         // lo que nos cuesta comprarlo
    private int stock;                // unidades disponibles
    private boolean activo;           // borrado lógico: false = inhabilitado

    /**
     * Constructor: la única puerta para crear un Producto, y valida todo.
     *
     * Si algún dato es inválido lanza IllegalArgumentException y el objeto
     * nunca llega a existir. Por eso, todo Producto que exista es válido.
     *
     * @param nombre      nombre visible del producto (no puede ser vacío)
     * @param tipo        si es de REVENTA o ELABORADO (obligatorio)
     * @param precioVenta precio al público (debe ser mayor a cero)
     * @param costo       lo que cuesta adquirirlo (puede ser cero, no negativo)
     */
    public Producto(String nombre, TipoProducto tipo, BigDecimal precioVenta, BigDecimal costo) {
        // isBlank() también rechaza textos que solo tienen espacios, ej: "   "
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de producto es obligatorio");
        }
        // compareTo devuelve: -1 si es menor, 0 si es igual, 1 si es mayor.
        // "<= 0" significa: el precio es cero o negativo -> no se permite.
        // Se usa compareTo y no equals porque equals también compara la
        // cantidad de decimales (1.0 y 1.00 serían "distintos" con equals).
        if (precioVenta == null || precioVenta.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio de venta debe ser mayor a cero");
        }
        // "< 0" significa: solo se rechaza si es negativo (costo cero sí se permite,
        // por ejemplo un producto regalado por el proveedor).
        if (costo == null || costo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El costo no puede ser negativo");
        }
        // Si pasó todas las validaciones, se asignan los valores
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioVenta = precioVenta;
        this.costo = costo;
        this.stock = 0;       // todo producto nace sin unidades
        this.activo = true;   // y habilitado para la venta
        // El id no se asigna aquí: queda en null hasta que la base de datos lo genere
    }

    // --- Comportamiento: las únicas formas de cambiar el stock ---

    /**
     * Llegó mercancía del proveedor: suma unidades al stock.
     *
     * @param cantidad unidades que ingresan (debe ser mayor a cero)
     * @throws IllegalArgumentException si la cantidad es cero o negativa
     */
    public void registrarEntrada(int cantidad) {
        // No tiene sentido registrar una entrada de 0 o de unidades negativas
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        this.stock += cantidad;   // equivale a: stock = stock + cantidad
    }

    /**
     * Se vendieron unidades: resta del stock.
     *
     * @param cantidad unidades vendidas (debe ser mayor a cero)
     * @throws IllegalArgumentException si la cantidad es cero o negativa (dato malo)
     * @throws IllegalStateException    si no hay suficiente stock (el dato es válido,
     *                                  pero el producto no puede atenderlo ahora) y cuando el
     *                                  producto esta desactivado
     */
    public void descontarStock(int cantidad) {
        // Primera regla: la cantidad pedida debe ser positiva
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        // Segunda regla: no se puede vender más de lo que hay.
        // Esto garantiza que el stock nunca quede negativo.
        if (cantidad > this.stock) {
            throw new IllegalStateException(
                    "Stock insuficiente de " + nombre + ": hay " + stock + ", se pidieron " + cantidad);
        }
        // Tercera regla: no se puede vender un producto desactivado
        if ( !this.activo  ) {
            throw new IllegalStateException(
                    "El producto está desactivado: " + nombre);
        }
        this.stock -= cantidad;   // equivale a: stock = stock - cantidad


    }

    /**
     * Calcula la ganancia por unidad = precio de venta - costo.
     *
     * Con BigDecimal no se usan los operadores + o -, sino sus métodos
     * (subtract = restar). El resultado es un nuevo BigDecimal; los
     * originales no cambian porque BigDecimal es inmutable.
     *
     * @return la ganancia de vender una unidad
     */
    public BigDecimal margen() {
        return precioVenta.subtract(costo);
    }

    /**
     * Borrado lógico: el producto no se elimina, se inhabilita.
     *
     * Se conserva para no perder el historial (por ejemplo, ventas pasadas
     * que hacen referencia a este producto).
     */
    public void desactivar() {
        this.activo = false;
    }

    // --- Getters: permiten LEER, no modificar ---
    // No hay setters a propósito: cualquier cambio debe pasar por los
    // métodos de comportamiento de arriba, que son los que validan.
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public TipoProducto getTipo() { return tipo; }
    public BigDecimal getPrecioVenta() { return precioVenta; }
    public BigDecimal getCosto() { return costo; }
    public int getStock() { return stock; }
    public boolean isActivo() { return activo; }   // en boolean, la convención es "is" en vez de "get"
}
