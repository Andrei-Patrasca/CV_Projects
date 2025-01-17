#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "fileIO.h"

char global_name[200];

int main()
{
    char first_name[100], last_name[100];
    char title[200], author[200];

    printf("===============Welcome to the Library================\n");
    printf("=           (1)Login              (2)Sign up        =\n");
    printf("=---------------------------------------------------=\n");
    printf("Input your choice (1 or 2): ");

    log_in_sign_up();

    char n = '0';
    while(n!='6')
        {
    printf("\n");
    printf("=====================================================  \n");
    printf("         (1)Borrow Books        (2)Return Books        \n");
    printf("         (3)View Loans          (4)Search for Books    \n");
    printf("         (5)Donate Book         (6)Exit                \n");
    printf("=====================================================\n\n");

    printf("Enter your choice:\n");
    scanf("%s", &n);

    switch (n) {
        case '1':
             Borrow_Books();
            break;
        case '2':
            return_books();
            break;
        case '3':
            view_loans();
            break;
        case '4':
            search_for_books();
            break;
        case '5':
            donate_books();
            break;
        case '6':
            exit_lib();
            break;
        default:
            printf("Invalid choice.\n");
            break;
    }
        }
    return 0;
}
