# Salir de la Composición en Jetpack Compose

Este documento explica qué significa que un composable "salga de la composición" y cómo afecta a los estados y efectos en Jetpack Compose.

---

## 1️⃣ Definición simple

> Un composable **sale de la composición** cuando **ya no forma parte del árbol de UI** que Compose mantiene.

En otras palabras:

- Ya **no está visible** en la pantalla, o  
- Ha sido reemplazado por otro composable en el mismo lugar, o  
- Ha sido eliminado del layout (por ejemplo, dentro de un `if` que ahora es `false`).

Cuando esto ocurre:

- Compose **libera los recursos asociados** a ese composable.
- Se cancelan efectos como `LaunchedEffect` o `DisposableEffect`.
- Los `remember` de ese scope también se destruyen.

---

## 2️⃣ Ejemplo visual

```kotlin
@Composable
fun Example() {
    var show by remember { mutableStateOf(true) }

    Column {
        Button(onClick = { show = !show }) {
            Text("Toggle Composable")
        }

        if (show) {
            Text("Hola! Estoy en la composición")
        }
    }
}
```

- Inicialmente, `show = true` → el `Text` está en la composición.  
- Pulsas el botón → `show = false` → el `Text` **sale de la composición**.  
- Todo lo que esté “recordado” en ese `Text` deja de existir.

---

## 3️⃣ Relación con efectos

### `DisposableEffect`

```kotlin
if (show) {
    DisposableEffect(Unit) {
        println("Effect started!")
        onDispose {
            println("Effect disposed!")  // <-- se ejecuta al salir de la composición
        }
    }
}
```

- Mientras `show = true` → efecto activo.  
- Cuando `show = false` → el composable y su `DisposableEffect` **salen de la composición**, se ejecuta `onDispose`.

### `LaunchedEffect`

- También se cancela automáticamente al salir de la composición.  
- Por eso puedes usarlo para tareas “efímeras” ligadas a un composable.

---

## 4️⃣ Formas de salir de la composición

1. **Condicionales (`if`)**

```kotlin
if (show) MyComposable()
```

2. **Navegación**

- Navegar a otra pantalla con `NavHost` → la screen anterior sale de la composición.

3. **Reemplazo con `key` o recomposición completa**

```kotlin
key(restartKey) { MyScreen() }
```

- Cuando cambia `restartKey`, la vieja `MyScreen` **sale** y se crea una nueva.

4. **Lazy lists (`LazyColumn`)**

- Items que se desplazan fuera de la pantalla pueden salir temporalmente de la composición para optimizar memoria.

---

## 5️⃣ Resumen

- **Salir de la composición = dejar de formar parte del árbol de UI activo.**
- Implica:
  - Cancelación de efectos (`DisposableEffect`, `LaunchedEffect`)  
  - Liberación de `remember`  
  - Posible destrucción de recursos internos
- Es un concepto clave para manejar **recursos externos, listeners, timers, MediaPlayers**, etc.

---

> Nota: Comprender cuándo un composable entra o sale de la composición es fundamental para evitar fugas de memoria y para optimizar la UI en aplicaciones Jetpack Compose.

