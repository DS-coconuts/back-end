#include <stdio.h>
#include <string.h>

int main() {
    char str1[20], str2[20];

    printf("Typing Practice: Enter two strings\n");
    printf("String 1: ");
    scanf("%s", str1);

    printf("String 2: ");
    scanf("%s", str2);

    if (strcmp(str1, str2) == 0) {
        printf("The two strings are identical!\n");
    }
    else {
        printf("The two strings do not match.\n");
    }

    return 0;
}
