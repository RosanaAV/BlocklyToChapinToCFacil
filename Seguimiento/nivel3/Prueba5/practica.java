/*
Nombre: TFG_BlocklyToChapinToCFacil
Descripción: Programa realizado para generar programas automáticamente para TFG_BlocklyToChapinToCFacil
Entrada: Archivos *.xml generados por Blockly
Salida: Archivos *.c 
Autor: Rosana Arnáiz Vicario
Organización: Universidad de Burgos
Fecha: 16/09/2017
Versión: v1.0.
*/

options {
	//DEBUG_TOKEN_MANAGER=true;			//Es el debug del programa
    BUILD_TOKEN_MANAGER=true;
    COMMON_TOKEN_ACTION=false;
}

/*Definición de la clase que implementará el parser.
Se abre el fichero a analizar y se muestra el resultado de los análisis.
También se puede introducir el código por pantalla.
*/
PARSER_BEGIN(practica)

import java.util.*;
import java.lang.*;
import java.io.*;

public class practica {
   	public static void main(String args[]) throws java.io.FileNotFoundException {
                    
   		practicaTokenManager tkmngr;
			
        
		if ( args.length < 1 ) {
			
   	       	tkmngr = new practicaTokenManager(new SimpleCharStream(System.in));    //entrada por teclado
       	} else {	
            tkmngr = new practicaTokenManager(new SimpleCharStream( new java.io.FileInputStream(args[0]),0,0 ));//si se abre un archivo
   		};
 
		Token tk;

   		try {
       		while ( (tk = tkmngr.getNextToken()).kind != EOF )  ; 			//lector de lexicos
   		} catch (TokenMgrError x) {
       		System.out.println("Error, Algo salio mal.\n"); 				//si se produce eun fallo 
       		throw x;
   		};
   	}
}
PARSER_END(practica)


//Declaración de variables para el programa
TOKEN_MGR_DECLS:
{
	
		//Esta variable es un String que almacena el texto que aparece en el print
	static String texto = new String();
		//Esta variable es un String que almacena el nombre de la variable
	static String variable = new String();
		//Esta variable es un String que alacena la frase del prompt
	static String variable3 = new String();
		//Esta variable es un String que almacena un número
	static String variable4 = new String();
		//Esta variable es un String que almacena el nombre de la función a definir
	static String nombreFuncion = new String();
		//Esta variable es un String que almacena la descripción de la función a definir
	static String descripcionFuncion = new String();
		//Esta variable es un String que almacena el nombre de la función a llamar
	static String nombreFuncion2 = new String();
		//Esta variable es un String que almacena el nombre del return de una función
	static String varReturn = new String();
		//Esta variable es un String que almacena el código cFacil para después mostrarlo
	static String escribir = new String();
		//Esta variable es una lista de String que almacena los argumentos de la función a definir
	static List<String> argumentosFuncion = new ArrayList<String>();
		//Esta variable es una lista de String que almacena los argumentos de la función a llamar
	static List<String> argumentosLLFuncion = new ArrayList<String>();
		//Esta variable es una lista que almacena las variables que hay en una función o en el programa
	static List<String> listaVariables = new ArrayList<String>();
		//Esta variable es un conjunto que almacena las variables que hay para que no haya duplicados
	static Set<String> conjuntoVariables = new HashSet<String>();
		//Esta variable es un entero que almacena el número de argumentos que tiene la función a llamar
	static int argFuncion=100;
		//Esta variable es un entero que almacena el número de argumentos de la función a llamar que que tienen valor
	static int arg=0;
		//Esta variable es un entero que va contando el número de paréntesis que hay
	static int parentesis=0;
		//Esta variable es un entero que va contando el número de llaves que hay
	static int llaves=0;
		//Esta variable es un booleano que devuelve si una función tiene return o no
	static int ret=0;
		//Esta variable es un booleano que devuelve si la función tiene argumentos o no
	static int argument=0;
		//Esta variable es un booleano que devuelve si se ha llamado a una función o no
	static int llfuncion=0;
		//Esta variable es un booleano que devuelve si la función a llamar tiene o no argumentos
	static int mutationLLF=0;
		//Esta variable es un booleano que devuelve si los argumentos de la función son variables o texto
	static int esTexto=0;
		//Esta función es un booleano que devuelve si el programa tiene alguna función o no
	static int esFuncion=0;
		//Esta función es un booleano que devuelve si estás en un set o no para guardar el nombre de la variable
	static int set=0;
		
}


//Declaración de tokens
TOKEN:
{
	< Name: ( <NameStarChar> )( <NameChar> )* >
	| < #NameChar: ( <NameStarChar> )|"-"|"."|["0"-"9"]|"\u00B7" >
	| < #NameStarChar: ["A"-"Z"]|"_"|["a"-"z"]|["\u00C0"-"\u00D6"]|["\u00D8"-"\u00F6"] >
	| < #S: (" " | "\t" | "\n" | "\r")+ >
	| < Entero: ("-")?(["0"-"9"])+ >
	| < Flotante: ("-")?((["0"-"9"])+ | (["0"-"9"])+ "."(["0"-"9"]))+((["e","E"])(["-","+"])?(["0"-"9"])+)? >
}

//Declaración de los estados iniciales
TOKEN :	
{
		//Para poder empezar a analizar el programa se tiene que encontrar ETIXML
		//Una vez encontrado, se imprime la cabecera y los include del programa cFacil
		//Después se va al estado XML
	< ETIXML: "<xml" > {
		System.out.println("//#define PROGRAMA \"Nombre\"\t//TODO");
		System.out.println("//#define DESCRIPCION \"Programa en c_facil\"\t//TODO");
		System.out.println("//#define AUTOR \"Programa generado automaticamente para TFG_BlocklyToChapinToCFacil\"\t//TODO");
		System.out.println("//#define VERSION  \"v1.0\"\t//TODO");
		System.out.println("\n#include \"c_facil.h\"");
	} : XML
}

