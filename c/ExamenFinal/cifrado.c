// IMPLEMENTA EN ESTE FICHERO LAS FUNCIONES DECLARADAS EN cifrado.h

#include "cifrado.h"
#include <stdio.h>
#include <stdlib.h>


/* (0.5 puntos) funcion necesaria para crear un esquema de cifrado vacio, sin nodos*/
void crearEsquemaDeCifrado (TCifrado *cf){
	*cf=NULL;
}

/* (3 puntos) funcion que pone un nodo al final de un esquema de cifrado, si es posible.
 * Se debe devolver en el ultimo parametro un valor logico que sea verdadero si ha sido posible
 * realizar la operacion. No se debe suponer que el valor de box.sig es valido*/
void insertarBox (TCifrado * cf, struct TBox box, unsigned char *ok){
	if(*cf!=NULL){
		TCifrado aux = *cf;
		while((*cf)->sig!=NULL){
			(*cf)=(*cf)->sig;
		}
		if((*cf)->esSBox != box.esSBox){
			(*cf)->sig=malloc(sizeof(struct TBox));
			(*cf)->sig->bitACambiar=(&box)->bitACambiar;
			(*cf)->sig->esSBox=(&box)->esSBox;
			(*cf)->sig->valorASumar=(&box)->valorASumar;
			crearEsquemaDeCifrado(&(*cf)->sig->sig);
			cf=&aux;
			*ok=1;
		}else
			*ok=0;
	} else{
		if(box.esSBox > 0){
			(*cf)=malloc(sizeof(struct TBox));
			(*cf)->bitACambiar=(&box)->bitACambiar;
			(*cf)->esSBox=(&box)->esSBox;
			(*cf)->valorASumar=(&box)->valorASumar;
			crearEsquemaDeCifrado(&(*cf)->sig);
			*ok=1;
		}else
			*ok=0;
	}
}

/* (1.5 puntos) funcion que dado un nodo y un valor, devuelve
 * el resultado de aplicar dicho nodo a dicho valor. Deberas
 * de tener en cuenta si el nodo es una SumaBox o una XORBox.
 * En el ultimo caso, necesitaras usar operadores logicos a
 * nivel de bit, como &, |, ^ o bien ~, asi como probablemente
 * usar constantes  numericas. */
unsigned char aplicarBox (struct TBox box, unsigned char valor){
	if(box.esSBox){
		return valor + box.valorASumar;
	}else{
		if(box.bitACambiar) // pos 7
			return !(valor^0x8000);
		else
			return (!valor^1);
	}
}

/* (1.5 puntos) funcion que toma un esquema de cifrado y un valor,
 * y devuelve el resultado de aplicar dicho esquema de cifrado a
 * dicho valor, segun el metodo descrito anteriormente.*/
unsigned char aplicarEsquemaDeCifrado(TCifrado cf, unsigned char valor){
	while(cf!=NULL){
		valor=aplicarBox(*cf,valor);
		cf=cf->sig;
	}
	return valor;
}

/* (2.5 puntos) funcion que toma un nombre de fichero, en el que se
 *  escribiran en modo binario los datos correspondientes al esquema
 *  de cifrado que se pasa como parametro, de modo que el al final el
 *  fichero unicamente contenga dichos datos. Si no se puede abrir el
 *  fichero, se debe de mostrar un mensaje de error por pantalla.*/
void escribirAFichero(char *nm, TCifrado cf){
	FILE* f;
	f=fopen(nm,"wb");
	if(f==NULL){
		perror("No se ha podido leer el fichero");
	}else{
		fwrite(&cf,sizeof(cf),1,f);
	}
}

/* (1.0 puntos) funcion que destruye un esquema de cifrado y libera la memoria que ocupa*/
void destruirEsquemaDeCifrado (TCifrado *cf){
	TCifrado aux = *cf;
	while(aux!=NULL){
		aux=(*cf)->sig;
		free(*cf);
		*cf=aux;
	}
	free(*cf);
}
