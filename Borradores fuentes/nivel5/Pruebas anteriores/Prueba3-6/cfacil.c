//#define PROGRAMA "Nivel 5"
//#define DESCRIPCION "Programa del nivel 5"
//#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
//#define VERSION  "v1.0"

#include "c_facil.h"


/*Prueba para dos funciones.
Función 1.
    
*/
PROCEDIMIENTO funcion1(RACIONAL ladoB) {
escribeNumero(productoNumeros(10,ladoB));
}

/*Prueba para dos funciones.
Función 2.
*/
PROCEDIMIENTO funcion2(RACIONAL ladoB,RACIONAL a,RACIONAL b) {
escribeNumero(a);
escribeNumero(b);
}

PRG(){
RACIONAL x,y; 
asignaValorA(x, pideNumero("Introduce un número"));
asignaValorA(y, pideNumero("Introduce otro número"));
funcion1(y);
funcion2(x,y);

}