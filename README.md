TP5 -Programación 3- 
Alumnos:
Carla Racciatti, Nicolás Clemente

Red Social Básica
Descripción: 
Este proyecto es una implementación de una red social básica utilizando Java y Swing para la interfaz gráfica de usuario. 
Los usuarios se representan como nodos y las relaciones de amistad como aristas en un grafo. 
La aplicación permite agregar y eliminar usuarios, establecer y eliminar amistades, y buscar relaciones entre los usuarios.

Objetivos de Aprendizaje:
Representación de grafos: Utilizar una estructura de datos de grafo para modelar la red social.
Búsqueda en anchura (BFS): Encontrar la relación directa o el camino más corto entre dos usuarios.
Búsqueda en profundidad (DFS): Buscar amigos en común entre dos usuarios si no tienen una relación directa.

Funcionalidades:
Agregar Usuario: Permite agregar un nuevo usuario a la red social.
Listar Usuarios: Muestra la lista de todos los usuarios en la red social con su respectivo checkbox. Para realizar las funcionalidades del programa, se deben tildar los checkboxes de los usuarios que desee.
Agregar Amistad: Permite crear una amistad entre dos usuarios seleccionados.
Eliminar Amistad: Permite eliminar una amistad entre dos usuarios seleccionados.
Buscar Relación: Permite verificar si dos usuarios son amigos directos o buscar amigos en común.
Eliminar Usuario: Permite eliminar un usuario de la red social.

Estructura del Proyecto
GUI: Contiene el package GUIRedSocial, que a su vez contiene: 
    GUIRedSocial.java y GUIRedSocial.form que usamos para diseñar la interfaz gráfica. 
Logica: Contiene la clase RedSocial.java que maneja la lógica y métodos.
Main: inicia la aplicación.

Uso:
Navegar a la clase Main.
Ejecutar la clase Main.
Interfaz Gráfica
Agregar Usuario:
Ingrese el nombre del usuario en el campo de texto y presione el botón "Agregar Usuario".
Listar Usuarios:
La lista de usuarios se muestra en un JList en la parte central de la ventana.
Agregar Amistad:
Seleccione dos usuarios de la lista (tildando el checkbox) y presione el botón "Agregar Amistad".
Eliminar Amistad:
Seleccione dos usuarios de la lista (tildando el checkbox)  y presione el botón "Eliminar Amistad".
Buscar Relación:
Seleccione dos usuarios de la lista (tildando el checkbox)  y presione el botón "Buscar Relación".
-Si son amigos directos, se mostrará un mensaje indicando que son amigos.
-Si no son amigos directos, se buscarán amigos en común y se mostrará un mensaje con los resultados.
Eliminar Usuario:
Seleccione un usuario de la lista y presione el botón "Eliminar Usuario".

Detalles de grafos (Busquedas BFS y DFS)

La red social se representa mediante un HashMap<String, Set<String>> donde cada clave es un usuario y el valor es un conjunto de amigos.

Búsqueda en Anchura (BFS)
El método busquedaAnchura se utiliza para encontrar el camino más corto entre dos usuarios y determinar si son amigos directos.

Búsqueda en Profundidad (DFS)
El método obtenerAmigosEnComun utiliza una búsqueda en profundidad para encontrar amigos en común entre dos usuarios.
