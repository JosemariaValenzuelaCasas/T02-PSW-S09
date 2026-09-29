# Calculadora CI - Integración Continua con Jenkins y Slack

Proyecto de demostración para el reto práctico de integración continua (CI/CD): Desarrollo Java, pruebas automatizadas con JUnit, pipeline en Jenkins y notificaciones de estado en Slack.

---

## 📁 Estructura del Proyecto

```text
calculadora-ci/
├── .gitignore
├── pom.xml
├── README.md
├── Jenkinsfile
└── src/
    ├── main/
    │   └── java/
    │       └── Calculadora.java
    └── test/
        └── java/
            └── CalculadoraTest.java
```

---

## 🛠️ Requisitos Previos

- **Java JDK**: Versión 17 o superior.
- **Apache Maven**: Versión 3.8 o superior.
- **Git**: Instalado y configurado.
- **Jenkins**: Servidor local o remoto.

---

## 🚀 Ejecución y Pruebas Locales

Antes de subir el código al repositorio o ejecutar en Jenkins, valida que las pruebas pasen en local:

```bash
mvn clean test
```

Salida esperada:
```text
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## 📤 Subir a Repositorio en GitHub

1. Inicializar Git y realizar el primer commit:
   ```bash
   git init
   git add .
   git commit -m "feat: proyecto inicial Calculadora con pruebas JUnit y Jenkinsfile"
   ```

2. Vincular el repositorio remoto de GitHub y subir los cambios:
   ```bash
   git branch -M main
   git remote add origin https://github.com/TU_USUARIO/TU_REPOSITORIO.git
   git push -u origin main
   ```

---

## ⚙️ Configuración del Pipeline en Jenkins

1. Abre Jenkins (`http://localhost:8080` o la URL de tu servidor).
2. Selecciona **Nueva Tarea** (New Item).
3. Ingresa el nombre del job: `calculadora-ci`.
4. Elige el tipo **Pipeline** (Tubería) y presiona **OK**.
5. En la sección **Pipeline**:
   - **Definition**: Selecciona *Pipeline script from SCM*.
   - **SCM**: Elige *Git*.
   - **Repository URL**: Pega la URL de tu repositorio de GitHub.
   - **Branch Specifier**: `*/main` (o `*/master`).
   - **Script Path**: `Jenkinsfile`.
6. Haz clic en **Guardar** (Save).
7. Ejecuta la tarea con **Construir ahora** (Build Now).

---

## 💬 Preparación para Integrar con Slack

En el archivo `Jenkinsfile` ya se encuentran preparadas las directivas `post { success { ... } failure { ... } }`:

1. **En Slack**:
   - Crear una aplicación en [Slack API](https://api.slack.com/apps) o instalar la integración **Jenkins CI**.
   - Crear un bot token o token de integración y definir el canal (ej. `#general` o `#qa-ci`).
2. **En Jenkins**:
   - Ir a **Administrar Jenkins > Plugins > Available Plugins**.
   - Buscar e instalar **Slack Notification**.
   - Ir a **Administrar Jenkins > Configurar Sistema > Slack** y configurar el Workspace y Credential (Secret text con el Token).
3. **En Jenkinsfile**:
   - Descomentar las líneas `slackSend(...)` en las secciones `success` y `failure`.

---

## 🧪 Validación de Escenarios del Reto

- **Escenario 1 (SUCCESS)**:
  Ejecutar el pipeline normalmente; todas las pruebas pasan y se obtiene `BUILD SUCCESS`.

- **Escenario 2 (FAILURE - Fallo Provocado)**:
  Modificar temporalmente una aserción en `src/test/java/CalculadoraTest.java` (por ejemplo cambiar `assertEquals(8, calc.sumar(5, 3));` por `assertEquals(9, ...);`), hacer commit y push. Jenkins detectará el fallo y notificará `BUILD FAILURE`.

- **Recuperación**:
  Corregir la aserción a su valor original, hacer push y verificar que el build vuelva a ser `BUILD SUCCESS`.
