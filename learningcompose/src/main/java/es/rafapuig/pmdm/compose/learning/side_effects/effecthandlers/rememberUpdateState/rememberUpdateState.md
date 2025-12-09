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