# Bitácora del proyecto Panadería

Registro de lo construido en cada sesión: comandos, conceptos y términos en inglés.
Se actualiza durante la sesión y se cierra con un resumen al final.

---

## Sesión 1 — viernes 16 de octubre de 2026

### Contexto y decisiones
- El proyecto pasa de ser práctica a **software real** para una panadería que abrirá en ~1 mes.
- Arquitectura: **monolito modular** primero (una sola app Spring Boot bien organizada). Microservicios después, como práctica para Pragma.
- Interfaz: web adaptable a celular (Spring + Thymeleaf).
- Alcance del **MVP**: productos de reventa, entradas de mercancía, ventas (efectivo/Nequi/Daviplata, sin crédito), cierre de caja diario (una sola caja).
- **v2**: productos elaborados, producción, sobrantes, insumos, recetas, pedidos.
- Roles: GERENTE (todo + usuarios), ADMINISTRADOR, CAJERO, PANADERO.
- `codigo-referencia/` es una solución de referencia: **no se copia**, solo se compara después de intentar.

### Lo construido
| Qué | Dónde |
|---|---|
| Carpeta del proyecto | `C:\Users\gotha\JAVA\panaderia-microservicios` |
| Servicio Spring Boot copiado del proyecto viejo | `inventario-service/` |
| Reglas de archivos ignorados | `.gitignore` |
| Presentación del repo | `README.md` |
| Repositorio remoto | https://github.com/Jojhan-Villalobos/panaderia-microservicios |
| Tablero Kanban | GitHub Projects: `Panadería - 10 días` (se reorganizará con el nuevo plan) |
| Issues #1–#9 | Plan anterior de 10 días (se cerrarán y reemplazarán) |

### Comandos usados (CMD de Windows)
```bat
:: Crear la carpeta del proyecto (ruta ABSOLUTA: lleva \ después de C:)
mkdir "C:\Users\gotha\JAVA\panaderia-microservicios"

:: Entrar a la carpeta; /d permite cambiar también de disco
cd /d "C:\Users\gotha\JAVA\panaderia-microservicios"

:: Borrar carpetas VACÍAS (seguro: si tiene algo adentro, no borra nada)
rmdir "C:\Users\gotha\Users"

:: Copiar carpeta completa (/E) excluyendo carpetas (/XD) target e .idea
robocopy "...\Panaderia\panaderia-api" inventario-service /E /XD target .idea

:: Mover una carpeta (en el mismo disco es instantáneo)
move "C:\Users\gotha\inventario-service" "C:\Users\gotha\JAVA\panaderia-microservicios\inventario-service"

:: Listar contenido; /a muestra también archivos ocultos
dir /a

:: Crear o editar un archivo de texto
notepad .gitignore

:: Ver con qué nombre y correo se firman los commits
git config --global user.name
git config --global user.email

:: Convertir la carpeta en repositorio; la rama principal se llama main
git init -b main

:: Ver el estado: rojo = sin preparar, verde = listo para commit
git status

:: Preparar todos los archivos (menos los del .gitignore)
git add .

:: Guardar un punto en la historia con un mensaje
git commit -m "chore: estructura inicial del monorepo con inventario-service"

:: Ver el historial, una línea por commit
git log --oneline

:: Registrar la dirección de GitHub con el apodo origin
git remote add origin https://github.com/Jojhan-Villalobos/panaderia-microservicios.git

:: Ver los remotos configurados (verificar que NO haya tokens en la URL)
git remote -v

:: Subir main a GitHub; -u deja amarradas la rama local y la remota
git push -u origin main
```

