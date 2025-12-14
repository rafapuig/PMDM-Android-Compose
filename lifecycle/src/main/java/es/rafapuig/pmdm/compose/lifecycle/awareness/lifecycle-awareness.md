Manejo del ciclo de vida por componentes lifecycle-aware

https://developer.android.com/topic/libraries/architecture/lifecycle

Los componentes conscientes del ciclo de vida son aquellos que
realizan acciones en respuesta a un cambio en el estado del ciclo de
vida de otro componente (actividad, etc)

Mediante estos componentes podemos mover el codigo que reacciona 
al cambio en el ciclo de vida del a actividad fuera de los callbacks
y llevarlo dentro del propio componente.

Por tanto, estos compomentes lifecycle-aware ajustan su comportamiento
automaticamente basandose en el estado actual de una actividad.

## Clase Lifecycle

Esta clase mantiene la información sobre el estado del ciclo de vida
en el que se encuentra actualmente un componente (Activity) y permite
a otros objetos observar este estado.

Usa dos enumeraciones:

### Event
Se corresponden con los eventos callback del ciclo de vida de una actividad.
ON_CREATE, ON_START, ON_RESUME, ON_PAUSE, ON_STOP, ON_DESTROY

Son los arcos o enlaces entre nodos del grafo de estados del ciclo de vida.


### State
El estado actual del componente monitoreado por el objeto Lifecycle

INITIALIZED, DESTROYED, CREATED, STARTED, RESUMED

Son los nodos del grafo de estados del ciclo de vida.


![Estados y eventos](https://developer.android.com/static/images/topic/libraries/architecture/lifecycle-states.svg)


## Lifecycle observer

Un observer del ciclo de vida es un objeto cuya clase implementa
DefaultLifecycleObserver y reemplaza los metodos:
onCreate, onStart, onResume, etc.

Podemos añadir un observador a un Lifecycle llamando al metodo
addObserver() de la clase Lifecycle, pasándole la referencia a 
la instancia del objeto observador.


## LifecycleOwner

Es una interface de un unico metodo que denota que una clase tiene un Lifecycle.
El método es getLifecycle() en Java 
(en Kotlin es una propiedad de solo lectura: lifecycle)

Cualquier clase puede implementar puede implementar esta interface.

Un LifecycleOwner proporciona el ciclo de vida al cual un componente que 
implementa DefaultLifecycleObserver puede observar.






