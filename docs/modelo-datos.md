\# Modelo de datos - MVP



```mermaid

erDiagram

&#x20;   USUARIO ||--o{ VENTA : registra

&#x20;   USUARIO ||--o{ MOVIMIENTO\_INVENTARIO : registra

&#x20;   USUARIO ||--o{ CIERRE\_CAJA : realiza

&#x20;   VENTA ||--|{ DETALLE\_VENTA : contiene

&#x20;   PRODUCTO ||--o{ DETALLE\_VENTA : aparece\_en

&#x20;   PRODUCTO ||--o{ MOVIMIENTO\_INVENTARIO : tiene



&#x20;   USUARIO {

&#x20;       bigint id PK

&#x20;       varchar nombre

&#x20;       varchar username

&#x20;       varchar password\_hash

&#x20;       varchar rol

&#x20;       boolean activo

&#x20;   }

&#x20;   PRODUCTO {

&#x20;       bigint id PK

&#x20;       varchar nombre

&#x20;       varchar tipo

&#x20;       numeric precio\_venta

&#x20;       numeric costo

&#x20;       int stock

&#x20;       boolean activo

&#x20;   }

&#x20;   VENTA {

&#x20;       bigint id PK

&#x20;       timestamp fecha

&#x20;       bigint usuario\_id FK

&#x20;       varchar medio\_pago

&#x20;       numeric total

&#x20;       varchar estado

&#x20;   }

&#x20;   DETALLE\_VENTA {

&#x20;       bigint id PK

&#x20;       bigint venta\_id FK

&#x20;       bigint producto\_id FK

&#x20;       int cantidad

&#x20;       numeric precio\_unitario

&#x20;   }

&#x20;   MOVIMIENTO\_INVENTARIO {

&#x20;       bigint id PK

&#x20;       bigint producto\_id FK

&#x20;       varchar tipo

&#x20;       int cantidad

&#x20;       numeric costo\_unitario

&#x20;       timestamp fecha

&#x20;       bigint usuario\_id FK

&#x20;   }

&#x20;   CIERRE\_CAJA {

&#x20;       bigint id PK

&#x20;       date fecha

&#x20;       bigint usuario\_id FK

&#x20;       numeric efectivo\_esperado

&#x20;       numeric efectivo\_contado

&#x20;       numeric total\_nequi

&#x20;       numeric total\_daviplata

&#x20;   }

```

