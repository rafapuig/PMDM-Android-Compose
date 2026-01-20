# Actividades de la app

En esta aplicacion se muestra como usar Room
para persistir en una base de datos local SQLite
la información de tareas ToDo (por hacer)
sin utilizar un repositorio para abstraer la fuente de datos.

Vemos 
1. Como usar la fuente de datos directamente en
un composable
2. Como usar la fuente de datos drectamente en un ViewModel

## 1. Uso directo de la fuente de datos (DAO) en la UI

### ComposeMainActivity
Esta actividad establece su contenido de la UI llamando
a la función composable TodosListScreenCompose() que, en este caso, 
se encarga de obtener directamente los datos desde la fuente de datos (el DAO).
La función TodosListScreenCompose(), crea la instacia de TodoDatabase,
de la que obtiene el DAO con el que manipular la informacíon de la base datos.

### CustomDatabaseFactoryComposeActivity
Esta actividad, al igual que ComposeMainActivity, establece su UI llamando
a la función TodosListScreenCompose(). 
En este caso, en la llamada a la función composable se proporciona explictamente 
una factoría.
Este actividad sirve de ejemplo de como crear una factoría personalizada 
que pasar a la función TodosListScreenCompose.
En concreto la factoria personalizada creará una base de datos en memoria y
cargará algunos Todos de ejemplo.

## 2. Uso de la fuente de datos en un ViewModel

### TodoListViewModel

TodoListViewModel contiene métodos para actualizar los datos de la base de datos
de Todos: añadir, actualizar y borrar Todos, además de una propiedad StateFlow
que emite la lista de Todos y permite recolectarlo con el metodo collectAsStateWithLifecycle().

Con esto, se llama a la función composable TodoListScreen, la pantalla de la UI.
Esta función TodoListScreen recibe la información de estado (la lista de Todos) junto
con las funciones callback a las que llamar cuando se produzca una acción por parte
del usuario cuando interacciona con los componentes definidos en la pantalla.

El TodoListViewModel tiene una dependencia respecto al DAO. En el constructor del
TodoListViewModel se debe proporcional el DAO del que depende el objeto TodoListViewModel.
Sabemos que siempre que una clase ViewModel declare parámetros en el constructor será
necesario definir una clase factoría encargada de crear instancias de ese tipo de ViewModel.
La factoría tiene que extender de ViewModelProvider.Factory
y debe usar una referencia a un DAO para crear el TodoListLiewModel.
La referencia la conocerá bien porque se la proporcionamos a la propia factoria
o bien porque la factoria contiene internamente el código para obtenerla.
En el caso del TodoListViewModel, la factoría la obtiene a partir de obtener la
referencia al objeto TodoListAplication y mediante este objeto acceder a la propiedad
todosDatabase, y desde todosDatabase acceder al DAO.
```kotlin
val Factory: ViewModelProvider.Factory =
    viewModelFactory {
        initializer {
            val app = this[APPLICATION_KEY] as TodoListApplication
            val dao = app.todosDatabase.todoDao()
            TodoListViewModel(dao)
        }
    }
```

### TodoListActivity
Esta actividad sirve de ejemplo de como utilizar un viewmodel que se encarga
de interactuar directamente con la fuente de datos (el DAO).
Se usa la función viewModels del ViewModel Scoping API para obtener una instancia
de TodoListViewModel asociada con la actividad (es decir, un ViewModel que 
durará mientras la actividad permanezca en la pila de back stack).

La referencia a este viewmodel dentro de la función setContent para recolectar
el flow y obtener la lista de Todos.
Con ello, se pasa la lista y las referencias a los métodos del viewmodel
que se encargar de actualizar los datos al TodoListScreen.

### MainActivity
Esta actividad tambien sirve de ejemplo de como utilizar un viewmodel que se encarga
de interactuar directamente con la fuente de datos (el DAO).
En este caso en la actividad no se obtiene una referencia al viewmodel mediante la
función viewModels del ViewModel Scoping API.

Tampoco se llama a TodoListScreen, en su lugar se llama a la función composable 
TodosListScreenRoot() a la cual se le pasa un viewmodel.

### TodosListScreenRoot
La función TodosListScreenRoot() recibe un viewmodel como argumento.
Y se encarga de procesarlo para propcionar los argumentos correspondientes 
al llamar a la función composable TodosListScreen().

Si no recibe el viewmodel desde el llamador usa uno obtenido mediante la 
expresión definida para valor del parámetro por defecto.
La expresión hace uso de la función viewModel() del ViewModel Scoping API.
Esta función, solamente puede usarse dentro de una función composable.








