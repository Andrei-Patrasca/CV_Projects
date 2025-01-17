#ifndef LIBRARYSISTEM_FILEIO_H
#define LIBRARYSISTEM_FILEIO_H
#pragma once
#include <stdio.h>
#include <string.h>

///These are the functionalities.c
void log_in_sign_up(void);
void return_books(void);
void  Borrow_Books(void);
void view_loans(void);
void search_for_books(void);
void exit_lib(void);
void donate_books(void);


///These are the used function from fileIO.c
void search_book_by_title_csv(const char *csv_file_path, const char *book_title);
void search_books_by_author_csv(const char *csv_file_path, const char *author_name);
void update_books(const char *title, const char *author, const char *first_name, const char *last_name);
void prevew_of_loans(const char *firstName, const char *lastName);
void loan_book(const char *title, const char *author, const char *firstName, const char *lastName);
int search_two_elements_in_same_line_csv(const char *csv_file_path, const char *element1, const char *element2);
int search_element_in_csv(const char *csv_file_path, const char *element);
void readFromCSV(const char* filePath);
void writeToCSV(const char* title, const char* author, const char* filename);

FILE* openFile(const char* filePath, const char* mode);

#endif
