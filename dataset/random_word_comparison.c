#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    char words[][10] = { "apple", "banana", "orange", "grape", "melon" };
    int randomIndex = rand() % 5;

    printf("Typing Practice: Please enter '%s': ", words[randomIndex]);

    char input[20];
    scanf("%s", input);

    if (strcmp(input, words[randomIndex]) == 0) {
        printf("You entered correctly!\n");
    }
    else {
        printf("There is a typo. Please try again.\n");
    }

    return 0;
}
