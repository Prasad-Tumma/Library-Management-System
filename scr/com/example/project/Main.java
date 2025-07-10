package com.example.project;
import java.util.*;

public class Main {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        LibraryDataManager dm=new LibraryDataManager();
        while(true){
            System.out.println("BOOK MANAGEMENT MENU");
            System.out.println("Please select option from 1-7");
            System.out.println("1. Register a new book");
            System.out.println("2. View all book");
            System.out.println("3. Find book by ID");
            System.out.println("4. Issue a book");
            System.out.println("5. Return a book");
            System.out.println("6. Delete a book entry");
            System.out.println("7. Exit application");
            int choice=sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1: System.out.println("Enter book title:");
                        String title=sc.nextLine();
                        System.out.println("Enter author name:");
                        String author=sc.nextLine();
                        new LibraryTaskHandler(()->{
                            dm.addBook(new Books(1, title, author, true));
                        }).start();
                        System.out.println("Book has been registered successfully");
                        break;
                case 2: new LibraryTaskHandler(()->{
                            dm.getAllBooks();
                        }).start();
                        break;
                case 3: System.out.println("Enter book ID to find:");
                        int find_id=sc.nextInt();
                        dm.getBookById(find_id);
                        break;
                case 4: System.out.println("Enter book ID to issue:");
                        int issue_id=sc.nextInt();
                        new LibraryTaskHandler(() -> {
                            boolean issue_update=dm.updateAvailability(issue_id,false);
                            if(issue_update)
                                System.out.println("Book has been issued");
                            else
                                System.out.println("Book has not been issued");
                        }).start();
                        break;
                case 5: System.out.println("Enter book ID to return:");
                        int return_id=sc.nextInt();
                        new LibraryTaskHandler(()->{
                            boolean return_update=dm.updateAvailability(return_id,true);
                            if(return_update)
                                System.out.println("Book has been returned");
                            else
                                System.out.println("Book has not been returned");
                        }).start();
                        break;
                case 6: System.out.println("Enter book ID to delete:");
                        int delete_id=sc.nextInt();
                        new LibraryTaskHandler(()->{
                            dm.deleteBook(delete_id);
                        }).start();
                        System.out.println("Book has been deleted successfully");
                        break;
                case 7: System.out.println("Exited");
                        System.exit(0);
                default: System.out.println("Invalid choice. Enter number from 1-7");
            }
        }
    }
}