/*
 * MProcesos.c
 *
 *  Created on: 18/06/2012
 *      Author: luisll
 */



#include "Mprocesos.h"
#include <stdio.h>
#include <stdlib.h>

void Crear(LProc *lroundrobin) {
	*lroundrobin = NULL;
}

void AnadirProceso(LProc *lroundrobin, int idproc) {
	if((*lroundrobin) !=NULL){
		LProc aux = malloc(sizeof(struct Nodo));
		aux->id=idproc;
		aux->sig= (*lroundrobin)->sig;
		(*lroundrobin)->sig=aux;
		*lroundrobin=aux;
	}else{
		(*lroundrobin)=malloc(sizeof(struct Nodo));
		(*lroundrobin)->id=idproc;
		(*lroundrobin)->sig=(*lroundrobin);
	}
}

void EjecutarProcesos(LProc lroundrobin) {
	if(lroundrobin==NULL){
		printf("Lista vacia\n");
	}else{
		int fin = lroundrobin->id;
		int cont =0;
		lroundrobin=lroundrobin->sig;
		while(lroundrobin->id!=fin){
			printf("Proceso %i -> %i\n",cont,lroundrobin->id);
			cont++;
			lroundrobin=lroundrobin->sig;
		}
		printf("FIN-> Proceso %i -> %i\n",cont,fin);
	}
}


void EliminarProceso(int id, LProc *lista) {
	int principio = (*lista)->id;
	while((*lista)->sig->id!=id){
		(*lista) = (*lista)->sig;
	}

	LProc aux=(*lista)->sig->sig;

	free((*lista)->sig);
	(*lista)->sig = aux;
	if(id!=principio){
		while((*lista)->id!=principio)
			(*lista)=(*lista)->sig;
	}
}

void EscribirFichero (char * nomf, LProc *lista) {
	if(*lista==NULL){
	} else {
		FILE* f;
		f=fopen(nomf,"wb");

		int principio = (*lista)->id;
		int cont=1;
		(*lista)=(*lista)->sig;
		while((*lista)->id!=principio){
			cont++;
			(*lista)=(*lista)->sig;
		}


		fwrite(&cont,4,1,f);
		printf("%i ",cont);

		LProc aux;
		(*lista)=(*lista)->sig;
		while((*lista)->id!=principio){
			fwrite(&(*lista)->id,4,1,f);
			printf("%i ",(*lista)->id);
			aux=(*lista)->sig;
			free(*lista);
			(*lista)=aux;
		}
		fwrite(&(*lista)->id,4,1,f);
		printf("%i \n",(*lista)->id);
		free(*lista);
		*lista=NULL;
		fclose(f);
	}

}
