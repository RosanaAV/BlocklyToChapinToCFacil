#define PROGRAMA "Programa"
#define DESCRIPCION "Programa automático"
#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
#define VERSION  "v1.0"

#include "c_facil.h"


/*Función que calcula el perímetro de un rectángulo
Entradas: base, altura
    
*/
RACIONAL perimetro(RACIONAL x,RACIONAL y) {
RACIONAL per; 
asignaValorA(per, productoNumeros(sumaNumeros(x,y),2));

return per;
}

/*Función que calcula el ñarea de un rectángulo
Entradas: base, altura
    
*/
RACIONAL area(RACIONAL x,RACIONAL y) {
RACIONAL ar; 
asignaValorA(ar, productoNumeros(x,y));

return ar;
}

PRG(){
RACIONAL b,h; 
escribeFrase("Programa que calcula el perímetro y el área de un rectángulo");
asignaValorA(b, pideNumero("Introduzca la base"));
asignaValorA(h, pideNumero("Introduzca la altura"));
escribeFrase("El perímetro del rectángulo es ");
escribeNumero(perimetro(b,h));
escribeFrase(" y su área es ");
escribeNumero(area(b,h));

}