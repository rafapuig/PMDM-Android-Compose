Comprobar mediante el Logcat filtando mediante:

package:mine tag:example1

El orden de coordinación en la transición entre la actividad inicial que llama 
y la actividad destino que es llamada

Cuando volvemos a la actividad inicial, la actividad destino 
sale de la pila de actividades, por tanto, se destruye -> vemos un onDestroy

Pero la actividad inicial sigue en la pila aunque naveguemos a la actividad destino.
Por eso, cuando termina la actividad destino se llama a onRestart de la actividad inicial.