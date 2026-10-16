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
- [ ] Crear `docs/requisitos.md` y hacer commit.
- [ ] Crear `docs/modelo-datos.md` (diagrama Mermaid) y hacer commit.
- [ ] Responder las 3 preguntas del modelo de datos.
- [ ] Reorganizar issues y tablero con el plan de 4 semanas.
