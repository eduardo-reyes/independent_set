# Análisis de algoritmos

Implementación:
Sea G = (V, E) una di-gráfica. Existe un conjunto independiente S(G) en
G tal que cada vértice en G puede ser alcanzado a partir de S(G) por una
trayectoria de longitud a lo más 2.

## Autor: Eduardo Alfonso Reyes López

## Prerrequisitos

[Instalar Java] (https://www.java.com/es/download/help/download_options_es.html)

## Instalación

Descargar el comprimido ".zip" y descomprimir la carpeta.

## Pre-ejecución

El programa a implementar recibe como entrada en los argumentos
de la línea de comandos un archivo de texto tal que:

    1. En la primera línea tendrá todos los vértices que forman a G,
    separados por una coma (’,’).
    2. En las siguientes líneas irán pares de vértices, separados por
    una coma (’,’), que indicará en las aristas de G.

Colocar el archivo de texto en la carpeta src/ tal como en los ejemplos.
Se puede alterar la ruta de búsqueda en la variable path de la clase Prac1.
Ejemplo: Colocar explícitamente en línea de comandos "Grafica.txt" cuado
salga el texto "Introduce el nombre del archivo: ".
Para ejecutar la demo sólo dar Enter.

## Ejecución

En la carpeta principal independent_set/

Compilar -> javac -d . src/Prac1.java

Ejecutar -> java classes.src.Prac1