//Declaración del estado XML
//A este estado se llega desde el estado inicial y desde el estado BLOQUE
<XML>TOKEN:
{
		//Se encuentra la etiqueta ETIFUNCION si hay una función para definir sin retorno
		//Después va al estado ETIFUNCION2
   	<ETIFUNCION: (<S>)?"<block type=\"procedures_defnoreturn" > : ETIFUNCION2
		//Se encuentra la etiqueta ETIFUNCIONRETURN si hay una función para definir con retorno
		//Después va al estado ETIFUNCIONRETURN2
	|<ETIFUNCIONRETURN: (<S>)?"<block type=\"procedures_defreturn" > : ETIFUNCIONRETURN2
		//Se encuentra la etiqueta ETIBLOQUE si hay un bloque en el programa
		//Después va al estado ETIBLOQUE2
	|< ETIBLOQUE: (<S>)?"<bloc" > : ETIBLOQUE2 
		//Se encuentra la etiqueta ETINEXT2 si en la función hay otro bloque
		//Se actualiza la variable escribir con código cFacil
		//Después se va al estado BLOQUE
	|< ETINEXT2: (<S>)?"<next" > {escribir=escribir+("\n");} : BLOQUE
		//Se encuentra la etiqueta ETIFINNEXT2 si en la función ya no hay más bloques
		//Después va al estado BLOQUE
	|< ETIFINNEXT2: (<S>)?"</next" > : BLOQUE
		//Se encuentra la etiqueta ETIFINSTATEMENT2 cuando se acaba un if, else o función
		//Después va al estado XML
	|< ETIFINSTATEMENT2: (<S>)?"/statement" > : FINSTATEMENT
		//Se encuentra la etiqueta FINSTAT3 cuando se encuentra el return de una función
		//Se actualiza la variable escribir con código cFacil
		//Después va al estado VARRETURN
	|< FINSTAT3:  (<S>)?"name=\"RETURN" > {escribir=escribir+("\nreturn ");} : VARRETURN
		//Se encuentra la etiqueta ETIFINXML2 cuando se acaba el programa
		//Después va al estado ETIFXML
	|< ETIFINXML2: (<S>)?"/xml" > : ETIFXML
	
}

//Declaración de estado ETIFXML
//A este estado se llega desde el estado XML y BLOQUE
<ETIFXML>TOKEN:
{
		//Se encuentra la etiqueta ETIFXML2 cuando se acaba el programa
		//Se imprime código cFacil
		//Después va al estado DEFAULT
	< ETIFXML2: ">" > {
		if(!conjuntoVariables.isEmpty()){
			System.out.printf("RACIONAL ");
			Iterator it4 = conjuntoVariables.iterator();
			while (it4.hasNext()){
				listaVariables.add(it4.next().toString());
			}
			if(listaVariables.size()>1){
				for ( int i=0; i < listaVariables.size() - 1; i++){
					System.out.printf(listaVariables.get(i)+",");
				}
			}
			System.out.printf(listaVariables.get(listaVariables.size()-1)+"; \n");
		}
		System.out.printf(escribir+"\n}");	
		} : DEFAULT
}

