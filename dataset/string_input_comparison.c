#include <stdio.h>
#include <string.h>

int main() {
    char input[50];
    printf("Typing Practice: Please enter 'Hello': ");
    scanf("%s", input);

    if (strcmp(input, "Hello") == 0) {
        printf("You entered correctly!\n");
    }
    else {
        printf("There is a typo. Please try again.\n");
    }

    return 0;
}
