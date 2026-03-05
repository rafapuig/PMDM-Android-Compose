## 🔹Qué hace rememberUpdatedState

Permite guardar la última versión de un valor o lambda sin invalidar o 
reiniciar efectos que dependen de él.

Muy útil cuando tienes efectos de larga duración (`LaunchedEffect, SideEffect`) 
que capturan valores, pero no quieres reiniciar el efecto cada vez que cambia 
el valor.

Problema típico:

Si pasas una lambda a `LaunchedEffect`, cada vez que cambia, el efecto 
se cancela y reinicia. Con `rememberUpdatedState`, el efecto usa la última 
versión del estado sin reiniciarse.

## 🧠 Cuándo usar rememberUpdatedState

Úsalo cuando:
- Quieres que el LaunchedEffect NO se reinicie
- Pero necesitas que use valores actualizados

Especialmente en:
- callbacks
- timers
- animaciones
- flows dentro del effect

### 🎯 Diferencia mental clave

| Caso                                                | Solución               |
| --------------------------------------------------- | ---------------------- |
| Quiero que el efecto se reinicie cuando cambie algo | `LaunchedEffect(key)`  |
| No quiero reinicio pero sí valor actualizado        | `rememberUpdatedState` |


### 🚀 Regla profesional

Si tu key es Unit, casi siempre necesitas preguntarte:

¿Estoy capturando algo que puede cambiar?

Si la respuesta es sí → probablemente necesitas rememberUpdatedState