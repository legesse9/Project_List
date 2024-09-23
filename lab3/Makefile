# comments in a Makefile start with sharp

# Compiler options with debugging and standard settings
gcc_opt = -std=c99 -pedantic -Wimplicit-function-declaration -Wreturn-type -Wformat -g -c

# Target 'all' builds everything defined below
all: lab3.zip lab3

#Target for the zip file that must be submitted to Carmen
lab3.zip: Makefile lab3main.c get_ingredients.c get_item.c get_thispizza.c save_info.c free_dmem.c lab3Readme lab3.h
	zip lab3.zip Makefile lab3main.c get_ingredients.c get_item.c get_thispizza.c save_info.c free_dmem.c Lab3Readme lab3.h

#Target for creating the final executable for lab3
lab3: lab3main.o get_ingredients.o get_item.o get_thispizza.o save_info.o free_dmem.o
	gcc lab3main.o get_ingredients.o get_item.o get_thispizza.o save_info.o free_dmem.o -o lab3

# Compile target for the main program
lab3main.o: lab3main.c lab3.h
	gcc $(gcc_opt) lab3main.c

#get_ingred, get_item, get_thispizza, save_info, free_demm

# Compile target for get_ingredients
get_ingredients.o: get_ingredients.c lab3.h
	gcc $(gcc_opt) get_ingredients.c

# Compile target for get_item
get_item.o: get_item.c lab3.h
	gcc $(gcc_opt) get_item.c

# Compile target for get_thispizza
get_thispizza.o: get_thispizza.c lab3.h
	gcc $(gcc_opt) get_thispizza.c

# Compile target for save_info
save_info.o: save_info.c lab3.h
	gcc $(gcc_opt) save_info.c

# Compile target for free_dmem
free_dmem.o: free_dmem.c lab3.h
	gcc $(gcc_opt) free_dmem.c

# Clean target to delete all compiled and generated files
clean:
	rm -f *.o lab3 lab3.zip
