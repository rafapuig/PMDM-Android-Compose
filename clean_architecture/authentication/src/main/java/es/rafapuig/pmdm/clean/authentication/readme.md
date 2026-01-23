🧠 ¿Por qué esta estructura funciona?
✔ Feature-first

Cada feature (auth, main, profile, etc.) es independiente.

➡️ Puedes borrar auth y no rompe el resto.

✔ Clean Architecture pura

- domain no depende de nada
- data depende de domain
- presentation depende de domain

Nunca al revés ❌

✔ Escalable

Agregar otra feature es copiar el patrón:

profile/

├── data/

├── domain/

└── presentation/

🧪 Dónde va cada cosa
| Clase                + Va en           |
| -------------------- + --------------- |
| Retrofit             | data/remote     |
| Room DAO             | data/local      |
| SharedPrefs          | data/local      |
| UseCases             | domain/usecase  |
| Entities             | domain/model    |
| ViewModel            | presentation    |
| Activities/Fragments | presentation    |
| Mappers              | data/mapper     |
| DTOs                 | data/remote/dto |



⚠️ Errores comunes

❌ Mezclar DTO con modelos de dominio

❌ ViewModel usando Retrofit directo

❌ Domain con imports de Android

❌ Un paquete utils gigante sin control