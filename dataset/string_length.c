#include <stdio.h>
#include <string.h>

int main() {
    char input[50];
    printf("Typing Practice: Enter any sentence: ");
    scanf("%[^\n]", input);

    printf("Length of the entered sentence: %d\n", strlen(input));

    return 0;
}
