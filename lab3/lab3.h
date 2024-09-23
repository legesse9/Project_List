#ifndef LAB3_H
#define LAB3_H

char **get_ingredients();

char *get_item();

char **get_thispizza(char **ingredients);

void save_info(char **ingredients, char **thispizza);

void free_dmem(char **ingredients, char ***thispizza);

#endif
