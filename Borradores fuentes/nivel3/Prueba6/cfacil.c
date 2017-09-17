//#define PROGRAMA "Nivel 3"
//#define DESCRIPCION "Programa del nivel 3"
//#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
//#define VERSION  "v1.0"

#include "c_facil.h"


/*Prueba para dos funciones.
Función 1.
    */
PROCEDIMIENTO funcion1(ladoB) {
escribeNumero(productoNumeros(1,ladoB));
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