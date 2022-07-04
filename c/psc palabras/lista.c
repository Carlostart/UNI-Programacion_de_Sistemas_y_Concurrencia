/*
 * lista.c
 *
 *  Created on: 12 de abr. de 2018
 *      Author: olaju
 */
#include "lista.h"
#include <string.h>
#include <stdlib.h>

void crear(Lista *l){
	*l=NULL;
}

/*
 * Compruba si un a lista esta vacía
 * Devuelve 0 si NO lo está
 */
int lista_vacia(Lista l){
	int i=1;
	Lista aux = l;
	if (aux!=NULL)
		i=0;
	return i;
}

/*
 * Escribe en consola el contenido de una lista de palabras separadas por coma
 * l: lista enlazada de palabras
 */
void escribir(Lista l){
	if(l!=NULL)
		escribir_fichero(stdout,l);
}

/*
 * Escribe en un fichero de salida el contenido de una lista de palabras separadas por coma
 * fp: Puntero a un objeto FILE que identifica el stream de salida
 * l: lista enlazada de palabras
 */
void escribir_fichero(FILE * fp, Lista l){
	Lista aux = l;
	while(!lista_vacia(aux->sig)){
		fprintf(fp,"%s, ",aux->palabra);
		aux=aux->sig;
	}
	fprintf(fp,"%s.\n",aux->palabra);
}

/*
 * Inserta una palabra al final de una lista enlazada.
 * No comprueba si la palabra existe, si se desea no repetir palabras
 * se debe utilizar buscar_palabra() y comprobar antes de invocar esta función
 * palabra: la palabra que se desea insertar
 * l: lista enlazada de palabras
 */
void insertar(char* palabra, Lista* l){
	Lista aux = *l;
	if(aux!=NULL){
		while(!lista_vacia(aux->sig))
			aux=aux->sig;
		aux->sig=malloc(sizeof(struct item));
		crear(&aux->sig->sig);
		strcpy(aux->sig->palabra,palabra);
		l=&aux;
	} else{
		aux=malloc(sizeof(struct item));
		crear(&aux->sig);
		strcpy(aux->palabra,palabra);
		*l = aux;
	}
}

/*
 * Elimina todos los items de la lista enlazada
 * Debe delvolver la memoria dinamica utilizada para cada uno de ellos
 * Para comprobar que se eliminan los items
 * escriba un mensaje por consola indicando la palabra de item que se va a eliminar
 * l: La lista enlazada que se desea eliminar
 */
void destruir(Lista* l){
	Lista aux = *l;
	while(!lista_vacia(aux->sig)){
		printf("Se ha eliminado: %s\n",aux->palabra);
		*l=aux->sig;
		free(aux);
		aux=*l;
	}
	printf("Se ha eliminado: %s\n",aux->palabra);
	free(aux);
}

/*
 * Comprueba si una palabra esta en la lista enlazada
 * palabra: la palabra que se desea buscar
 * l: lista enlazada de palabras
 * Devuelve 0 si la palabra NO está en la lista
 * Devuelve 1 si la palabra Sí está en la lista
 */
int buscar_palabra(char* palabra, Lista l){
	Lista aux = l;
	while(!lista_vacia(aux->sig)){
		if(strcmp(palabra,aux->palabra)==0){
			return 1;
		}
		aux=aux->sig;
	}
	return 0;
}