//Declaración del estado BLOQUE
//A este estado se llega si hay algún estado que definir, desde los estado XML, FINBLOQUE, SIGUIENTE, FINNOMVAR, SIG, FRASEPROMPT3, STATEMENT, ARGUMENTOSFUN,
//FINLLF, OP, OPLOGIC, y desde es propio estado de BLOQUE
<BLOQUE>TOKEN:
{
		//Se encuentra la etiqueta ETIFINXML cuando se termina el código a analizar
		//Se imprime la llave de final del programa cFacil
		//Después va al estado DEFAULT
	< ETIFINXML: "/xml" > : ETIFXML
		//Se encuentra la etiqueta ETIFINBLOQUE cuando se termina el bloque analizado
		//Después va al estado FINBLOQUE
	|< ETIFINBLOQUE: (<S>)?"</block" > : FINBLOQUE
		//Se encuentra la etiqueta ETIPRINT cuando hay un bloque que imprime algo
		//Después va al estado PRINT
	|< ETIPRINT: (<S>)?"type=\"text_print\""(<S>)? > : PRINT
		//Se encuentra la etiqueta ETIVARSET cuando hay un bloque que asigna un valor a una variable
		//Se imprime código cFacil y se suma uno al contador parentesis
		//Después va al estado NOM
	|< ETIVARSET: (<S>)?"type=\"variables_set\""(<S>)? > {escribir=escribir+("asignaValorA(");parentesis++;set=1;} : NOM
		//Se encuentra la etiqueta ETIPROMPT cuando hay un bloque que pide un dato por pantalla
		//Después va al estado TEXTPROMPT
	|< ETIPROMPT:(<S>)?"type=\"text_prompt_ext\""(<S>)? > : TEXTPROMPT
		//Se encuentra la etiqueta ETINEXT cuando hay otro bloque en el programa
		//Se imprime código cFacil y se resta uno al contador parentesis
		//Después va al estado FINBLOQUE
	|< ETINEXT: (<S>)?"<next" > {escribir=escribir+(");\n");parentesis--;} : FINBLOQUE
		//Se encuentra la etiqueta ETIVARGET cuando hay un bloque que muestra el nombre de una variable
		//Después va al estado NOM
	|< ETIVARGET : (<S>)?"type=\"variables_get\""(<S>)? > : NOM
		//Se encuentra la etiqueta ETIFINSTATEMENT cuando termina la definición de una función
		//Se imprime código cFacil
		//Después va al estado XML
	|< ETIFINSTATEMENT: (<S>)?"/statement>" > : FINSTATEMENT
		//Se encuentra la etiqueta ETILLAMAFUNCION cuando hay un bloque que llama a una función
		//Se inicializan las variables argFuncion, arg, llfuncion, argumentosLLFuncion
		//Después va al estado LLAMAFUNCION
	|< ETILLAMAFUNCION: (<S>)?"type=\"procedures_callnoreturn" > {argFuncion=0;arg=0;llfuncion=1;argumentosLLFuncion=new ArrayList<String>();} : LLAMAFUNCION		
		//Se encuentra la etiqueta ETIARGUMENTOS cuando hay argumentos en la función llamada
		//Se inicializa la variable mutationLLF y se suma uno al contador arg
		//Después va al estado ARGU
	|< ETIARGUMENTOS: (<S>)?"value name=\"ARG" > {arg++;mutationLLF=0;} : ARGU
		//Se encuentra la etiqueta ETIMATHARITHMETIC cuando hay un bloque de operaciones aritméticas
		//Después va al estado OP
	|< ETIMATHARITHMETIC: (<S>)?"type=\"math_arithmetic\""(<S>)? > : OP
		//Se encuentra la etiqueta ETIMATHNUMBER cuando hay un bloque que muestra un número
		//Después va al estado NUM
	|< ETIMATHNUMBER: (<S>)?"type=\"math_number\""(<S>)? > : NUM
		//Se encuentra la etiqueta ETIVALUENAMEB en el segundo valor de una operación artimética y en el segundo valor de una operación relacional
		//Se imprime código cFacil
		//Después va al estado BLOQUE
	|< ETIVALUENAMEB: (<S>)?"value name=\"B\">" > {escribir=escribir+(",");} : BLOQUE
		//Se encuentra la etiqueta ETIIF cuando hay un bloque de operación relacional if
		//Se imprime código cFacil y se suma uno al contador parentesis
		//Después va al estado BLOQUE
	|< ETIIF: (<S>)?"type=\"controls_if"(<S>)? > {escribir=escribir+("if (");parentesis++;}: BLOQUE
		//Se encuentra la etiqueta ETILOGIC cuando hay un bloque de operación relacional
		//Después va al estado OPLOGIC
	|< ETILOGIC: (<S>)?"type=\"logic_compare"(<S>)? > : OPLOGIC
		//Se encuentra la etiqueta ETIDO0 cuando se termina el el bloque de la operación relacional
		//Se imprime código cFacil y se resta uno al contador parentesis
		//Después va al estado BLOQUE
	|< ETIDO0: (<S>)?"name=\"DO0"(<S>)? > {escribir=escribir+("){\n");parentesis--;} : BLOQUE
		//Se encuentra la etiqueta ETIADD3 cuando se encuentra un valor en el text join
		//Se imprime código cFacil
		//Después se va al estado SIGUIENTE
	|<ETIADD3: (<S>)?"value name=\"ADD" > {escribir=escribir+(");\n");parentesis--;} : SIGUIENTE
		//Se encuentra la etiqueta ETILLFUNCIONRETURN cuando hay un bloque que llama a una función
		//Se inicializan las variables argFuncion, arg, llfuncion, argumentosLLFuncion
		//Después va al estado LLAMAFUNCION
	|<ETILLFUNCIONRETURN: (<S>)?"block type=\"procedures_callreturn" > {argFuncion=0;arg=0;llfuncion=1;argumentosLLFuncion=new ArrayList<String>();} : LLAMAFUNCION		

}

//Declaración del estado FINBLOQUE
//A este estado se llega cuando se termina un bloque, desde el estado BLOQUE
<FINBLOQUE>TOKEN:
{
		//Se encuentra la etiqueta FINBLO cuando se termina el bloque
		//Se comprueban las variables comprueban las variables mutationLLF y parentesis, y dependiendo de lo que tengan almacenado se imprime un código 
		// cFacil u otro, y se actualiza la variable parentesis
		//Después va al estado BLOQUE
	<FINBLO: (<S>)?">"(<S>)? > {  
		if(mutationLLF==1){
			escribir=escribir+(";");
		}else if(parentesis>1){
			escribir=escribir+(")");
			parentesis--;
		}else if(parentesis==1){
			escribir=escribir+(");\n");
			parentesis--;
		}
	} : BLOQUE
}

//Declaración del estado PRINT
//A este estado se llega cuando se ha encontrado un bloque print, desde el estado BLOQUE
<PRINT>TOKEN:
{
		//Se encuentra la etiqueta TEXTOPRINT
		//Después va al estado TEXT
	<TEXTOPRINT: (<S>)?"field name=\"TEXT\">" > : TEXT
}

//Declaración del estado TEXT1
//A este estado se llega cuando se ha encontrado un texto dentro de otro texto, desde el estado SIGUIENTE
<TEXT1>TOKEN:
{
		//Se encuentra la etiqueta TEXT2
		//Después va al estado TEXT
	<TEXT2: (<S>)?"TEXT\">" > : TEXT
}

//Declaración del estado TEXT
//A este estado se llega para almacenar la frase que aparezca de texto, desde el estado PRINT y TEXT1
<TEXT>TOKEN:
{
		//Cuando se encuentra la etiqueta CARACTER, almacena en la variable texto todo lo que encuentra
		//Después va al estado SIGUIENTE
	< CARACTER: (~["<"])* > {
			texto = new String(image);
	} : SIGUIENTE
}	

