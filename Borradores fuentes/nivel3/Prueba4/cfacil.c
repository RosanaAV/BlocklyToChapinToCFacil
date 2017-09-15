//#define PROGRAMA "Nivel 3"
//#define DESCRIPCION "Programa del nivel 3"
//#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
//#define VERSION  "v1.0"

#include "c_facil.h"


/*Función que muestra por pantalla el texto "¡Hola!"*/
PROCEDIMIENTO area_rectangulo(ladoA,ladoB) {
escribeNumero(productoNumeros(ladoA,ladoB));
}

PRG(){
asignaValorA(x, pideNumero("Introduce un lado del rectangulo"));
asignaValorA(y, pideNumero("Introduce el otro lado del rectangulo"));
area_rectangulo(x,y);

}