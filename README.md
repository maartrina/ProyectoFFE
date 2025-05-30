# 📺 Aplicación de Gestión de Series y Plataformas

Este proyecto es una aplicación de escritorio hecha en Java con Swing, que permite **registrar, editar, eliminar y consultar series y plataformas** de una base de datos Oracle.

## 🛠️ Requisitos

- Java JDK 8 o superior
- Oracle Database (se recomienda Oracle XE)
- Eclipse o cualquier otro IDE para Java
- Conexión a la base de datos (puede ser local o desde una VM)
- Archivo `ojdbc8.jar` (driver de Oracle) añadido al classpath del proyecto

---

## ▶️ ¿Cómo ejecutar la aplicación?

1. **Clonar o descargar** el proyecto.
2. Abrirlo en Eclipse (o tu IDE).
3. Asegúrate de tener **el archivo `ojdbc8.jar`** en tu proyecto y configurado como biblioteca externa.
4. Configura correctamente los datos de conexión en la clase `GestorBaseDatos` si es necesario.
5. Ejecuta la clase `Main.java` desde el paquete `vista`.

---

## 🧩 ¿Qué puedes hacer con esta app?

### 📌 Plataformas
- Registrar una nueva plataforma indicando nombre y país de origen.
- Editar los datos de una plataforma ya existente.
- Eliminar una plataforma (si no tiene series asociadas).

### 📌 Series
- Registrar una nueva serie con título, género, número de temporadas, año de lanzamiento y plataforma.
- Editar series registradas.
- Eliminar series que ya no quieras guardar.
- Consultar series aplicando filtros de búsqueda por título o género.

---

## 🧪 Pruebas

El proyecto incluye **pruebas unitarias básicas** usando JUnit para los controladores.

Para ejecutarlas:
- Botón derecho sobre la clase de prueba → `Run As → JUnit Test`.

---

## 🗃️ Estructura de carpetas
📦 src
├── 📁 bd
│   └── GestorBaseDatos.java
├── 📁 controlador
│   ├── PlataformaControlador.java
│   └── SerieControlador.java
├── 📁 dao
│   ├── PlataformaDAO.java
│   └── SerieDAO.java
├── 📁 modelo
│   ├── Plataforma.java
│   └── Serie.java
├── 📁 vista
│   ├── Main.java
│   ├── PanelConsulta.java
│   ├── PanelEditarPlataforma.java
│   ├── PanelEditarSerie.java
│   ├── PanelEliminarPlataforma.java
│   ├── PanelEliminarSerie.java
│   ├── PanelRegistroPlataforma.java
│   └── PanelRegistroSerie.java

## 👩‍💻 Desarrollado por
Martina Sáez alcaide 1º de DAM.  
Proyecto alternativo FFE.
