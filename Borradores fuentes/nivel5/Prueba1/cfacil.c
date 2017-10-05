//#define PROGRAMA "Nivel 5"
//#define DESCRIPCION "Programa del nivel 5"
//#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
//#define VERSION  "v1.0"

#include "c_facil.h"


/*Esta función recibe dos números y devuelve el mayor entre ambos.
*/
RACIONAL máximo(RACIONAL x,RACIONAL y) {
RACIONAL maximo; 
if (siMayorValor(x,y)){
asignaValorA(maximo, x);
}else{
asignaValorA(maximo, y);
}
return maximo
}

PRG(){
RACIONAL num1,num2; 
asignaValorA(num1, pideNumero("Introduce un número"));
asignaValorA(num2, pideNumero("Introduce otro número"));
escribeFrase("El máximo número entre ");
escribeNumero(num1);
escribeFrase(" y ");
escribeNumero(num2);
escribeFrase(" es ");
escribeNumero(máximo(num1,num2));

}