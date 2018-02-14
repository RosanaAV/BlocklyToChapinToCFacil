#define PROGRAMA "Programa"
#define DESCRIPCION "Programa automático"
#define AUTOR "Programa generado automaticamente para TFG_BlocklyToChapinToCFacil"
#define VERSION  "v1.0"

#include "c_facil.h"


PRG(){
RACIONAL num; 
asignaValorA(num, pideNumero("Elige un número: 1 o 2"));
escribeFrase("El número elegido es ");
escribeNumero(num);

}