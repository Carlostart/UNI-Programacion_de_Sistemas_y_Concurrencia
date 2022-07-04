/*
 * Colaprio.c
 *
 *  Created on: 18 jun. 2018
 *      Author: olaju
 */

#import "Colaprio.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void Crear_Cola(char *nombre, TColaPrio *cp){
	FILE * f;
	f = fopen(nombre,"rb");
	if(f==NULL){
		perror("Error opening file");
		exit(-1);
	}

	int *n;
	fread(n,4,1,f);
	int v[1];
	int i;
	for(i = 0;i<*n;i++){
		fread(v,4,2,f);
		printf("%i, %i\n", v[0], v[1]);
	}
}
void Mostrar(TColaPrio cp){

}
void Destruir(TColaPrio *cp){

}
void Ejecutar_Max_Prio(TColaPrio *cp){

}
void Ejecutar(TColaPrio *cp, int prio){

}
