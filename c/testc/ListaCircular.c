//
// Created by migov on 08/04/2018.
//

//#include "ListaCircular.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//crea una lista circular vacia (sin ningun nodo)
void crear(TListaCircular *lc){
    *lc=NULL;
}

//inserta un nuevo nodo con el dato nombre al final de la lista circular
void insertar(TListaCircular *lc,char *nombre){
    if(*lc==NULL){
        TListaCircular l = malloc(sizeof(struct TNodo));
        strcpy((l)->nombre, nombre);
        (l)->sig=l;
        *lc=l;
    }else {
        TListaCircular l = malloc(sizeof(struct TNodo));
        strcpy(l->nombre, nombre);
        l->sig = (*lc)->sig;
        (*lc)->sig=l;
        *lc=l;
    }
}

//recorre la lista circular escribiendo los nombres de los nodos en la
//pantalla
void recorrer(TListaCircular lc){
    TListaCircular aux = lc;
    TListaCircular primero = lc;
    while (primero!=aux->sig){
        printf("%s, ",aux->nombre);
        aux = aux->sig;
    }
    printf("%s.",aux->nombre);
}

//devuelve el numero de nodos de la lista
int longitud(TListaCircular lc){
    if (lc!=NULL) {
        TListaCircular primera = lc;
        TListaCircular aux = primera;
        int contador = 1;
        while (primera != aux->sig) {
            aux = aux->sig;
            contador++;
        }
        return contador;
    } else return 0;
}

//mueve el puntero exterto de la lista n nodos (siguiendo la direccion de la
//lista)
void mover(TListaCircular *lc,int n) {
    TListaCircular *aux = lc;
    TListaCircular *siguiente = &(*lc)->sig;
    if (*lc != NULL) {
        if (n != 0) {
        	int i;
            for (i = 0; i < n; i++) {
                aux = &(*aux)->sig;
                siguiente = &(*aux)->sig;
                strcpy((*aux)->nombre, (*siguiente)->nombre);
            }
        }
        *lc = *aux;
    }
}

//elimina el primer nodo de la lista, y devuelve el nombre que contiene
//a traves del parametro nombre
void extraer(TListaCircular *lc,char *nombre) {
    TListaCircular aux = *lc;
    TListaCircular *aExtraer = &(*lc)->sig;
    aux->sig = (*aExtraer)->sig;
    strcpy(nombre, (aux->sig)->nombre);
    free(*aExtraer);
}
