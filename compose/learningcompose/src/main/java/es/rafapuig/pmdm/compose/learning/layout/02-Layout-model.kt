package es.rafapuig.pmdm.compose.learning.layout

/**
 *
 * LAYOUT MODEL
 *
 * El proceso de layout se divide en dos fases:
 * 1. Medición (dimensionado)
 * 2. Colocación (posicionamiento)
 *
 * de los elementos de la UI del árbol resultante de la fase de composición.
 *
 *
 * En el modelo de layout (ubicación), el árbol de elementos de UI
 * se distribuye (ubica / lay out) en una sola pasada.
 *
 * Primero,
 * se pide a cada nodo que calcule su propio tamaño y,
 * que calcule el de sus hijos de forma recursiva,
 * transmitiendo las restricciones de tamaño hacia abajo por el árbol a los hijos.
 * Luego,
 * se dimensionan y se colocan los nodos hoja,
 * y los tamaños y las instrucciones de colocación resultantes
 * se transmiten de nuevo hacia arriba por el árbol.
 *
 * En resumen, los padres son medidos antes que sus hijos,
 * pero se les asigna tamaño y se colocan después de sus hijos.
 *
 * https://developer.android.com/develop/ui/compose/layouts/basics#model
 *
 *
 * Rendimiento
 *
 * En Compose se dimensionan los elementos hijos SOLAMENTE UNA VEZ.
 * La medición en una sola pasada beneficia al rendimiento,
 * ya que permite a Compose gestionar de forma eficiente árboles de UI profundos.
 *
 * Si un elemento de UI midiera a su hijo dos veces
 * y ese hijo midiera a cada uno de sus hijos dos veces, y así sucesivamente,
 * un solo intento de ubicar toda la UI tendría que realizar una gran cantidad de trabajo,
 * lo que dificultaría mantener el rendimiento de la aplicación.
 *
 * Si un layout requiere realizar varias medidas de sus hijos,
 * Compose ofrece un sistema especial: las medidas intrínsecas.
 * https://developer.android.com/develop/ui/compose/layouts/intrinsic-measurements
 *
 * Lo veremos en asuntos avanzados de layout
 */