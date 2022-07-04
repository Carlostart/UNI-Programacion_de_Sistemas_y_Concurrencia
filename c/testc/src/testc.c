/*
 ============================================================================
 Name        : testc.c
 Author      : a
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
 /*
	   FILE *fp;
	   char str[60];

	   fp = fopen("listaNombres.txt" , "r");
	   if(fp == NULL) {
	      perror("Error opening file");
	      return(-1);
	   }
	   if( fgets (str, 60, fp)!=NULL ) {
	      puts(str);
	   }
	   fclose(fp);


	int* a;
	a = (int*)malloc(sizeof(int));
	free(a);

	int* b;
	b = (int*)malloc(sizeof(int));
	free(b);
	if(a==b)
		printf("hola"); */


	char a[] = "hola";
	char b[] = "hola";

	while(strcmp(a,b)==0){
		printf("a");
	}

	system("PAUSE");
	return(0);
}
