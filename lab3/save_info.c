#include <stdio.h>
#include <stdlib.h>
#include "lab3.h"

// Function to save pizza order
// Takes a pointer to an array of available ingredients and ingredients selected as inputs
void save_info(char **ingredients, char **thispizza) {
	int saveChoice;

	// Decides if user wants to save pizza order
	printf("Do you want to save the order to a file? (1 = yes, 2 = no): ");
	scanf("%d", &saveChoice);

	if (saveChoice != 1) {
		printf("You chose not to save the order.\n");
		return;
	}

	// Creates an array of characters to store user inputed filename
	char filename[100];
	printf("Enter the filename where the order should be saved: ");
	scanf("%s", filename);

	// Open file to write info
	FILE *file = fopen(filename, "w");
	if (file == NULL) {
		printf("Error: Could not open the file. \n");
		return;
	}

	// Write avaliable ingredients from get_ingredients function
	fprintf(file, "Available Pizza Ingredients today:\n");
	for (int i = 0; ingredients[i] != NULL; i++) {
		fprintf(file, "%s\n", ingredients[i]);
	}

	// Write the customers selected ingredients from get_thispizza function
	fprintf(file, "\nIngredients on this pizza:\n");
	for (int i = 0; thispizza[i] != NULL; i++) {
		fprintf(file, "%s\n", thispizza[i]);
	}

	// Close file
	fclose(file);
	printf("Order saved successfully to '%s'.\n", filename);
}


