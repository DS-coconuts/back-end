#include <stdio.h>

int main() {
    char target[] = "programming";
    char input[20];

    printf("Typing Practice: Please enter '%s': ", target);

    for (int i = 0; i < strlen(target); ++i) {
        scanf(" %c", &input[i]);
    }
    input[strlen(target)] = '\0';

    if (strcmp(input, target) == 0) {
        printf("You entered correctly!\n");
    }
    else {
        printf("There is a typo. Please try again.\n");
    }

    return 0;
}
