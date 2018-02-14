//#define PROGRAMA "Nivel 5"
//#define DESCRIPCION "Programa del nivel 5"
//#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
//#define VERSION  "v1.0"

#include "c_facil.h"


/*Esta función recibe tres lados de un triángulo y muestra por pantalla de qué tipo de triángulo se trata, de un triángulo equilátero, isósceles o escaleno.
*/
PROCEDIMIENTO tipo_triangulo(RACIONAL a,RACIONAL b,RACIONAL c) {
if (siIgualValor(a,siIgualValor(b,c))){
escribeFrase("Es un triángulo equilatero");
}
if (siDistintoValor(siDistintoValor(a,b),siDistintoValor(siDistintoValor(b,c),siDistintoValor(a,c)))){
escribeFrase("Es un triángulo escaleno");
}
if (siDistintoValor(siIgualValor(a,b),c)){
escribeFrase("Es un triángulo isósceles");
}
if (siDistintoValor(siIgualValor(a,c),b)){
escribeFrase("Es un triángulo isósceles");
}
if (siDistintoValor(siIgualValor(b,c),a)){
escribeFrase("Es un triángulo isósceles");
}}

PRG(){
RACIONAL ladoC,ladoB,ladoA; 
asignaValorA(ladoA, pideNumero("Introduce un lado del triángulo"));
asignaValorA(ladoB, pideNumero("Introduce otro lado del triangulo"));
asignaValorA(ladoC, pideNumero("Introduce otro lado del triángulo"));
tipo_triangulo(ladoA,ladoB,ladoC);

}