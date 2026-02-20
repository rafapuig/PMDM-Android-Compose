🔄 Mapper (DTO → Dominio)

❗ Por qué NO usar LoginResponse directamente

❌ Si el backend cambia → rompes la UI
❌ DTOs no son reglas de negocio
❌ Domain queda acoplado a la API

✔ El dominio no sabe que existe LoginResponse


🧱 Regla de oro (guárdala)

DTO entra → Mapper → Domain sale
Domain nunca ve DTOs