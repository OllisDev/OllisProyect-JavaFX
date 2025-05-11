# PixelCoinsLauncher - OllisProyect

Este proyecto es una aplicación de escritorio desarrollada en **JavaFX** que permite a los usuarios gestionar juegos, registrar sesiones de juego y realizar otras funcionalidades relacionadas con la experiencia de usuario en un entorno gráfico.

## Características principales

- **Gestión de usuarios**:

  - Registro de nuevos usuarios.
  - Inicio de sesión.
  - Visualización y edición de información del usuario.

- **Gestión de juegos**:

  - Registro de nuevos juegos con nombre, género y ruta ejecutable.
  - Eliminación de juegos registrados.
  - Ejecución de juegos desde la aplicación.

- **Sesiones de juego**:

  - Registro de sesiones de juego con tiempo jugado y monedas ganadas.
  - Visualización de resúmenes de sesiones de juego.

- **Tienda virtual**:

  - Visualización del saldo de monedas del usuario.
  - Funcionalidad futura para comprar productos virtuales.

- **Sistema anti-AFK**:
  - Detección de inactividad del usuario durante las sesiones de juego.

## Estructura del proyecto

El proyecto está organizado en los siguientes paquetes:

### 1. **`com.project`**

Contiene las clases principales de la aplicación:

- `MainWindow`: Clase principal que gestiona la ventana inicial de la aplicación.
- `PixelCoinsWindow`: Clase que gestiona las funcionalidades principales de la aplicación, como la gestión de juegos y sesiones.

### 2. **`com.project.model`**

Contiene las clases del modelo de datos:

- `User`: Representa un usuario del sistema.
- `Game`: Representa un juego registrado en el sistema.
- `GameSession`: Representa una sesión de juego registrada.

### 3. **`com.project.repository`**

Contiene las clases que gestionan el almacenamiento de datos:

- `GameRepository`: Gestiona la lista de juegos registrados.
- `GameSessionRepository`: Gestiona las sesiones de juego registradas.

### 4. **`com.project.utils`**

Contiene utilidades y herramientas auxiliares:

- `WindowsChecker`: Detecta la ventana activa del sistema.
- `GlobalMouseListener`: Detecta la actividad del ratón.

### 5. **`src/test/java`**

Contiene las pruebas unitarias y funcionales del proyecto:

- `MainWindowTest`: Pruebas para la clase `MainWindow`.
- `PixelCoinsWindowTest`: Pruebas para la clase `PixelCoinsWindow`.

## Requisitos del sistema

- **Java**: JDK 11 o superior.
- **JavaFX**: Versión 11 o superior.
- **Maven**: Para gestionar las dependencias y construir el proyecto.

## Instalación y ejecución

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/usuario/proyecto-ollis-javafx.git
   cd proyecto-ollis-javafx

   ```

2. **Construir el proyecto con Maven**

   ```bash
   mvn clean install

   ```

3. **Ejecutar la aplicación**

   ```bash
   mvn javafx:run

   ```

## Uso de la aplicación

1. **Inicio de sesión**

   - Introduce tu nombre de usuario y contraseña para acceder a la aplicación.
   - Si no tienes una cuenta, regístrate desde la opción de registro.

2. **Gestión de juegos**

   - Registra nuevos juegos proporcionando el nombre, género y ruta ejecutable.
   - Ejecuta juegos directamente desde la aplicación.
   - Elimina juegos que ya no necesites.

3. **Sesiones de juego**

   - Visualiza el tiempo jugado y las monedas ganadas al finalizar una sesión.

4. **Tienda virtual**
   - Consulta tu saldo de monedas y accede a funcionalidades futuras de la tienda.

## Pruebas

El proyecto incluye pruebas unitarias y funcionales para garantizar la calidad del código. Para ejecutar las pruebas, utiliza el siguiente comando:

```bash
   mvn test

```

## Tecnologías utilizadas

- JavaFX: Para la interfaz gráfica de usuario.
- JUnit 5: Para las pruebas unitarias.
- TestFX: Para las pruebas funcionales de la interfaz gráfica.
- Maven: Para la gestión de dependencias y construcción del proyecto.

## Autor

- Nombre del autor: OllisDev
- GitHub: https://github.com/ollisdev
