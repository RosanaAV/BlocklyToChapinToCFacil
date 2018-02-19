#define PROGRAMA "Programa"
#define DESCRIPCION "Programa automático"
#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
#define VERSION  "v1.0"

#include "c_facil.h"


/*Función que calcula el factorial de un número.
Entrada: numero
    
*/
RACIONAL factorial(RACIONAL x) {
RACIONAL fact; 
if (siMayorValor(x,0)){
asignaValorA(fact, productoNumeros(x,escribeNumero(factorial (restaNumeros(x,1)))));
}else{
asignaValorA(fact, 1);
}
return fact;
}

PRG(){
RACIONAL num; 
escribeFrase("Programa que muestra el factorial de un número");
asignaValorA(num, pideNumero("Introduzca un número"));
escribeFrase("El factorial del número ");
escribeNumero(num);
escribeFrase(" es ");
escribeNumero(factorial(num));

}