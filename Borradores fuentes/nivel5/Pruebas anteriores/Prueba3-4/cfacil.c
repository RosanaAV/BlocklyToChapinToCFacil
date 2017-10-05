//#define PROGRAMA "Nivel 5"
//#define DESCRIPCION "Programa del nivel 5"
//#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
//#define VERSION  "v1.0"

#include "c_facil.h"


/*Función que muestra por pantalla el texto "¡Hola!"
*/
PROCEDIMIENTO area_rectangulo(RACIONAL ladoA,RACIONAL ladoB) {
escribeNumero(productoNumeros(0,ladoB));
}

PRG(){
RACIONAL x,y; 
asignaValorA(x, pideNumero("Introduce un lado del rectangulo"));
asignaValorA(y, pideNumero("Introduce el otro lado del rectangulo"));
area_rectangulo(x,y);

}