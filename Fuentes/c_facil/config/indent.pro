//~/.indent.pro
// Fichero de configuración de indent para las prácticas de Programación (2ºsemestre GºIng.Informática EPS-UBu)
// v 2011/12 by CPA.LSI.IngCivil
//   Esta obra está bajo una licencia Creative Commons Reconocimiento-NoComercial 3.0 España. http://creativecommons.org/licenses/by-nc/3.0/es/
	-nbad -bap -nbbb -sob
/* !=	 -bad -nbap -bbb -nsob */
	-nfc1 -nfca -ncdb -nsc -lc75	// -c33 -cd33 -cp33 -d0
/* !=	 -fc1  -nfa  -cdb  -sc */
	-br        -ce -cli2 -cdw  -ss -npcs -ncs -nsaf -nsai -nsaw -nprs -nbs
/* !=	-bl -bli0 -nce      -ncdw -nss  -pcs  -cs  -saf  -sai  -saw  -prs  -bs */
	-di8 -nbc -nbfda -nbfde -npsl -brs -brf	//-idN -djn -ndj
/* !=	      -bc  -bfda  -bfde  -psl -bls -blf */
	-i2 -ci4 -lp -pi1 -ip1 -ppi1     -il1 -ts8
/* !=	        -nlp           -lps -nlps		*/
	-l75 -bbo  -hnl
/* !=	    -nbbo -nhnl */
	-v

/*opciones preconfiguradas para el proyecto gnu
-gnu:	 -nbad !-saw  -bbo    -bap   -ncdb          -ip5         !-bl   !-pcs         !-nsob -i2
	!-saf   -nprs -hnl    -nfc1  -nsc     -di2               !-bli2 !-bls
	!-sai   -lp           -nfca  -nbc    !-psl          -cp1 !-nce   -ndj         !-cs		*/
/*opciones preconfiguradas para el kernel de linux
-linux:	 -nbad !-saw  -bbo    -bap   -ncdb    -d0   -ip0    -c33  -br    -npcs -ci4    -sob  -i8  -l80
	!-saf   -nprs -hnl    -nfc1  -nsc     -di1          -cd33 -ce    -brs          -ss   -il1
	!-sai   -lp           -nfca  -nbc     -npsl         -cp33 -cli0                -ncs  -ts8	*/
/*opciones preconfiguradas para la sintaxis original Kernighan & Ritchie
-kr:	 -nbad !-saw  -bbo    -bap   -ncdb    -d0   -ip0    -c33  -br    -npcs -ci4   !-nsob -i4  -l75
	!-saf   -nprs -hnl    -nfc1  -nsc     -di1          -cd33 -ce    -brs         !-nss
	!-sai   -lp           -nfca  -nbc     -npsl         -cp33 -cli0               !-cs		*/
/*opciones preconfiguradas en la primera version del indent (Berkeley)
-orig:	 -nbad !-saw  -bbo   !-nbap !-cdb           -ip4    -c33  -br    -npcs -ci4   !-nsob -i4  -l75
	!-saf   -nprs -hnl   !-fc1  !-sc      -di16         -cd33 -ce    -brs         !-nss
	!-sai   -lp          !-fca  !-bc     !-psl          -cp33 -cli0                      -ts8	*/