### Errores que cometimos y lo que enseñan
| Error | Causa | Lección |
|---|---|---|
| `The system cannot find the path specified` | Se escribió `C:Users\...` sin `\` | `C:` sin barra = "carpeta actual del disco C" (ruta relativa) |
| `inventario-service` quedó en `C:\Users\gotha` | CMD nuevo arranca en la carpeta del usuario | Mirar el **prompt** antes de cada comando |
| Issue a punto de crearse en `platzi-play` | La página global de Issues elige un repo por defecto | Verificar **dónde** estás antes de actuar |

### Conceptos clave
- **Git**: bodega (carpeta) → muelle de carga (`add`) → camión sellado (`commit`) → despacho (`push`).
- **Ruta absoluta vs relativa**: dirección completa vs "desde donde estoy parado".
- **.gitignore**: lo que no se sube (compilados, config del IDE, contraseñas en `.env`).
- **Conventional Commits**: `feat:`, `fix:`, `chore:`, `docs:`, `test:`.
- **Microservicios**: dividir por área de negocio, cada uno con su BD. No siempre son la mejor opción.
- **Scrum vs Kanban**: sprints fijos vs flujo continuo con límite WIP.
- **Requisitos funcionales vs no funcionales**: qué hace vs cómo se comporta.
- **MVP**: lo mínimo para operar; lo demás después.
- **Mínimo privilegio**: cada rol accede solo a lo que necesita.
- **Modelo de datos**: entidad, atributo, PK, FK, relación 1:N y N:M (tabla intermedia).
- Precio copiado en `detalle_venta` (el pasado no cambia), kárdex, plata en `NUMERIC`/`BigDecimal`, borrado lógico.

### Glosario de términos en inglés
| Término | Significado | Contexto |
|---|---|---|
| `mkdir` (make directory) | crear carpeta | CMD |
| `cd` (change directory) | cambiar de carpeta | CMD |
| `dir` (directory) | listar carpeta | CMD |
| `rmdir` (remove directory) | borrar carpeta vacía | CMD |
| `move` | mover | CMD |
| `robocopy` (robust copy) | copia robusta de carpetas | CMD |
| `repository` / repo | repositorio: carpeta con historial | Git |
| `init` (initialize) | inicializar | `git init` |
| `status` | estado | `git status` |
| `add` | agregar (preparar) | `git add` |
| `commit` | confirmar / guardar un punto en la historia | `git commit` |
| `log` | registro (historial) | `git log` |
| `diff` (difference) | diferencias | `git diff` |
| `remote` | remoto (copia en otro servidor) | `git remote` |
| `origin` | origen: apodo del remoto principal | Git |
| `push` | empujar: subir commits | `git push` |
| `branch` | rama: línea de trabajo | Git |
| `main` | principal: nombre de la rama principal | Git |
| `untracked` | sin seguimiento: Git lo ve pero no lo controla | `git status` |
| `staged` / `to be committed` | preparado para el commit | `git status` |
| `hash` | identificador único de un commit | `git log` |
| `upstream` (`-u`) | rama remota amarrada a la local | `git push -u` |
| `chore` | tarea de mantenimiento | Conventional Commits |
| `issue` | tarea registrada en GitHub | GitHub |
| `label` | etiqueta | GitHub |
| `assignee` | responsable asignado | GitHub |
| `board` | tablero | GitHub Projects |
| `enhancement` | mejora / funcionalidad nueva | Label de GitHub |
| `WIP` (Work In Progress) | trabajo en curso | Kanban |
| `sprint` | ciclo de trabajo de duración fija | Scrum |
| `MVP` (Minimum Viable Product) | producto mínimo viable | Gestión de producto |
| `PK` (Primary Key) | llave primaria | Bases de datos |
| `FK` (Foreign Key) | llave foránea | Bases de datos |
| `soft delete` | borrado lógico (marcar inactivo) | Bases de datos |
| `hash` (de contraseña) | versión cifrada irreversible | Seguridad |
| `Gateway` | puerta de entrada única | Microservicios |
| `service discovery` | directorio de servicios | Microservicios |

### Autoevaluación del modelo de datos
| Pregunta | Resultado | Nota |
|---|---|---|
| ¿Por qué no cambian las ventas de ayer si sube el precio? | Correcta (tras repaso) | Al subir precio se cambia `producto.precio_venta` (etiqueta); al vender se COPIA a `detalle_venta.precio_unitario` (recibo), que nunca cambia |
| ¿Por qué no borrar un producto vendido? | Correcta | Borrado lógico: las ventas apuntarían a un registro inexistente |
| 2 leches + 6 huevos: ¿cuántos registros? | Correcta | 1 venta, 2 detalles, 2 movimientos (uno por renglón, no por unidad) |

### Pendiente
- [x] Crear `docs/requisitos.md` y hacer commit.
- [x] Crear `docs/modelo-datos.md` (diagrama Mermaid) y hacer commit.
- [x] Responder las 3 preguntas del modelo de datos.
- [ ] Reorganizar issues y tablero con el plan de 4 semanas.

### Resumen de cierre — Sesión 1
- **Construido:** repo `panaderia-microservicios` en GitHub con 4 commits, `.gitignore`, `README.md`, `docs/requisitos.md`, `docs/modelo-datos.md` (diagrama Mermaid), esta bitácora, tablero Kanban e issues.
- **Comandos nuevos:** `mkdir`, `cd /d`, `dir /a`, `rmdir`, `robocopy`, `move`, `notepad`, `REM`/`::`, `git init/status/add/commit/log/remote/push/diff`.
- **Conceptos:** Git (bodega → muelle → camión → despacho), rutas absolutas vs relativas, Conventional Commits, microservicios vs monolito, Scrum vs Kanban, requisitos, MVP, mínimo privilegio, modelo de datos (PK, FK, 1:N, N:M), snapshot de precio, kárdex, `NUMERIC`/`BigDecimal`, borrado lógico, integridad referencial.
- **Términos en inglés nuevos al final:** `snapshot` (foto/instantánea de un dato), `REM` (remark: comentario en CMD), integridad referencial (*referential integrity*).
- **Siguiente sesión (Día 3):** Java POO — convertir el modelo en clases Java (`Producto`, `Venta`, `DetalleVenta`) con encapsulamiento y `BigDecimal`.

---

## Sesión 2 — lunes 19 de octubre de 2026 (Día 3: Java POO)

### Lo construido
- Rama `feat/modelo-producto` (Trunk Based: ramas cortas, `main` siempre estable).
- Paquete `com.panaderia.inventario` con `TipoProducto` (enum) y `Producto` (clase encapsulada).
- Regla nueva escrita por Jojhan: **no se puede vender un producto desactivado** (`if (!this.activo)` → `IllegalStateException`, antes de modificar el stock).

### Comandos nuevos
```bat
:: Crear una rama y pasarse a ella (switch = cambiar, -c = create)
git switch -c feat/modelo-producto
:: Descartar cambios de UN archivo y volverlo al último commit
git restore ruta\al\archivo
```

### Configuración de IntelliJ
- "Project JDK is not defined" → **Setup SDK** → temurin-17.
- Carpeta `java` gris (sin New → Package) → clic derecho en `pom.xml` → **Add as Maven Project** → `src/main/java` azul, `src/test/java` verde.
- "Add File to Git" → Cancel + Don't ask again (el `git add` se hace en la terminal).

### Conceptos clave
- **Clase vs objeto:** molde vs ficha llena.
- **Encapsulamiento:** atributos `private`, cambios solo por métodos que validan (sin `setStock`).
- **enum:** lista cerrada de valores (`REVENTA`, `ELABORADO`).
- **Declarar vs llamar un método:** `registrarEntrada(int cantidad)` vs `leche.registrarEntrada(50)`.
- **IllegalArgumentException** (dato malo) vs **IllegalStateException** (dato válido, estado no lo permite).
- **Desactivado ≠ sin stock:** son dos reglas independientes.
- **Guard clauses:** validar primero, modificar después; si algo falla, el objeto queda intacto.
- **`!`** invierte un boolean: `!false` = `true`.
- **Prueba de escritorio:** ejecutar a mano línea por línea; un `if` pregunta, no cambia variables; después de `throw` no se ejecuta nada más.
- **Javadoc:** `/** */`, `@param`, `@throws`.

### Errores y lecciones
| Error | Lección |
|---|---|
| `leche.registrarEntrada(int 50)` | El tipo se escribe al declarar, no al llamar |
| Regla de desactivado mirando `stock` y cambiando `activo` | Cada regla mira solo su dato; vender no apaga productos |
| Validación puesta después de `stock -= cantidad` | Validar SIEMPRE antes de modificar |
| En la prueba de escritorio se anotó el resultado del `if` como valor de `activo` | Columna aparte para "¿Entra?"; solo un `=` cambia variables |

### Términos en inglés
| Término | Significado |
|---|---|
| `package` / `import` | paquete / importar |
| `public` / `private` | público / privado |
| `enum` | enumeración (lista cerrada) |
| `new` / `this` | nuevo (crear objeto) / este objeto |
| `throw` | lanzar un error |
| `return` / `void` | devolver / no devuelve nada |
| getter | método que lee un atributo |
| `isBlank` / `compareTo` / `subtract` | está en blanco / comparar / restar |
| JDK (Java Development Kit) | kit de desarrollo de Java |
| source root | raíz de código fuente |
| `switch` / `restore` | cambiar de rama / restaurar archivo |
| guard clause | cláusula de guarda (validar antes de actuar) |
| desk check | prueba de escritorio |
| Javadoc / `@param` / `@throws` | documentación / parámetro / lanza |

### Pruebas con JUnit 5 (escritas por Jojhan) — 20 al 23 de octubre
- `ProductoTest`: **6 tests passed**.
- Patrón: Preparar → Actuar → Verificar (Arrange, Act, Assert). La prueba es el **inspector**: llama al método real, nunca reescribe la regla con `if`/`throw`.
- `assertEquals(esperado, real)`, `assertTrue/assertFalse`, `assertThrows(Tipo.class, () -> acción)`.
- La acción que debe fallar va **dentro** de `() ->` (caja cerrada); si se ejecuta afuera, explota antes de que JUnit la atrape.
- **Falso positivo:** prueba en verde por la razón equivocada (desactivado con stock 0 lo detenía la regla de stock). Solución: preparar con stock y desactivar.
- **Probar la prueba:** comentar `desactivar()` hizo fallar la prueba ("nothing was thrown") → la prueba sí depende de la regla.
- `crearLeche()` es la **pesa patrón**: las pruebas cambian cuando cambian las **reglas**, no los **datos**.

### Errores y lecciones (pruebas)
| Error | Lección |
|---|---|
| Reescribir la regla con `if`/`throw` dentro de la prueba | La prueba llama al método real y verifica con `assert...` |
| `descontarStock(0)` esperaba `IllegalStateException` | Leer `Caused by:`: el 0 lo atrapó la regla 1 (dato malo). Una prueba = una regla |
| `leche.margen();` sin guardar el resultado | Un valor de retorno se guarda (`BigDecimal m = leche.margen();`) o se usa directo |
| `leche.margen() = ...` | El `=` asigna de derecha a izquierda: a la izquierda va la variable |
| Esperado 4500 en vez de 900 | Leer `Expected`/`Actual` y decidir cuál es el correcto según el negocio |
| Cambiar `crearLeche()` a precio 0 y aceptar el 💡 que agregó un parámetro a `getPrecioVenta` | No tocar la pesa patrón; no aceptar correcciones automáticas sin entenderlas; los getters solo leen |
| `() -> !leche.isActivo()` | Dentro de `assertThrows` va la acción (vender), no una pregunta |
| `new String("...")` | El texto entre comillas ya es un `String` |

### Git: cierre del día 3
```bat
git add .gitignore
git commit -m "chore: ignorar carpeta .ollamassist del IDE"
git add inventario-service/src/main/java/com/panaderia/inventario/
git commit -m "feat: modelo Producto con reglas de stock y producto desactivado"
git add inventario-service/src/test/java/com/panaderia/inventario/
git commit -m "test: pruebas unitarias de Producto con JUnit 5"
:: volver a main
git switch main
:: fast-forward: main avanza hasta la rama
git merge feat/modelo-producto
:: 55117df..21e4cae main -> main
git push
:: -d solo borra ramas ya fusionadas
git branch -d feat/modelo-producto
:: HEAD -> main, origin/main en el mismo commit
git log --oneline -3
```
- Etiquetas de `git log`: `HEAD` = dónde estás; `main` = rama local; `origin/main` = GitHub.
- En CMD los comentarios `::` van SOLOS en su línea (después de un comando se tratan como parte del comando).
- El CMD abierto **como Administrador** arranca en `C:\Windows\System32`: abrir CMD normal y hacer `cd /d` al proyecto.

### Términos en inglés (pruebas y Git)
| Término | Significado |
|---|---|
| assertion | aserción, afirmación verificable |
| expected / actual | esperado / real |
| nothing was thrown | no se lanzó ningún error |
| Caused by | causado por (la pista clave del error) |
| false positive | falso positivo |
| return value | valor de retorno |
| merge | fusionar ramas |
| fast-forward | avance rápido (mover la rama sin crear commit nuevo) |
| HEAD | dónde estás parado en el historial |

### Resumen de cierre — Sesión 2 (Día 3)
- **Construido:** `TipoProducto`, `Producto` (encapsulado, 3 reglas en `descontarStock`), `ProductoTest` (6 pruebas, 5 escritas por Jojhan), fusionado en `main` y subido a GitHub (`21e4cae`).
- **Siguiente (Día 4):** colecciones y excepciones propias — `Venta` y `DetalleVenta` (un carrito con varios renglones), `StockInsuficienteException`.
