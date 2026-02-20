🧠 Idea clave con Koin

Todo se define en módulos Kotlin

ViewModels se inyectan desde Compose

🧩 Módulo de DataStore

🌐 Módulo de Network

📦 Módulo de Auth (Data + Domain)

### 🚀 Inicializar Koin
Koin se inicializa en la clase que extiende la clase Applicacion

### ⚠️ Errores comunes con Koin

❌ Usar single para UseCases (usa factory)

❌ Meter Android Context en Domain

❌ ViewModel con dependencias de UI

❌ Módulos gigantes sin separar


### 🧠 Regla de oro con Koin

Koin vive en la capa DI, no en el dominio