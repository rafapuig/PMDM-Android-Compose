# Composables que crean un Recomposition Scope en Jetpack Compose

## 🧠 Qué es un *recomposition scope*
Un *recomposition scope* es una unidad independiente dentro del árbol de composición que puede recomponerse sin afectar al resto.  
Compose crea scopes para optimizar el trabajo y evitar recomponer más de lo necesario.

Regla de oro:

> **Si un composable recibe un parámetro `content: @Composable (...) -> Unit`, es muy probable que cree un nuevo recomposition scope.**

---

# ✅ 1. Composables que SIEMPRE crean un nuevo Recomposition Scope

Estos composables aíslan su contenido en un scope diferente:

- **Scaffold**
- **Dialog**, **AlertDialog**
- **Popup**
- **DropdownMenu**
- **ModalBottomSheet**
- **BottomSheetScaffold**
- **ModalNavigationDrawer**
- **NavigationDrawer**, **NavigationRail**
- **NavHost** (Jetpack Navigation Compose)
- **AnimatedContent**
- **AnimatedVisibility**
- **Crossfade**
- **Pager** (Accompanist / Compose Foundation)
- **Effects que crean scopes internos**:
    - `LaunchedEffect`
    - `SideEffect`
    - `DisposableEffect`
    - `produceState`

Estos composables usan internamente `content: @Composable` lambdas que se consideran scopes independientes.

---

# 🟦 Ejemplo típico

```kotlin
Scaffold(
    topBar = { ... },
    bottomBar = { ... }
) { innerPadding ->     // <--- ESTE ES UN NUEVO SCOPE
    // contenido aislado
}
