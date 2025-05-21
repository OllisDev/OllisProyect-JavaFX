# PixelCoinsLauncher

PixelCoinsLauncher es una aplicación de escritorio desarrollada en JavaFX que permite a los usuarios gestionar su biblioteca de juegos, registrar sesiones de juego, ganar monedas virtuales (PixelCoins) y acceder a una tienda interna. Incluye funcionalidades de registro, inicio de sesión, sistema anti-AFK y gestión de usuarios y juegos.

## Características

- Registro y autenticación de usuarios.
- Gestión de biblioteca de juegos por usuario.
- Ejecución de juegos y registro de sesiones.
- Sistema de monedas virtuales (PixelCoins) basado en el tiempo jugado.
- Tienda interna para gastar monedas.
- Sistema anti-AFK para evitar la acumulación de monedas sin actividad.
- Interfaz gráfica moderna y animada con JavaFX.
- Persistencia de datos en base de datos.

## Estructura del Proyecto

```
ollisproyect/
│
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/project/
│   │   │       ├── MainWindow.java
│   │   │       ├── PixelCoinsWindow.java
│   │   │       ├── model/
│   │   │       └── repository/
│   │   └── resources/
│   │       └── com/project/
│   │           └── images/
│   │           └── styles/
│   └── test/
│       └── java/
│           ├── MainWindowTest.java
│           └── PixelCoinsWindowTest.java
└── target/
```

## Instalación y Ejecución

### 1. Requisitos

- Java 17 o superior
- Maven 3.6+
- JavaFX SDK (si usas JDK sin JavaFX incluido)
- Base de datos compatible (ej. MySQL, SQLite, etc.)

### 2. Compilar el proyecto

Desde la carpeta `ollisproyect/`:

```sh
mvn clean package
```

### 3. Ejecutar la aplicación

```sh
mvn javafx:run
```

O ejecuta el JAR generado:

```sh
java -jar target/ollisproyect-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### 4. Crear instalador .exe (Windows)

Utiliza `jpackage` para generar el instalador ejecutable:

```sh
jpackage ^
  --type exe ^
  --input target ^
  --name PixelCoinsLauncher ^
  --main-jar ollisproyect-1.0-SNAPSHOT-jar-with-dependencies.jar ^
  --main-class com.project.MainWindow ^
  --java-options "--add-opens=java.base/java.lang=ALL-UNNAMED" ^
  --icon src/main/resources/com/project/images/icon.ico ^
  --dest dist
```

Ajusta las rutas según tu entorno y asegúrate de incluir JavaFX en el classpath si es necesario.

## Pruebas

Las pruebas unitarias están en [`src/test/java`](src/test/java):

```sh
mvn test
```

## Principales Clases

- [`com.project.MainWindow`](src/main/java/com/project/MainWindow.java): Ventana principal, registro e inicio de sesión.
- [`com.project.PixelCoinsWindow`](src/main/java/com/project/PixelCoinsWindow.java): Ventana principal tras login, gestión de juegos, tienda y cuenta.
- [`com.project.model.User`](src/main/java/com/project/model/User.java): Modelo de usuario.
- [`com.project.model.Game`](src/main/java/com/project/model/Game.java): Modelo de juego.
- [`com.project.repository.UserRepository`](src/main/java/com/project/repository/UserRepository.java): Acceso a datos de usuarios.
- [`com.project.repository.GameRepository`](src/main/java/com/project/repository/GameRepository.java): Acceso a datos de juegos.

## Créditos

Desarrollado por OllisDev.

## Proximamente

- Aplicación web.
- Aplicación movil.
- Artículos en la tienda.

---

**Nota:** Si tienes problemas con rutas de recursos o dependencias de JavaFX, revisa la configuración de tu IDE y el archivo [`pom.xml`](pom.xml).
