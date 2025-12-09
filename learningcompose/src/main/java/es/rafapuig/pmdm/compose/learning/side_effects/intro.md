# Efectos colaterales

Un efecto colateral (side-effect) es un cambio en el estado de la aplicación
que ocurre fuera del ambrio de una funcion composable.

Debido a que el ciclo de vida de un compolsable y sus porpiedades tales como
- recomposiciones de manera impredecible
- en orden diferente
- descartables
las funciones composables deberian de forma ideal estar libres de efectos colaterales

Algunas veces son necesarios:
- iniciar un evento de un solo uso como mostrar una snackbar
- navegar a otra pantalla dada una cierta condición en el estado


Un **effect** es una función composable que 
- no emite UI
- causa efectos colaterales (secundarios) a ejecutar cuando se completa la composición.

## LaunchedEffect
- ejecutar funciones suspendidas en el ámbito de una función composable

## rememberCoroutineScope
- obtener un scope composition-aware para lanzar una corutina fuera de una función composable


## rememberUpdatedState
- se usa para hacer referencia a un valor dentro de un effect que no se debe reiniciar cuando este valor cambia


