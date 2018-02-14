//#define PROGRAMA "Nivel 4"
//#define DESCRIPCION "Programa del nivel 4"
//#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
//#define VERSION  "v1.0"

#include "c_facil.h"


/*Prueba para dos funciones.
Función 1.
    */
PROCEDIMIENTO funcion1(ladoB) {
escribeNumero(productoNumeros(10,ladoB));
}


/*Prueba para dos funciones.
Función 2.*/
PROCEDIMIENTO funcion2(ladoB,a,b) {
escribeNumero(a);
escribeNumero(b);
}


PRG(){
asignaValorA(x, pideNumero("Introduce un número"));
asignaValorA(y, pideNumero("Introduce otro número"));
funcion1(y);
funcion2(x,y);

}