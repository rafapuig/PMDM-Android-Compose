# Composable Scope y Composition Roots en Jetpack Compose

Este documento resume qué *composables* crean un **composition scope** (o *RestartGroup*) y cuáles NO lo hacen, y cómo afecta esto a la recomposición.

---

## ✔️ Composables que crean un *RestartGroup* (Composition Scope)

Los siguientes composables crean **un nuevo grupo de recomposición**, por lo que **se recomponen cuando cambian los estados usados en su interior**:

### ### Layouts de alto nivel (crean scope)
- `Column`
- `Row`
- `Box`
- `LazyColumn`
- `LazyRow`
- `LazyVerticalGrid`
- `LazyHorizontalGrid`

### ### Componentes de Material que son contenedores
- `Card`
- `Surface`
- `ModalDrawer`
- `BottomSheetScaffold`
- `TopAppBar` (según implementación)
- `NavigationBar`, `NavigationRail`, etc.

### ### Otros composables estructurales
- `AnimatedContent`
- `Crossfade`
- `AnimatedVisibility`
- `SubcomposeLayout`

---

## ❌ Composables que **NO** crean un nuevo Composition Scope

Estos composables **NO** crean un nuevo RestartGroup.  
Esto significa que **si defines el estado afuera**, no se verán afectados por recomposiciones internas:

### ### Estructuras de Material que operan como *slots*, NO como layouts
- `Scaffold`
- `AlertDialog`
- `DropdownMenu`
- `Dialog`

### ### Controles (no crean scope)
- `Text`
- `TextField`
- `Button`
- `Icon`
- `Image`
- `Checkbox`
- `Switch`
- `Slider`

### Estos **solo se recomponen si su padre se recompone**, no por sí mismos.

---

## 🧠 Ejemplo importante

### Caso que **NO** se recompone:

```kotlin
@Composable
fun Example() {
    var toggle by remember { mutableStateOf(false) }

    Scaffold {   // ← NO crea composition scope
        Button(onClick = { toggle = !toggle }) { Text("Toggle") }
    }

    println("Recomposition!") // ← NO se llama al pulsar el botón
}
```

### Caso que **SÍ** se recompone:

```kotlin
@Composable
fun Example() {
    var toggle by remember { mutableStateOf(false) }

    Column {     // ← SÍ crea composition scope
        Button(onClick = { toggle = !toggle }) { Text("Toggle") }
    }

    println("Recomposition!") // ← se llama correctamente
}
```

---

## 💡 Regla general

> **Un composable crea recomposición si define un nuevo “layout node” o un grupo estructural.  
> Un composable que funciona como “slot” NO crea un nuevo RestartGroup.**

---

## 🧩 ¿Por qué Scaffold no crea recomposición?

Porque `Scaffold` **solo aloja contenido** mediante una lambda de slot, pero no crea un layout propio que viva dentro de la composición.  
El slot recibe recomposición *solo si el padre de Scaffold se recompone*, no por cambios internos.

Los layouts (`Column`, `Row`, `Box`, etc.) sí crean nodos reales, y por eso generan su propio RestartGroup.

---

## ✔️ Conclusión

| Composable | ¿Crea Composition Scope? | ¿Recompone por cambios internos? |
|-----------|---------------------------|----------------------------------|
| `Column`  | ✔️ Sí | ✔️ Sí |
| `Row`     | ✔️ Sí | ✔️ Sí |
| `Box`     | ✔️ Sí | ✔️ Sí |
| `LazyColumn` | ✔️ Sí | ✔️ Sí |
| `Text` | ❌ No | ❌ Solo si el padre se recompone |
| `Button` | ❌ No | ❌ Solo si el padre se recompone |
| `Scaffold` | ❌ No | ❌ Solo si el padre se recompone |

---

Si quieres, puedo generar un PDF, DOCX, o convertirlo en una página HTML para imprimir.
