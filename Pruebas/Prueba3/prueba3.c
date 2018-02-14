#define PROGRAMA "Programa"
#define DESCRIPCION "Programa automático"
#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
#define VERSION  "v1.0"

#include "c_facil.h"


PRG(){
RACIONAL b,h; 
escribeFrase("Programa que calcula el perímetro de un rectángulo");
asignaValorA(b, pideNumero("Introduzca la base"));
asignaValorA(h, pideNumero("Introduzca la altura"));
escribeFrase("El perímetro del rectángulo es ");
escribeNumero(productoNumeros(sumaNumeros(b,h),2));

}