En este modulo creamos la app del contador version del contador
sin usar ningun sistema de inyeccion de dependencias

El CounterViewModel depende de un CounterRepository 
que a su vez depende de un CounterDataStore

## CounterActivity
Muestra como crear el ViewModel y pasarle la referencia al repositorio
del que depende en el contexto de una actividad
Se usa la función viewModels para obtener el viewModel
Esta función necesita una factory para crear el viewModel
Creamos la factoria pasandole el repositorio en el constructor


## CounterComposeActivity
Muestra como crear el ViewModel y pasarle la referncia al repositorio
del que depende en el contexto de una funcion Composable
Se usa la funcion viewModel del API para obtener el viewModel
Esta función no necesita una factory para crear el viewModel
Se le puede proporcionar directamente un objeto CounterViewModel
y que para contruirlo hemos tenido que proporcionarle el repositorio

## MainActivity
En MainActivity llamamos al Composition Root sin porporcionarle
el viewModel, para que use el viewModel que se obtiene del valor por defecto
del parámetro viewModel de la función MVICounterScreenRoot. 
Para ello creamos una factoría en el companion object del CounterViewModel.
Esta factoria se usa con la función viewModel del API para obtener el viewModel
por defecto del parametro viewModel de la función MVICounterScreenRoot.