//Declaración del estado SIGUIENTE
//A este estado se llega cuando ha almacenado la frase del print, desde el estado TEXT
<SIGUIENTE>TOKEN:
{
		//Se encuentra la etiqueta ETITEXT cuando hay otro bloque texto en el print
		//Después va al estado TEXT1
	<ETITEXT: (<S>)?"block type=\"text\""(<S>)? > : TEXT1
		//Se encuentra la etiqueta ETIPRINTNUM cuando hay un bloque que muestra un número
		//Se imprime código cFacil y se suma uno al contador parentesis
		//Después va al estado NUM
	|<ETIPRINTNUM: (<S>)?"block type=\"math_number\""(<S>)? > {escribir=escribir+("escribeNumero(");parentesis++;} : NUM
		//Se encuentra la etiqueta ETIBLOCKGET cuando hay un bloque que muestra una variable
		//Se imprime código cFacil y se suma uno al contador parentesis
		//Después va al estado NOM
	|<ETIBLOCKGET: (<S>)?"block type=\"variables_get\""(<S>)? > {escribir=escribir+("escribeNumero(");parentesis++;} : NOM
		//Se encuentra la etiqueta ETIFINVALU cuando termina el bloque print
		//Se imprime código cFacil y se suma uno al contador parentesis
		//Después va al estado BLOQUE
	|<ETIFINVALU: (<S>)?"</value"(<S>)? > {escribir=escribir+("escribeFrase(\""+texto+"\"");parentesis++;} : BLOQUE
		//Se encuentra la etiqueta ETIMATHARITHMETIC2 cuando hay un bloque de operaciones aritméticas
		//Se imprime código cFacil y se suma uno al contador parentesis
		//Después va al estado OP
	|<ETIMATHARITHMETIC2: (<S>)?"type=\"math_arithmetic\""(<S>)? > {escribir=escribir+("escribeNumero(");parentesis++;} : OP
		//Se encuentra la etiqueta ETITEXTJOIN cuando se trata de un bloque print con varios bloques
		//Después va al estado TEXTJOIN
	|<ETITEXTJOIN: (<S>)?"block type=\"text_join"(<S>)? > : TEXTJOIN
		//Se encuentra la etiqueta ETIADD2 cuando encuentra un bloque del text join
		//Después se va al estado  SIGUIENTE
	|<ETIADD2: (<S>)?"value name=\"ADD" > : SIGUIENTE
		//Se encuentra la etiqueta ETILLFUNCIONRETURN cuando llama a una función con return
		//Se imprime código cFacil y se actualizan variables
		//Después va al estado LLAMAFUNCION
	|<ETILLFUNCIONRETURN2: (<S>)?"block type=\"procedures_callreturn" > {escribir=escribir+("escribeNumero(");parentesis++;argFuncion=0;arg=0;llfuncion=1;argumentosLLFuncion=new ArrayList<String>();} : LLAMAFUNCION		

	

}

//Declaración del estado NOM
//A este estado se llega cuando hay que mostrar el nombre de una variable, desde el estado BLOQUE
<NOM>TOKEN:
{
		//Se encuentra la etiqueta CARACTER2 cuando después va el nombre de la variable
		//Después va al estado NOMVAR
	<CARACTER2: (<S>)?"VAR\">" > : NOMVAR
}

//Declaración del estado NOMVAR
//A este estado se llega para almacenar el nombre de la variable, desde el estado NOM
<NOMVAR>TOKEN:
{
		//Cuando se encuentra la etiqueta CARACTER3, almacena en la variable variable todo lo que encuentra
		//Después va al estado FINNOMVAR
	< CARACTER3: (~["<"])* > {
			variable = new String(image);
			escribir=escribir+(variable);
			if (set==1){
				conjuntoVariables.add(variable);
				set=0;
			}
	} : FINNOMVAR	
}

//Declaración del estado FINNOMVAR 
//A este estado se llega cuando se ha almacenado el nombre de la variable, desde el estado NOMVAR
<FINNOMVAR>TOKEN:
{
		//Se encuentra la etiqueta ETINAMEVALUE cuando se encuentra en un bloque de asignar variables
		//Se imprime código cFacil
		//Después va al estado BLOQUE
	< ETINAMEVALUE: (<S>)?"name=\"VALUE"(<S>)? > {escribir=escribir+(", ");} : BLOQUE
		//Se encuentra la etiqueta ETIFINVALU2 cuando se termina la variable
		//Después va al estado BLOQUE
	|<ETIFINVALU2: (<S>)?"</value"(<S>)? > : BLOQUE
}


//Declaración del estado NUM
//A este estado se llega cuando hay que mostrar un número, desde el estado BLOQUE, SIGUIENTE y SIG
<NUM>TOKEN:
{
		//Se encuentra la etiqueta CARACTER8 cuando después va el número
		//Después va al estado NUM2
	<CARACTER8: (<S>)?"NUM\">" > : NUM2
}

//Declaración del estado NUM2
//A este estado se llega para almacenar el número, desde el estado NUM
<NUM2>TOKEN:
{
		//Cuando se encuentra la etiqueta CARACTER9, se almacena en la variable variable4 el número
		//Después va al estado SIG
	< CARACTER9: (~["<"])* > {
			variable4 = new String(image);
	} : SIG	
}

