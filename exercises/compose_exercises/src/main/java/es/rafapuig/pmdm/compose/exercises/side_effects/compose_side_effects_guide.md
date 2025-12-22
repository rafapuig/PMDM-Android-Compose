# Jetpack Compose: Efectos y Flow

Este documento resume cómo manejar efectos secundarios, estados derivados, flows y corutinas en Jetpack Compose, incluyendo ejemplos prácticos.

---

## 1. `derivedStateOf`

- Sirve para crear **valores derivados calculados** a partir de estados de Compose.
- Debe ser **puro y síncrono**.
- **No puede contener corrutinas ni efectos secundarios.**

**Ejemplo correcto:**
```kotlin
val isValid = derivedStateOf {
    username.isNotBlank() && password.length > 5
}
```

**No correcto:**
```kotlin
val state = derivedStateOf {
    launch { ... } // ❌ Prohibido
}
```

### Si necesitas corrutinas
- Usa `LaunchedEffect`, `produceState` o ViewModel.

---

## 2. `snapshotFlow`

- Convierte un estado Compose en un Flow.
- Útil para detectar cambios en tiempo real.
- Emite inmediatamente el valor actual, por lo que si quieres ignorar la primera emisión, usa `.drop(1)`.

**Ejemplo: detectar si el usuario escribe:**
```kotlin
var text by remember { mutableStateOf("") }
var isUserTyping by remember { mutableStateOf(false) }

LaunchedEffect(Unit) {
    snapshotFlow { text }
        .drop(1) // ignorar valor inicial
        .collect { newText ->
            isUserTyping = newText.isNotBlank()
        }
}
```

**Detectar inactividad tras 700ms:**
```kotlin
LaunchedEffect(Unit) {
    snapshotFlow { text }
        .drop(1)
        .collect { value ->
            isUserTyping = true
            launch {
                delay(700)
                if (value == text) isUserTyping = false
            }
        }
}
```

**Combinar múltiples flows con el mismo collect:**
```kotlin
merge(flow1, flow2).collect { value ->
    handleEvent(value)
}
```

---

## 3. `rememberUpdatedState`

- Mantiene la última referencia de un valor dentro de lambdas o corutinas.
- Evita capturar valores antiguos.

```kotlin
val currentCallback by rememberUpdatedState(callback)
LaunchedEffect(Unit) {
    while(true) {
        delay(1000)
        currentCallback()
    }
}
```

---

## 4. `DisposableEffect`

- Se ejecuta cuando un composable entra y sale de la composición.
- Útil para limpiar recursos externos:
  - ExoPlayer
  - Listeners del sistema
  - WakeLocks
  - Sensores

**Ejemplo: contador con DisposableEffect**
```kotlin
DisposableEffect(from) {
    val job = scope.launch {
        countdownTimer(from).collect { current = it }
    }
    onDispose { job.cancel() }
}
```

**Nota:** Si usas `rememberCoroutineScope()` dentro del mismo composable, **el job se cancela automáticamente** al salir de la composición, por lo que `DisposableEffect` no es estrictamente necesario.

---

## 5. `LaunchedEffect` y `rememberCoroutineScope`

- `LaunchedEffect` se lanza cuando la key cambia y cancela automáticamente al salir.
- `rememberCoroutineScope()` crea un scope atado al composable; las corutinas lanzadas dentro se cancelan automáticamente al salir.

---

## 6. Ejemplo de Flow: contador regresivo con progreso

```kotlin
data class CountdownState(
    val timeLeft: Int,
    val progress: Float
)

fun countdownTimerWithProgress(from: Int): Flow<CountdownState> =
    (from downTo 0).asFlow()
        .onEach { delay(1000) }
        .map { value -> CountdownState(value, 1f - (value / from.toFloat())) }

@Composable
fun CountdownWithProgress(from: Int) {
    var timeLeft by remember { mutableStateOf(from) }
    var progress by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(from) {
        scope.launch {
            countdownTimerWithProgress(from).collect { state ->
                timeLeft = state.timeLeft
                progress = state.progress
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth().padding(16.dp))
        Text("Tiempo restante: $timeLeft", style = MaterialTheme.typography.headlineMedium)
    }
}
```

- Se puede ocultar el composable y el Flow se cancela automáticamente.
- Se puede extender para **pausar/reanudar** o exponer estado desde un ViewModel.

---

## 7. Resumen de uso de efectos en Compose

| Efecto | Uso principal | Cancelación automática | Cuando usar |
|--------|---------------|----------------------|-------------|
| derivedStateOf | Valores derivados | N/A | Cálculos puros y síncronos
| snapshotFlow | Detectar cambios de estado | N/A si se usa LaunchedEffect | Reacción a cambios de estado
| LaunchedEffect | Ejecutar corutinas ligadas a composición | Sí | Side-effects que usan corutinas
| rememberCoroutineScope | Crear scope atado al composable | Sí | Corutinas que necesitan sobrevivir recomposiciones
| DisposableEffect | Limpiar recursos externos | Sí, manual en onDispose | Listeners, ExoPlayer, sensores, WebView, etc. |

---

Este documento sirve como guía práctica para manejar correctamente estados, flujos y efectos secundarios en Jetpack Compose.

