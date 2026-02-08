# DI Koin Navigation ViewModel List-Details

Esta aplicación es un ejemplo de como combinar la navegación (Navigation 3) 
con pantallas que usan un ViewModel cuando aplicamos un inyector de dependencias (Koin)

En particular hay que prestar atención a la declaración de la dependencia
para el ViewModel de la vista de detalle, dado que el constructor de
DetailViewModel recibe un argumento con el ID del item a mostrar.

Este parámetro lo proporciona la clase de NavKey Detail(val id: Int).
Por tanto, es lo que se denomina en inyección de dependencias un PARAMETRO DINÁMICO.

La forma de declarar la dependencia con un parámetro dinámico cuando se trata de
un ViewModel es la siguiente:

```kotlin
viewModel { (id: Int) -> DetailViewModel(id) }
```

Y para inyectarla como parámetro de la llamada a la función composable
DetailScreenRoot usaremos la función koinViewModel pasando un argumento 
para el parámetro "parameters".

```kotlin
viewModel: DetailViewModel = koinViewModel(parameters = { parametersOf(id) })
```

Al ser "parameters" el último parámetro de la función, cuando le pasamos una lambda
podemos sacarla fuera de los parentesis, con lo que la llamada al koinViewModel
quedaría de la siguiente manera:

```kotlin
viewModel: DetailViewModel = koinViewModel{ parametersOf(id) }
```