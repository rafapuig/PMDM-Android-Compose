# Guardar el estado dinamico de la UI

## Enlaces a la documentación oficial


https://developer.android.com/topic/libraries/architecture/saving-states

### Saved State module for ViewModel
https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-savedstate

## Ejemplo UIStateActivity

Ejemplo de como se puede guardar estado de la actividad
mediante las funciones callback del ciclo de vida de la actividad


```kotlin
class UIStateActivity : ComponentActivity() {
    /** ... */    
}
```

- onSaveInstanceState

- onRestoreInstanceState