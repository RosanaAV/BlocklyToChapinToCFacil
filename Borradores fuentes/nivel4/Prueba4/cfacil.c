//#define PROGRAMA "Nivel 4"
//#define DESCRIPCION "Programa del nivel 4"
//#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
//#define VERSION  "v1.0"

#include "c_facil.h"


/*Esta función recibe unos números y hace unas operaciones con dichos números y las muestra por pantalla.*/
PROCEDIMIENTO funcion(x,y,z) {
if (siIgualValor(x,sumaNumeros(y,z))){
escribeFrase("El resultado es: ");
escribeNumero(sumaNumeros(y,z));
}
if (siDistintoValor(x,sumaNumeros(y,z))){
escribeFrase("El resultado es:");
escribeNumero(sumaNumeros(x,sumaNumeros(y,z)));
}
}


PRG(){
asignaValorA(num1, pideNumero("Introduce un número"));
asignaValorA(num2, pideNumero("Introduce otro número"));
asignaValorA(num3, pideNumero("Introduce otro número"));
funcion(num1,num2,num3);

}