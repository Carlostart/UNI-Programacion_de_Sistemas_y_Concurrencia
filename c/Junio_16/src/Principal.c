/*
 * Principal.c
 *
 *  Created on: 14/6/2016
 *      Author: esc
 */

#include "ListaJugadores.h"
#include <stdio.h>
#include <stdlib.h>



// Lee el fichero y lo introduce en la lista
void cargarFichero (char * nombreFich, TListaJugadores *lj) {
	FILE *f;
	unsigned int id[2];
	f = fopen(nombreFich,"rb");
	if(f==NULL){
		perror("Error opening file");
		exit(-1);
	}

	while (!feof(f)) {
		fread (id, 4, 3, f);
		printf("%u | ", id[0]);
		printf("%u | ", id[1]);
		printf("%u |\n", id[2]);

		insertar(lj,id[1]);

	}
	fclose(f);
}


int main(){
	//printf("hola");
	TListaJugadores lj;
	crear(&lj);
    unsigned int num_goles;
	cargarFichero ("goles.bin",&lj);
	printf("Hay un total de %d jugadores\n",longitud(lj));
	fflush(stdout);

	recorrer(lj);
	fflush(stdout);
	printf("Introduce un número de goles: \n");
	fflush(stdout);
	scanf("%d",&num_goles);


	eliminar(&lj,num_goles);
	printf("--------------------------------------\n");
	recorrer(lj);
	printf("Hay un total de %d jugadores\n",longitud(lj));
	fflush(stdout);

	printf ("El jugador que más goles ha marcado es el que tiene ID: %d",maximo(lj));
	fflush(stdout);
	destruir (&lj);

	return 0;
}
