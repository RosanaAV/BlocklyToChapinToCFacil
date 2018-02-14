//#define PROGRAMA "Nivel 5"
//#define DESCRIPCION "Programa del nivel 5"
//#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
//#define VERSION  "v1.0"

#include "c_facil.h"


/*Esta función devuelve el área de un rectángulo.
*/
RACIONAL area_rectangulo(RACIONAL x,RACIONAL y) {
RACIONAL area; 
asignaValorA(area, productoNumeros(x,y));

return area
}

/*Esta función devuelve el perímetro de un rectángulo.
*/
RACIONAL perimetro_rectangulo(RACIONAL x,RACIONAL y) {
RACIONAL perimetro; 
asignaValorA(perimetro, sumaNumeros(productoNumeros(x,2),productoNumeros(y,2)));

return perimetro
}

PRG(){
RACIONAL num1,num2; 
asignaValorA(num1, pideNumero("Introduce un número"));
asignaValorA(num2, pideNumero("Introduce otro número"));
escribeFrase("El área del rectángulo de lados ");
escribeNumero(num1);
escribeFrase(" y ");
escribeNumero(num2);
escribeFrase(" es ");
escribeNumero(area_rectangulo(num1,num2));
escribeFrase(" y su perímetro es ");
escribeNumero(perimetro_rectangulo(num1,num2));

}