//#define PROGRAMA "Nivel 4"
//#define DESCRIPCION "Programa del nivel 4"
//#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
//#define VERSION  "v1.0"

#include "c_facil.h"


PRG(){
asignaValorA(x, pideNumero("Introduce un número"));
asignaValorA(y, pideNumero("Introduce otro número"));
if (siIgualValor(x,y)){
escribeFrase("Los números son iguales");
}
if (siDistintoValor(x,y)){
escribeFrase("Los números son distintos");
}

}