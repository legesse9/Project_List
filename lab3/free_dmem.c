#include <stdio.h>
#include <stdlib.h>
#include "lab3.h"

// Function to free all allocated memory
void free_dmem(char **ingredients, char ***thispizza) {

	// Free each ingredient in the array of ingredients
	for (int i = 0; ingredients[i] != NULL; i++) {
		free(ingredients[i]);
	}

	// Free the array of ingredient pointers
	free(ingredients);

	// Free the array of pointers to selected ingredients and free the pointer to the array itself
	free(*thispizza);
	free(thispizza);
}
