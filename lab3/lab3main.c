#include <stdio.h>
#include <stdlib.h>
#include "lab3.h"

int main() {
	char **ingredients = NULL;
	char **thispizza = NULL;

	// Get the list of available ingredients
	ingredients = get_ingredients();

	// Display the list of available ingredients
	printf("\nAvailable ingredients today:\n");
	for (int i = 0; ingredients[i] != NULL; i++) {
		printf("%d. %s\n", i + 1, ingredients[i]);
	}

	// Get the pizza order
	thispizza = get_thispizza(ingredients);

	// Display the pizza order
	printf("\nThe ingredients on your pizza will be:\n");
	for (int i = 0; thispizza[i] != NULL; i++) {
		printf("%d. %s\n", i + 1, thispizza[i]);
	}

	// Save available ingredients and pizza order
	save_info(ingredients, thispizza);

	// Free all allocated memory
	free_dmem(ingredients, &thispizza);

	return 0;

}
