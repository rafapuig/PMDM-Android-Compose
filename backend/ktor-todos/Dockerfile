# --- FASE 1: CONSTRUCCIÓN (Build Stage) ---
FROM gradle:8.5.0-jdk17 AS build

WORKDIR /home/gradle/src

COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src

# --- ¡AQUÍ ESTÁ LA MODIFICACIÓN! ---
# Añadimos opciones para limitar el uso de memoria de Gradle y la JVM.
# -Dorg.gradle.jvmargs="-Xmx512m": Limita la memoria máxima de la JVM de Gradle a 512 MB.
# --no-daemon: Evita que Gradle mantenga un proceso en segundo plano (buena práctica en CI/CD).
# --parallel=false: Desactiva la construcción en paralelo para reducir el consumo de RAM.
# --max-workers=1: Limita a un solo worker.
RUN gradle shadowJar --no-daemon --parallel=false --max-workers=1 -Dorg.gradle.jvmargs="-Xmx512m -XX:MaxMetaspaceSize=256m"

# --- FASE 2: EJECUCIÓN (Runtime Stage) ---
FROM eclipse-temurin:17-jre-jammy

EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "app.jar"]