//Declaración del estado SIG
//A este estado se llega cuando se ha almacenado el número, desde el estado NUM2
<SIG>TOKEN:
{
		//Se encuentra la etiqueta MATHNUMBER cuando hay otro bloque que muestra un número
		//Después va al estado NUM
	<MATHNUMBER: (<S>)?"type=\"math_number"(<S>)? > : NUM
		//Se encuentra la etiqueta FINVALUE cuando termina el bloque número
		//Se imprime código cFacil
		//Después va al estado BLOQUE
	|<FINVALUE: (<S>)?"/value"(<S>)? > {escribir=escribir+(variable4);} : BLOQUE
		//Se encuentra la etiqueta VARGET cuando hay un bloque que muestra una variable
		//Después va al estado NOM
	|<VARGET: (<S>)?"block type=\"variables_get"(<S>)? > : NOM
		//Se encuentra la etiqueta ETIMATHARITHMETIC3 cuando hay un bloque de operaciones aritméticas
		//Después va al estado OP
	|<ETIMATHARITHMETIC3: (<S>)?"type=\"math_arithmetic\""(<S>)? > : OP
}

//Declaración del estado TEXTPROMPT
//A este estado se llega cuando se encuentra un bloque prompt, desde el estado BLOQUE
<TEXTPROMPT>TOKEN:
{
		//Se encuentra la etiqueta ETIMUTATION cuando en el prompt se pide un número
		//Se imprime código cFacil y se suma uno al contador parentesis
		//Después va al estado FRASEPROMPT
	<ETIMUTATION: (<S>)?"mutation type=\"NUMBER"(<S>)? > {
			escribir=escribir+("pideNumero(\"");	
			parentesis++;			
	} : FRASEPROMPT
		//Se encuentra la etiqueta ETIMUTATIONTEXT cuando en el prompt se pide un texto
		//Se imprime código cFacil y se suma uno al contador parentesis
		//Después va al estado FRASEPROMPT
	|<ETIMUTATIONTEXT: (<S>)?"mutation type=\"TEXT"(<S>)? > {
			escribir=escribir+("pideCaracter(\"");	
			parentesis++;
	} : FRASEPROMPT
}

//Declaración del estado FRASEPROMPT
//A este estado se llega para almacenar la frase del prompt, desde TEXTPROMPT
<FRASEPROMPT>TOKEN:
{
		//Se encuentra la etiqueta CARACTER6 cuando después va la frase del prompt
		//Después va al estado FRASEPROMPT2
	<CARACTER6: (<S>)?"field name=\"TEXT\">" > : FRASEPROMPT2
}

//Declaración del estado FRASEPROMPT2
//A este estado se llega para almacenar la frase del prompt, desde el estado FRASEPROMPT 
<FRASEPROMPT2>TOKEN:
{
		//Cuando se encuentra la etiqueta CARACTER7, se almacena en la variable variable3 la frase del prompt
		//Después va al estado FRASEPROMPT3
	< CARACTER7: (~["<"])* > {
		variable3 = new String(image);
	} : FRASEPROMPT3
}

//Declaración del estado FRASEPROMPT3
//A este estado se llega cuandose ha almacenado la frase del prompt, desde el estado FRASEPROMPT2
<FRASEPROMPT3>TOKEN:
{
		//Se encuentra la etiqueta ETINUMBERPROMPT cuando la frase del prompt esta en otro bloque texto
		//Después va al estado TEXTOPROMPT
	<ETINUMBERPROMPT: (<S>)?"block type=\"text\""(<S>)? > : TEXTOPROMPT
		//Se encuentra la etiqueta ETIFINVALUEPROMPT cuando termina el bloque prompt
		//Se imprime código cFacil
		//Después va al estado BLOQUE
	|<ETIFINVALUEPROMPT: (<S>)?"</value"(<S>)? > {escribir=escribir+(variable3+"\"");} : BLOQUE
}

//Declaración del estado TEXTOPROMPT
//A este estado se llega cuando se llega a almacenar la frasae del prompt, desde el estado FRASEPROMPT3
<TEXTOPROMPT>TOKEN:
{
		//Se encuentra la etiqueta CARACTER10 cuando después va la frase del prompt
		//Después va al estado FRASEPROMPT2
	<CARACTER10: (<S>)?"TEXT\">" > : FRASEPROMPT2
}	

//Declaración del estado FUNCION
//A este estado se llega cuando se encuentra la definición de una función, desde el estado XML y ARGUM
<FUNCION>TOKEN:
{
		//Se encuentra la etiqueta NOMFUN cuando después viene el nombre de la función a definir
		//Después va al estado NOMFUNCION
	<NOMFUN: (<S>)?"NAME\">" > : NOMFUNCION
		//Se encuentra la etiqueta ARGUMENTOS cuando la función tiene argumentos
		//Se actualiza la variable argument
		//Después se va al estado ARGUN
	|<ARGUMENTOS: (<S>)?"arg name=\"" > {argument=1;} : ARGUN
}

//Declaración del estado NOMFUNCION
//A este estado se llega para almacenar el nombre de la función a definir, desde el estado FUNCION
<NOMFUNCION>TOKEN:
{
		//Cuando se encuentra la etiqueta NOMBRE,se almacena en la variable nombreFuncion el nombre de la función
		//Después se va al estado DESCRIPCIONFUNCION
	< NOMBRE: (~["<"])* > {
		nombreFuncion = new String(image);
	} : DESCRIPCIONFUNCION
}

//Declaración del estado ARGUN
//A este estado se llega para almacenar los argumentos de la función, desde el estado FUNCION
<ARGUN>TOKEN:
{
		//Cuando se encuentra la etiqueta ARGUM, se almacena en la lista argumentosFuncion los argumentos de la función
		//Después se va al estado FUNCION
	< ARGUM: (~["\""])* > {
		argumentosFuncion.add(new String(image));
	} : FUNCION
}

//Declaración del estado DESCRIPCIONFUNCION
//A este estado se llega cuando se encuentra la descripción de la función, desde el estado NOMFUNCION
<DESCRIPCIONFUNCION>TOKEN:
{
		//Se encuentra la etiqueta DESCRIPCI cuando después va la descipción de la variable a definir
		//Después va el estado DES
	<DESCRIPCI: (<S>)?"comment pinned"(<S>)? > : DES
}

