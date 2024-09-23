#include <stdio.h>
#include <stdlib.h>
#include "lab3.h"


// Function to get the customer's pizza order
// Takes a pointer to the array of avaliable ingredients as an input
// Returns a pointer to an array of selected ingredients
char **get_thispizza(char **ingredients) {
	int numIngredients = 0;

	// Determine the number of available ingredients
	while (ingredients[numIngredients] != NULL) {
		numIngredients++;
	}

	// Collect number of ingredients selected from user input
	int orderSize;
	printf("Of our %d avsilable ingredients, how many do you plan to put on your pizza? ", numIngredients);
	scanf("%d", &orderSize);

	// Ensure that user does not select a number of ingredients that is invalid
	if (orderSize > numIngredients || orderSize < 1) {
		printf("Invalid number of ingredients. Please choose between 1 and %d.\n", numIngredients);
		return NULL;
	}

	// Memory allocated for an array of pointer of the selected ingredients
	char **thispizza = malloc((orderSize + 1) * sizeof(char *));
	if (thispizza == NULL) {
		printf("Memory allocation failed\n");
		exit(1);
	}

	// Loop to allow user to selected ingredients by the number value
	for (int i = 0; i < orderSize; i++) {
		int choice;
		printf("Enter the number of the ingredient you want? (1 to %d): ", numIngredients);
		scanf("%d", &choice);

		if (choice < 1 || choice > numIngredients) {
			printf("Invalid ingredients number. Try again.\n");
			i--;
			continue;
		}

		thispizza[i] = ingredients[choice - 1];
	}

	thispizza[orderSize] = NULL;

	return thispizza;

}
