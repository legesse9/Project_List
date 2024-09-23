#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lab3.h"

// Function to read a single ingredient string from the user
char *get_item() {
	static char buffer[61]; // 60 characters plus 1 for the null
	printf("Enter an ingredient: ");
	scanf(" %[^\n]", buffer); // Read a line of input until a newline

	// Memory allocated for ingredients
	char *ingredient = malloc(strlen(buffer) + 1);
	if (ingredient == NULL) {
		printf("Memory allocation failed.\n");
		exit(1);
	}

	
	strcpy(ingredient, buffer);

	return ingredient;
}
