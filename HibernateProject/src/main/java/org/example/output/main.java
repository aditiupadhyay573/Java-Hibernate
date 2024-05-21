package org.example.output;


import java.util.List;
import java.util.Scanner;

import org.example.dao.BookDao;
import org.example.entity.Books;

public class main {

    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Book Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook(bookDao, scanner);
                    break;
                case 2:
                    viewAllBooks(bookDao);
                    break;
                case 3:
                    updateBook(bookDao, scanner);
                    break;
                case 4:
                    deleteBook(bookDao, scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook(BookDao bookDao, Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        bookDao.saveBook(title, author, price);
    }

    private static void viewAllBooks(BookDao bookDao) {
        List<Books> books = bookDao.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Books book : books) {
                System.out.println("ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Price: " + book.getPrice());
                System.out.println();
            }
        }
    }

    private static void updateBook(BookDao bookDao, Scanner scanner) {
        System.out.print("Enter book ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new author: ");
        String author = scanner.nextLine();
        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Books book = new Books();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(price);

        int rowsAffected = bookDao.updateBook(book);
        if (rowsAffected > 0) {
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void deleteBook(BookDao bookDao, Scanner scanner) {
        System.out.print("Enter book ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        int rowsAffected = bookDao.deleteBook(id);
        if (rowsAffected > 0) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }
}

