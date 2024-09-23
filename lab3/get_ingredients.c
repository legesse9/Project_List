#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lab3.h"

char **get_ingredients() {

	int numIngredients;

	// Collects the number of ingredients availabe prompted by user
	printf("How many available pizza ingredients do we have today?");
	scanf("%d", &numIngredients);

	// Memory allocated for an array of ingredients
	char **ingredients = malloc((numIngredients + 1) * sizeof(char *));
	if (ingredients == NULL) {
		printf("Memory allocation failed.\n");
		exit(1);
	}

	// Loop to get each ingredient in array of ingredients using get_item()
	for (int i = 0; i < numIngredients; i++) {
		ingredients[i] = get_item();
	}

	ingredients[numIngredients] = NULL;

	return ingredients;
}