//Declaración del estado DES
//A este estado se llega cuando después va la descipción de la función, desde el estado DESCRIPCIONFUNCION
<DES>TOKEN:
{
		//Se encuentra la etiqueta DESCRIPCI cuando después va la descipción de la variable a definir
		//Después va el estado DESCRIP
	<DESCRIPC: (<S>)?">" > : DESCRIP
}

//Declaración del estado DESCRIP
//A este estado se llega para añmacenar la descripción de la función a definir
<DESCRIP>TOKEN:
{
		//Cuando se encuentra la etiqueta DESCRIPCION, se almacena en la variable descripcionFuncion la descipción
		// de la función a definir
		//Después va el estado STATEMENT
	< DESCRIPCION: (~["<"])* > {
			descripcionFuncion = new String(image);
	} : STATEMENT
}

//Declaración del estado STATEMENT
//A este estado se llega cuando se termina la definición del nombre y los argumentos de la función, desde el estado DESCRIPCION
<STATEMENT>TOKEN:
{
		//Se encuentra la etiqueta STATEMENT, cuando se llega al final de la definición del nombre y los argumentos de la función
		//Se imprime código cFacil y se actualiza la variable llaves
		//Después va al estado BLOQUE
	<STATEM: (<S>)?"statement"(<S>)? > {
		System.out.printf("\n\n/*");
		System.out.printf(descripcionFuncion);
		System.out.printf("\n*/");
		if(ret==1){
			System.out.printf("\nRACIONAL "+nombreFuncion);
		}else{
			System.out.printf("\nPROCEDIMIENTO "+nombreFuncion);
		}
		if(argument==0){
			System.out.printf(" {\n");
		}else{
			System.out.printf("(");
			if(argumentosFuncion.size()>1){
				for ( int i=0; i < argumentosFuncion.size() - 1; i++){
					System.out.printf("RACIONAL "+argumentosFuncion.get(i)+",");
				}
			}
			System.out.printf("RACIONAL "+argumentosFuncion.get(argumentosFuncion.size()-1)+") {\n");
		}
		llaves++;
		argumentosFuncion=new ArrayList();
	}: BLOQUE
}

//Declaración del estado LLAMAFUNCION
//A este estado se llega cuando se encuentra la llamada de una función, desde el estado BLOQUE
<LLAMAFUNCION>TOKEN:
{
		//Se encuentra la etiqueta NOMBREFUN cuando después viene el nombre de la función llamada
		//Después va al estado LLAMAFUN
	<NOMBREFUN: (<S>)?"mutation name=\"" > : LLAMAFUN
}

//Declaración del estado LLAMAFUN
//A este estado se llega para almacenar el nombre de la función llamada, desde el estado LLAMAFUNCION 
<LLAMAFUN>TOKEN:
{
		//Cuando se encuentra la etiqueta FUNC, se almacena en la variable nombreFuncion2 el nombre de la función llamada
		//Se imprime código cFacil
		//Después va al estado ARGUMENTOSFUN
	< FUNC: (~["\""])* > {
			nombreFuncion2 = new String(image);
			escribir=escribir+(nombreFuncion2);
	} : ARGUMENTOSFUN
}

//Declaración del estado ARGUMENTOSFUN
//A este estado se llega cuando se conoce el nombre de la función llamada, desde el estado LLAMAFUN
<ARGUMENTOSFUN>TOKEN:
{
		//Se encuentra la etiqueta FINLLAMAFUNCION cuando se sale de la llamada a la función
		//Se actualiza la variable mutationLLF
		//Después se va al estado BLOQUE
	<FINLLAMAFUNCION: (<S>)?"/mutation"(<S>)? > {mutationLLF=1;} : BLOQUE
		//Se encuentra la etiqueta ARGUMENTO cuando la función llamada tiene argumentos
		//Se actualiza la variable argFuncion
		//Después vuelve al estado ARGUMENTOSFUN
	|<ARGUMENTO: (<S>)?"arg name"(<S>)? > {argFuncion++;} : ARGUMENTOSFUN
}

//Declaración del estado ARGU
//A este estado se llega cuando hay argumentos en la función llamada, desde el estado BLOQUE
<ARGU>TOKEN:
{
		//Se encuentra la etiqueta LLFVAR cuando los argumentos de la función llamada son variables
		//Después se va al estado NOMVARLLF
	<LLFVAR: (<S>)?"VAR\">" > : NOMVARLLF
		//Se encuentra la etiqueta LLFVAR cuando los argumentos de la función llamada son texto
		//Se actualiza la variable esTexto
		//Después se va al estado NOMVARLLF
	|<LLFTEXT: (<S>)?"TEXT\">" > {esTexto=1;} : NOMVARLLF
}

//Declaración del estado NOMVARLLF
//A este estado se llega para almacenar los argumentos de la función llamada, desde el estado ARGU 
<NOMVARLLF>TOKEN:
{
		//Cuando se encuentra la etiqueta LLFARG, se almacena en la variable argumentosLLFuncion los argumentos de la función llamada
		//Se actualiza la variable argumentosLLFuncion
		//Después va al estado FINLLF
	< LLFARG: (~["<"])* > {
			argumentosLLFuncion.add(new String(image));
	} : FINLLF	
}

