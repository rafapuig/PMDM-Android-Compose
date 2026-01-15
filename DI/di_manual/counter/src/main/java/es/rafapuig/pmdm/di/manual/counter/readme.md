En este modulo creamos una versión dela app del contador
que no usa ningun sistema de inyección de dependencias

El CounterViewModel depende de un CounterRepository 
que a su vez depende de un CounterDataStore

## CounterActivity
Muestra como crear el ViewModel y pasarle la referencia al repositorio
del cual depende dentro del bloque de codigo de una clase Activity
Se usa la función viewModels (ViewModel Scoping API) para obtener el viewModel
confinado al ViewModelStoreOwner (en este caso la Activity) más proximo
desde donde se llama a esta función.
Esta función viewModels() necesita de una factoría para usarla si necesita instanciar el viewModel.
Creamos la factoría proporcionando el repositorio en el constructor


## CounterComposeActivity
Muestra como crear el ViewModel y pasarle la referencia al repositorio
del que depende en el contexto de una función composable.
Se usa la función viewModel() del ViewModel Scoping API para obtener 
el viewModel.
Esta función no necesita obligatoriamente una factoría para crear el viewModel.
Se le puede proporcionar directamente un objeto CounterViewModel 
al cual, para construirlo, habremos tenido que proporcionarle a su vez el repositorio.

## MainActivity
En MainActivity llamamos al Composition Root sin proporcionarle
el viewModel, para que use el viewModel que se obtiene del valor por defecto
del parámetro viewModel de la función MVICounterScreenRoot. 
El valor por defecto del parámetro "viewModel" de la función MVICounterScreenRoot
se obtiene llamando a la función viewModel() del ViewModel Scoping API.
Para que la funcion viewModel() pueda proporcionar un viewModel necesita que pasemos
la referencia a una factoría de objetos CounterViewModel.
Para ello creamos una factoría en el companion object del CounterViewModel.
Esta factoría se usa con la función viewModel del ViewModel Scoping API
para obtener el viewModel por defecto del parametro viewModel de la función MVICounterScreenRoot.








