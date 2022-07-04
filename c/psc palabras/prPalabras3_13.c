/*
 ============================================================================
 Name        : prPalabras3_13.c
 Author      : PONGA SU NOMBRE AQUI <<<<<<<<<<<<<<<<<<<<<
 Version     : 1
 Copyright   : Examen parcial abril 2017
 Description : Programa principal, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lista.h"
#define MIN_LETRA (3)
#define MAX_LETRA (13)
#define NUM_TAMANO (100)


void escribir_salida(FILE * fp, Lista* lp);

void escribir_salida(FILE * fp, Lista* lp){
	escribir_fichero(fp,*lp);

}

int main(void) {

	setvbuf(stdout, NULL, _IONBF, 0);
	setvbuf(stderr, NULL, _IONBF, 0);
	char *inputFileName = "Lorem_Ipsum.txt";
	char *outputFileName = "Palabras3_13.txt";
	int i;

	/* Crear Array de Palabras de tamaño NUM_TAMANO */

	Lista palabras[NUM_TAMANO];

	/* Incializar lista palabras */


	for(i=0;i<NUM_TAMANO;i++)
		crear(&palabras[i]);

	/* Abrir Fichero de Entrada */

	FILE* f = fopen(inputFileName, "r");

	/* Leer palabras del fichero de entrada */


	char str[30];
	fscanf(f,"%s",str);
	while(!feof(f)){
		int tam=strlen(str);
		//printf("%i->%s\n",tam,str);
		insertar(str,&palabras[tam]);
		fscanf(f,"%s",str);
	}
	fclose(f);
	/* Escribir en consola */

	for(i=0;i<NUM_TAMANO;i++){
		printf("Palabras de %i letras: ", i);
		escribir(palabras[i]);
	}

	/* Escribir archivo (para el apartado B) */

	FILE* fo = fopen(outputFileName,"w");

	for(i=0;i<NUM_TAMANO;i++){
		printf("Palabras de %i letras: ", i);
		escribir_salida(fo,&palabras[i]);
	}
	fclose(fo);

	/* Destruir las listas creadas */

	for(i=0;i<NUM_TAMANO;i++){
		destruir(&palabras[i]);
	}

	return EXIT_SUCCESS;
}
