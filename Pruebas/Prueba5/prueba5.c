#define PROGRAMA "Programa"
#define DESCRIPCION "Programa automático"
#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
#define VERSION  "v1.0"

#include "c_facil.h"


/*Función que calcula el máximo entre dos números
Entradas: num1 y num2
    
*/
RACIONAL maximo(RACIONAL x,RACIONAL y) {
RACIONAL max; 
if (siMayorValor(x,y)){
asignaValorA(max, x);
}else{
asignaValorA(max, y);
}
return max
}

PRG(){
RACIONAL num1,num2; 
escribeFrase("Programa que muestra el máximo de dos números");
asignaValorA(num1, pideNumero("Introduzca un número"));
asignaValorA(num2, pideNumero("Introduzca otro número"));
escribeFrase("El máximo de los dos números es ");
escribeNumero(maximo(num1,num2));

}