//Declaración del estado FINLLF
//A este estado se llega cuando se termina de definir el nombre y los argmentos de la función llamada, desde el estado NOMVARLLF
<FINLLF>TOKEN:
{
		//Se encuentra la etiqueta ETIFINLLAMAFUNCION, cuando se llega al final de la definición del nombre y los argumentos de la función
		//Se imprime código cFacil
		//Después va al estado BLOQUE
	< ETIFINLLAMAFUNCION: (<S>)?"</value" > {
			if(llfuncion==1){
				if(argFuncion==arg){
					if(esTexto==1){
						escribir=escribir+("(");
						parentesis++;
						if(argumentosLLFuncion.size()>1){
							for ( int i=0; i < argumentosLLFuncion.size() - 1; i++){
								escribir=escribir+("\""+argumentosLLFuncion.get(i)+"\",");
							}
						}
						escribir=escribir+("\""+argumentosLLFuncion.get(argumentosLLFuncion.size()-1)+"\"");
					}else{
						escribir=escribir+("(");
						parentesis++;
						if(argumentosLLFuncion.size()>1){
							for ( int i=0; i < argumentosLLFuncion.size() - 1; i++){
								escribir=escribir+(argumentosLLFuncion.get(i)+",");
							}
						}
						escribir=escribir+(argumentosLLFuncion.get(argumentosLLFuncion.size()-1));
					}
					llfuncion=0;
					esTexto=0;
				}
			}
		} : BLOQUE
}

//Declaración del estado OP
//A este estado se llega cuando se ha encontrado una operación artimética, desde el estado BLOQUE, SIGUIENTE y SIG
<OP>TOKEN:
{
		//Se encuentra la etiqueta OPADD, cuando se se encuentra una operación suma 
		//Se imprime código cFacil y se actualiza la variable parentesis
		//Después va al estado BLOQUE
	<OPADD: "field name=\"OP\">ADD" > {escribir=escribir+("sumaNumeros(");parentesis++;} : BLOQUE
		//Se encuentra la etiqueta OPMINUS, cuando se se encuentra una operación resta
		//Se imprime código cFacil y se actualiza la variable parentesis
		//Después va al estado BLOQUE
	|<OPMINUS: "field name=\"OP\">MINUS" > {escribir=escribir+("restaNumeros(");parentesis++;} : BLOQUE
		//Se encuentra la etiqueta OPMULTIPLY, cuando se se encuentra una operación multiplicación
		//Se imprime código cFacil y se actualiza la variable parentesis
		//Después va al estado BLOQUE
	|<OPMULTIPLY: "field name=\"OP\">MULTIPLY" > {escribir=escribir+("productoNumeros(");parentesis++;} : BLOQUE
		//Se encuentra la etiqueta OPDIVIDE, cuando se se encuentra una operación división
		//Se imprime código cFacil y se actualiza la variable parentesis
		//Después va al estado BLOQUE
	|<OPDIVIDE: "field name=\"OP\">DIVIDE" > {escribir=escribir+("cocienteNumeros(");parentesis++;} : BLOQUE
}

//Declaración del estado OPLOGIC
//A este estado se llega cuando se ha encontrado una operación relacional, desde el estado BLOQUE
<OPLOGIC>TOKEN:
{
		//Se encuentra la etiqueta OPEQ, cuando se se encuentra una operación igual 
		//Se imprime código cFacil y se actualiza la variable parentesis
		//Después va al estado BLOQUE
	<OPEQ: "field name=\"OP\">EQ" > {escribir=escribir+("siIgualValor(");parentesis++;} : BLOQUE
		//Se encuentra la etiqueta OPNEQ, cuando se se encuentra una operación distinto 
		//Se imprime código cFacil y se actualiza la variable parentesis
		//Después va al estado BLOQUE
	|<OPNEQ: "field name=\"OP\">NEQ" > {escribir=escribir+("siDistintoValor(");parentesis++;} : BLOQUE
		//Se encuentra la etiqueta OPLT, cuando se se encuentra una operación menor 
		//Se imprime código cFacil y se actualiza la variable parentesis
		//Después va al estado BLOQUE
	|<OPLT: "field name=\"OP\">LT" > {escribir=escribir+("(siMenorValor(");parentesis++;} : BLOQUE
		//Se encuentra la etiqueta OPLTE, cuando se se encuentra una operación menor-igual 
		//Se imprime código cFacil y se actualiza la variable parentesis
		//Después va al estado BLOQUE
	|<OPLTE: "field name=\"OP\">LTE" > {escribir=escribir+("siMenorIgualValor(");parentesis++;} : BLOQUE
		//Se encuentra la etiqueta OPGT, cuando se se encuentra una operación mayor 
		//Se imprime código cFacil y se actualiza la variable parentesis
		//Después va al estado BLOQUE
	|<OPGT: "field name=\"OP\">GT" > {escribir=escribir+("siMayorValor(");parentesis++;} : BLOQUE
		//Se encuentra la etiqueta OPGTE, cuando se se encuentra una operación mayor-igual 
		//Se imprime código cFacil y se actualiza la variable parentesis
		//Después va al estado BLOQUE
	|<OPGTE: "field name=\"OP\">GTE" > {escribir=escribir+("siMayorIgualValor(");parentesis++;} : BLOQUE
}

//Declaración del estado VARRETURN
//A este estado se llega cuando hay que mostrar el nombre de la variable de retorno de una función, desde el estado VARRETURN2
<VARRETURN>TOKEN:
{
		//Se encuentra la etiqueta CAR cuando después va el nombre de la variable
		//Después va al estado VARRETURN2
	<CAR: (<S>)?"VAR\">" > : VARRETURN2
}

//Declaración del estado VARRETURN2
//A este estado se llega para almacenar el nombre de la variable, desde el estado VARRETURN
<VARRETURN2>TOKEN:
{
		//Cuando se encuentra la etiqueta VRETURN, almacena en la variable variable todo lo que encuentra
		//Después va al estado XML
	< VRETURN: (~["<"])* > {
			varReturn = new String(image);
			escribir=escribir+(varReturn+"\n}");
			llaves--;
	} : XML	
}

