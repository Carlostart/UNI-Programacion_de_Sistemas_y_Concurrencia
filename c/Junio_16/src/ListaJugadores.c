/*
 * ListaJugadores.c
 *
 *  Created on: 16 de jun. de 2018
 *      Author: olaju
 */
#include "ListaJugadores.h"
#include <string.h>
#include <stdlib.h>
#include <stdio.h>


//crea una lista vacía (sin ningún nodo)
void crear(TListaJugadores *lj){
	*lj = NULL;
	//printf("Creado\n");
}
//inserta un nuevo jugador en la lista de jugadores, poniendo 1 en el número
//de goles marcados. Si ya existe añade 1 al número de goles marcados.
void insertar(TListaJugadores *lj,unsigned int id){
	if(*lj!=NULL){
		if((*lj)->id==id){
			(*lj)->goles=(*lj)->goles+1;
		//	printf("Gol %u\n", (*lj)->id);
		}else{
			insertar(&(*lj)->sig,id);
		}
	}else{
		(*lj) = malloc(sizeof(struct TNodo));
		crear(&(*lj)->sig);
		(*lj)->id=id;
		(*lj)->goles=1;
		//printf("Añadido y Gol %u\n", (*lj)->id);
	}
}
//recorre la lista de jugadores escribiendo los identificadores y los goles
// marcados
void recorrer(TListaJugadores lj){
	TListaJugadores aux = lj;
	while(aux != NULL){
		printf("ID: %u   Goles: %u\n", aux->id,aux->goles);
		aux = aux->sig;
	}
}
//devuelve el número de nodos de la lista
int longitud(TListaJugadores lj){
	int cont=0;
	TListaJugadores aux = lj;
	while(aux!=NULL){
		cont++;
		aux = aux->sig;
	}
	return cont;
}
//Eliminar. Toma un número de goles como parámetro y elimina todos los
// jugadores que hayan marcado menos que ese número de goles
void eliminar(TListaJugadores *lj,unsigned int n){
	if(*lj!=NULL){
		if((*lj)->goles<n){
			TListaJugadores aux = (*lj)->sig;
			free(*lj);
			*lj = aux;
			eliminar(lj,n);
		}else {
			eliminar(&(*lj)->sig,n);
		}
	}
}

// Devuelve el ID del máximo goleador. Si la lista está vacía, devuelve 0. Si
//hay más de un jugador con el mismo número de goles que el máximo devuelve
//el de mayor ID. Hay que devolver el identificador, no el número de goles
//que ha marcado
unsigned int maximo(TListaJugadores lj){
	unsigned int max = 0;
	unsigned int id = 0;
	TListaJugadores aux = lj;
	while(aux!=NULL) {
		if(max < aux->goles && id < aux->id){
			max = aux->goles;
			id = aux->id;
		}
		aux=aux->sig;
	}
	return id;
}
//Destruye la lista y libera la memoria)
void destruir(TListaJugadores *lj){
	TListaJugadores aux = *lj;;
	while(aux!=NULL){
		aux = aux->sig;
		free(*lj);
		*lj = aux;
	}
}
