# DTOs (Data Trasfer Object)

Los DTOs son los objetos que se usan para enviar o recibir datos
en la comunicacion con el API Rest remota.

Se utilizan data class de Kotlin

Sufijos:
- Request: Para un objeto que se envia en la solicitud
- Response: Para un objeto resultado o respuesta de la solicitud
- Dto: Data Transfer Object (objetos anidados en una respuesta o solicitud)

Si utilizamos serializacion Json mediante la biblioteca Kotlinx Serialización
debemos anotar los DTOs con la @Serializable

🧠 Regla de oro moderna

Una sola librería de serialization para TODO
(API + Navigation 3 + DataStore si aplica)

Las propiedades de las clases de DTOs se anotan con @SerialName
indicando el nombre de la clave del atributo en el JSON.
(Si coinicide con el nombre de la propiedad de la clase no es necesario)

👉 Regla clave:
El DTO refleja EXACTAMENTE lo que devuelve el backend.