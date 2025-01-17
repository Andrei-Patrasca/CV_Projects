#include "fileIO.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


void search_book_by_title_csv(const char *csv_file_path, const char *book_title) {
    FILE *file = fopen(csv_file_path, "r");
    if (file == NULL) {
        printf("Error opening file.\n");
        return;
    }

    char line[1024];
    int found = 0;
    printf("Here are the details for the book:\n");

    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\n")] = '\0'; // Remove newline character

        char *token = strtok(line, ",");
        char *title = NULL;
        char *author = NULL;

        while (token != NULL) {
            if (title == NULL) {
                title = strdup(token);
            } else if (author == NULL) {
                author = strdup(token);
            }
            token = strtok(NULL, ",");
        }

        if (title != NULL && strcmp(title, book_title) == 0) {
            printf("Title: %s\n", title);
            printf("Author: %s\n", author);
            found = 1;
        }

        free(title);
        free(author);
    }

    if (!found) {
        printf("No book matches the title: %s\n", book_title);
    }

    fclose(file);
}


void search_books_by_author_csv(const char *csv_file_path, const char *author_name) {
    FILE *file = fopen(csv_file_path, "r");
    if (file == NULL) {
        printf("Error opening file.\n");
        return;
    }

    char line[1024];
    int ok=0;
    printf("Here are the books from your author:\n");

    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\n")] = '\0';

        char *token = strtok(line, ",");
        char *book_title = NULL;
        char *book_author = NULL;

        while (token != NULL) {
            if (book_title == NULL) {
                book_title = strdup(token);
            } else if (book_author == NULL) {
                book_author = strdup(token);
            }
            token = strtok(NULL, ",");
        }

        if (book_author != NULL && strcmp(book_author, author_name) == 0) {
            printf("Book title: %s\n", book_title);
            ok=1;
        }


        free(book_title);
        free(book_author);
    }
    if(ok==0)
        {
            printf("There are not books written by this author.\n");
        }

    fclose(file);
}


void prevew_of_loans(const char *firstName, const char *lastName) {
    FILE *loansFile = fopen("Loans.csv", "r");
    if (loansFile == NULL) {
        printf("Error opening file.\n");
        return;
    }

    char line[1024];
    char tempFirstName[100], tempLastName[100], tempTitle[100], tempAuthor[100];
    int found = 0;

    while (fgets(line, sizeof(line), loansFile)) {
        sscanf(line, "%[^,],%[^,],%[^,],%[^\n]", tempFirstName, tempLastName, tempTitle, tempAuthor);
        if (strcmp(tempFirstName, firstName) == 0 && strcmp(tempLastName, lastName) == 0) {
            printf("Title: %s, Author: %s\n", tempTitle, tempAuthor);
            found = 1;
        }

    }

    if (!found) {
        printf("No books found for user %s %s.\n", firstName, lastName);
    }

    fclose(loansFile);
}

void update_books(const char *title, const char *author, const char *first_name, const char *last_name) {
    FILE *loans_file = fopen("Loans.csv", "r");
    FILE *available_file = fopen("AvailableBooks.csv", "a");

    if (loans_file == NULL || available_file == NULL) {
        printf("Error opening file.\n");
        return;
    }

    char line[1024];
    int found = 0;
    while (fgets(line, sizeof(line), loans_file)) {
        line[strcspn(line, "\n")] = '\0';

        char *token = strtok(line, ",");
        char *first_name_in_file = token;
        token = strtok(NULL, ",");
        char *last_name_in_file = token;
        token = strtok(NULL, ",");
        char *title_in_file = token;
        token = strtok(NULL, ",");
        char *author_in_file = token;

        if (strcmp(first_name, first_name_in_file) == 0 &&
            strcmp(last_name, last_name_in_file) == 0 &&
            strcmp(title, title_in_file) == 0 &&
            strcmp(author, author_in_file) == 0) {
            found = 1;
            fprintf(available_file, "%s,%s,%d\n", title, author,1);
            break;
        }
    }

    if (found) {
        printf("Book returned successfully.\n");
    } else {
        printf("Book not found.\n");
    }

    fclose(loans_file);
    fclose(available_file);
}



void loan_book(const char *title, const char *author, const char *firstName, const char *lastName) {
    FILE *inputFile = fopen("AvailableBooks.csv", "r");
    FILE *loansFile = fopen("Loans.csv", "a");
    if (inputFile == NULL || loansFile == NULL) {
        printf("Error opening file.\n");
        return;
    }

    char line[1024];
    char tempTitle[100], tempAuthor[100];
    int copies;
    int found = 0;

    while (fgets(line, sizeof(line), inputFile)) {
        sscanf(line, "%[^,],%[^,],%d", tempTitle, tempAuthor, &copies);
        if (strcmp(tempTitle, title) == 0 && strcmp(tempAuthor, author) == 0) {
            if (copies > 0) {
                fprintf(loansFile, "%s,%s,%s,%s\n", firstName, lastName, title, author);
                printf("Book successfully loaned.\n");
                found = 1;
            }
            break;
        }
    }

    if (!found) {
        printf("This book is not available to loan.\n");
    }

    fclose(inputFile);
    fclose(loansFile);
}



int search_two_elements_in_same_line_csv(const char *csv_file_path, const char *element1, const char *element2) {
    FILE *file = fopen(csv_file_path, "r");
    if (file == NULL) {
        printf("Error opening file.\n");
        return 0;
    }

    char line[1024];
    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\n")] = '\0';

        char *token = strtok(line, ",");
        int found1 = 0, found2 = 0;
        while (token != NULL) {
            if (strcmp(token, element1) == 0) {
                found1 = 1;
            } else if (strcmp(token, element2) == 0) {
                found2 = 1;
            }
            if (found1 && found2) {
                fclose(file);
                return 1;
            }
            token = strtok(NULL, ",");
        }
    }

    fclose(file);
    return 0;
}

void writeToCSVBooks(const char *title, const char *author, int x, const char *file_path) {
    FILE* file = fopen(file_path, "a");
    if (file == NULL) {
        printf("Error: Unable to open file %s for writing.\n", file_path);
        return;
    }

    fprintf(file, "%s,%s,%d\n", title, author, x);

    fclose(file);
}

void writeToCSV(const char* title, const char* author, const char* filename) {
    FILE* file = openFile(filename, "a");
    if (file == NULL) {
        printf("Error opening file %s\n", filename);
    } else {
        fprintf(file, "%s,%s\n", title, author);
        fclose(file);
        printf("You have succesfully registered!");
    }
}


void readFromCSV(const char* filePath) {
    FILE* file = openFile(filePath, "r");
    if (file == NULL)
        return;

    char headers[100];
    char data[100];

    char* token;
    char* next_token;

    fgets(headers, sizeof(headers), file);
    int i=1;
    while (fgets(data, sizeof(data), file) != NULL) {
        if (strlen(data) <= 1)
            break;

        token = strtok_s(data, ",", &next_token);

        while (token != NULL) {
            printf("%d: %s\n", i, token);
            token = strtok_s(NULL, ",", &next_token);

        }
    i++;
    }

    fclose(file);
}


FILE* openFile(const char* filePath, const char* mode)
{
    if (filePath == NULL || strlen(filePath) == 0 || mode == NULL || strlen(mode) == 0)
        return NULL;

    FILE* file = NULL;

    fopen_s(&file, filePath, mode);
    return file;

}
