//#define PROGRAMA "Nivel 4"
//#define DESCRIPCION "Programa del nivel 4"
//#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
//#define VERSION  "v1.0"

#include "c_facil.h"


/*Función que comprueba el primer valor respecto al segundo valor.*/
PROCEDIMIENTO comprobar_numeros(a,b) {
if (siIgualValor(a,b)){
escribeFrase("Los números son iguales");
}
if (siDistintoValor(a,b)){
escribeFrase("Los números son distintos");
if (siMayorValor(a,b)){
escribeFrase("El primer valor es mayor que el segundo");
}
if ((siMenorValor(a,b)){
escribeFrase("El primer valor es menor que el segundo");
}
}
}


PRG(){
asignaValorA(x, pideNumero("Introduce un número"));
asignaValorA(y, pideNumero("Introduce otro número"));
comprobar_numeros(x,y);

}