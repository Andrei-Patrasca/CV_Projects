#include "fileIO.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
char global_name[200];
char first_name[100], last_name[100];
char title[200], author[200];

void log_in_sign_up(void) {
    char n = '0';
    while (n != '1' && n != '2') {
        scanf("%s", &n);
        switch (n) {
        case '1': {
            int ok = 0;
            while (ok != 1) {
                printf("Your first name: ");
                scanf(" %[^\n]", first_name);
                printf("Your second name: ");
                scanf(" %[^\n]", last_name);

                strcpy(global_name, first_name);
                strcat(global_name, " ");
                strcat(global_name, last_name);

                int result = search_two_elements_in_same_line_csv("AccountName.csv", first_name, last_name);

                if (result) {
                    printf("You have logged in successfully %s %s !!!\n\n", first_name, last_name);
                    ok = 1;
                } else {
                    printf("Names not found in the CSV file. Try again:\n");
                }
            }
            break;
        }
        case '2': {
            printf("Enter your first name: ");
            scanf(" %[^\n]", first_name);
            printf("Enter your last name: ");
            scanf(" %[^\n]", last_name);

            strcpy(global_name, first_name);
            strcat(global_name, " ");
            strcat(global_name, last_name);

            writeToCSV(first_name, last_name, "AccountName.csv");

            break;
        }
        default: {
            printf("Wrong input, try again to input (1 or 2): ");
            break;
        }
        }
   }
}


//1
void  Borrow_Books(void) /// you should also decrease in Available Books the copies number by 1
{
    printf("Here are the available books:\n");
    readFromCSV("AvailableBooks.csv");
 printf("Enter the boook title:\n");
scanf(" %[^\n]", title);
printf("Enter the boook author:\n");
scanf(" %[^\n]", author);
printf("\n");
loan_book(title,author,first_name, last_name);

}


//2
void return_books(void)///This needs fixing a lot(delet returned book from loans or decrease the number, if alredy in books, increase nr)
{
    char title[200], author[200];
    int i;
    printf("Title of the book: \n");
    scanf(" %[^\n]", title);
    printf("Author of the book: \n");
    scanf(" %[^\n]", author);

    update_books(title,author,first_name,last_name);

}


//3
void view_loans(void)
{
    printf("Here are your loans:\n");
    prevew_of_loans(first_name,last_name);

}


//4
void search_for_books(void)
{
    printf("Search by:\n");
    printf("==============================================\n");
    printf("(1)Author name              (2)Book Title\n");
    printf("             (3)Both Author and Title\n");
    printf("==============================================\n");

    char n='0';
    printf("Enter your choice:\n");
    scanf("%s", &n);
    switch(n)
    {
    case '1':
        printf("Here are the available books:\n");
        readFromCSV("AvailableBooks.csv");
        printf("Input the author of the searched book:\n");
        scanf(" %[^\n]", author);
        search_books_by_author_csv("AvailableBooks.csv",author);

        break;
    case '2':
        printf("Here are the available books:\n");
        readFromCSV("AvailableBooks.csv");
        printf("Input the Title of the searched book:\n");
        scanf(" %[^\n]", title);
        search_book_by_title_csv("AvailableBooks.csv",title);
        break;
    case '3':
        printf("Here are the available books:\n");
        readFromCSV("AvailableBooks.csv");
        printf("Input the title of the searched book:\n");
        scanf(" %[^\n]", title);
        printf("Input the author of the searched book:\n");
        scanf(" %[^\n]", author);
        if(search_two_elements_in_same_line_csv("AvailableBooks.csv",title,author)==1)
            printf("The book you are searching for is available.\n");
        else
            printf("The book you are searching is not in the library.\n");
        break;

    default:
        printf("Wrong input!!");

    }


}

//5
void donate_books(void)
{
    printf("Input the title of the book you want to donate:\n");
    scanf(" %[^\n]", title);
    printf("Input the author of the book you want to donate:\n");
    scanf(" %[^\n]", author);
    int x;
    printf("Input the number of copyes donated:\n");
    scanf("%d",&x);
    writeToCSVBooks(title,author,x,"AvailableBooks.csv");
    printf("Thank you for the donation !!");
}

//6
void exit_lib(void)
{
    printf("Press enter one more time to exit.\nHave a nice day !!!\n");
    exit(0);
}


