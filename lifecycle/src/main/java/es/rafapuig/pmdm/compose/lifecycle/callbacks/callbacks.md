# El ciclo de vida de una actividad

![Ciclo de vida de la Activity](https://developer.android.com/guide/components/images/activity_lifecycle.png)

https://developer.android.com/guide/components/activities/activity-lifecycle

Los callbacks se debe usar para acciones que sean propias de la actividad.

El codigo que implementa las acciones de un componente dependiente 
del ciclo del vida de la actividad debe ir en el propio componente. 
Y para eso haremos el componente lifecyicle-aware del ciclo de la actividad.

## onCreate
Cuando se crea la actividad se llama al metodo onCreate()
Solamente se llama una vez durante la vida del actividad

Un componente lifecycle-aware del ciclo de vida de esta actividad
recibe el evento ON_CREATE

Normalmente lo usamos para declarar la UI de la actividad mediante
el metodo setContentView() o setContent() en el caso de una UI con 
Jetpack Compose

## onStart
La actividad es visible para el usuario y se prepara para interactuar con 
el usuario

Un componente lifecycle-aware del ciclo de vida de esta actividad
recibe el evento ON_START

## onResume
La actividad se ha vuelto visible y esta lista para interactuar con el usuario
Esta en primer plano (foreground) hasta que pierde el foco:
- el usuario navega a otra actividad

Un componente lifecycle-aware del ciclo de vida de esta actividad
recibe el evento ON_RESUME.
Aqui es donde el componente debe habilitar la funcionalidad que desea
llevar a cabo mientras la actividad esta en primer plano:
- la preview de la camara

## onPause
La actividad esta en segundo plano (background) pero visible para el usuario
Esta lista para perder el foco:
- el usuario navega a otra actividad

Un componente lifecycle-aware del ciclo de vida de esta actividad
recibe el evento ON_PAUSE.
Aqui el compomente detiene la funcionalidad.

## onStop
La actividad no esta visible para el usuario y esta lista para ser destruida

Un componente lifecycle-aware del ciclo de vida de esta actividad
recibe el evento ON_STOP.

Es el momento para realizar operaciones de cierre que seran intensivas en CPU.

## onDestroy
La actividad esta a punto de ser destruida

Un componente lifecycle-aware del ciclo de vida de esta actividad
recibe el evento ON_DESTROY

Se invoca por 2 motivos:
1. La actividad se esta finalizando por accion del usuario o
 por llamada al metodo finish()
2. La actividad esta a punto de ser destruida por el sistema
debido a un cambio de configuración (rotacion, cambio a dark-mode, etc)

Si la actividad es destruida debido a un cambio de configuración en sistema
imediatamente crea una nueva instancia y llama al onCreate()