//Declaración del estado FINSTATEMENT
//A este estado se llega para terminar un bloque statement, desde el estado XML y BLOQUE
<FINSTATEMENT>TOKEN:
{
		//Se encuentra la etiqueta FINSTA para terminar una función
		//Se imprime código cFacil
		//Después va al estado XML
	<FINSTA: "</block" > {escribir=escribir+("}");llaves--;} : XML
		//Se encuentra la etiqueta  ETIELSE si hay una sentencia else
		//Se imprime código cFacil
		//Después va al estado BLOQUE
	|< ETIELSE: "statement name=\"ELSE\"" > {escribir=escribir+("}else{\n");} : BLOQUE
		//Se encuentra la etiqueta FINSTAT2 cuando la función tiene return
		//Se imprime código cFacil
		//Después va al estado VARRETURN
	|<FINSTAT2:  (<S>)?"name=\"RETURN" > {escribir=escribir+("\nreturn ");} : VARRETURN
		//Se encuentra la etiqueta ETINEXT3 si en la función hay otro bloque
		//Se imprime código cFacil
		//Después se va al estado BLOQUE
	|< ETINEXT3: "<next" > {escribir=escribir+("}\n");llaves--;} : BLOQUE
}

//Declaración del estado TEXTJOIN
//A este estado se llega para empezar con el primer bloque del text join, desde el estado SIGUIENTE
<TEXTJOIN>TOKEN:
{
		//Se encuentra la etiqueta ETIADD cuando está el primer bloque del text join
		//Después va al estado SIGUIENTE
	<ETIADD: (<S>)?"value name=\"ADD" > : SIGUIENTE
}

//Declaración del estado ETIFUNCION2
//A este estado se llega si hay una función para definir sin retorno, desde XML
<ETIFUNCION2>TOKEN:
{
		//Se encuentra la etiqueta ETIFUN2 si hay una función para definir sin retorno
		//Se actualiza la variable escribir y se inicializan variables
		//Después va al estado FUNCION
	<ETIFUN2: (<S>)?">" > {
		argument=0;
		ret=0;
		if(esFuncion==1){
			if(!conjuntoVariables.isEmpty()){
				System.out.printf("RACIONAL ");
				Iterator it = conjuntoVariables.iterator();
				while (it.hasNext()){
					listaVariables.add(it.next().toString());
				}
				if(listaVariables.size()>1){
					for ( int i=0; i < listaVariables.size() - 1; i++){
						System.out.printf(listaVariables.get(i)+",");
					}
				}
				System.out.printf(listaVariables.get(listaVariables.size()-1)+"; \n");
			}
			System.out.printf(escribir);
			escribir=new String();
			listaVariables=new ArrayList();
			conjuntoVariables=new HashSet();
		}
		esFuncion=1;
	} : FUNCION	
}

//Declaración del estado ETIFUNCIONRETURN2
//A este estado se llaga si hay una función para definir con retorno,desde el estado XML
<ETIFUNCIONRETURN2>TOKEN:
{
		//Se encuentra la etiqueta ETIFUNRET2 si hay una función para definir con retorno
		//Se actualiza la variable escribir y se inicializan variables
		//Después va al estado FUNCION
	<ETIFUNRET2: (<S>)?">" >	{
		argument=0;
		ret=1;
		if(esFuncion==1){
			if(!conjuntoVariables.isEmpty()){
				System.out.printf("RACIONAL ");
				Iterator it2 = conjuntoVariables.iterator();
				while (it2.hasNext()){
					listaVariables.add(it2.next().toString());
				}
				if(listaVariables.size()>1){
					for ( int i=0; i < listaVariables.size() - 1; i++){
						System.out.printf(listaVariables.get(i)+",");
					}
				}
				System.out.printf(listaVariables.get(listaVariables.size()-1)+"; \n");
			}
			System.out.printf(escribir);
			escribir=new String();
			listaVariables=new ArrayList();
			conjuntoVariables=new HashSet();
		}
			esFuncion=1;
	} : FUNCION
}	


//Declaración del estado ETIBLOQUE2
//A este estado se llega si hay un bloque en el programa, desde el estado XML	
<ETIBLOQUE2>TOKEN:
{
		//Se encuentra la etiqueta ETIBLO2 si hay un bloque en el programa
		//Se imprime el inicio del programa cFacil y se actaulizan variables
		//Después va al estado BLOQUE
	<ETIBLO2:  (<S>)?"k" > {
		if(esFuncion==1){
			if(!conjuntoVariables.isEmpty()){
				System.out.printf("RACIONAL ");
				Iterator it3 = conjuntoVariables.iterator();
				while (it3.hasNext()){
					listaVariables.add(it3.next().toString());
				}
				if(listaVariables.size()>1){
					for ( int i=0; i < listaVariables.size() - 1; i++){
						System.out.printf(listaVariables.get(i)+",");
					}
					System.out.printf(listaVariables.get(listaVariables.size()-1)+"; \n");
				}else{
					System.out.printf(listaVariables.get(listaVariables.size()-1)+"; \n");
				}
			}
			System.out.printf(escribir+"\n\nPRG(){\n");
			escribir=new String();
			listaVariables=new ArrayList();
			conjuntoVariables=new HashSet();
		}else{
			System.out.printf("\n\nPRG(){\n");
		}
		
	} : BLOQUE
}

//En cualquier estado, si encuentra cualquier caracter no hace nada
<*>SKIP :
{
   <~[]>
}
