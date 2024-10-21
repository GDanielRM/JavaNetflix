package org.example.dani.ui;

import org.example.dani.ui.menu.MovieMenu;
import org.example.dani.ui.menu.CategoryMenu;
import org.example.dani.ui.menu.DirectorMenu;
import org.example.dani.ui.menu.UserMenu;

import java.sql.SQLException;
import java.util.Scanner;

public class UIAdmin {

    public static void showAdminMenu() throws SQLException {
        int response;
        do {
            System.out.println("\n\n--> Admin Menu <--");
            System.out.println("Welcome " + UIMenu.userLogged.getName());
            System.out.println("What do you want to do?");
            System.out.println("1. Movies");
            System.out.println("2. Categories");
            System.out.println("3. Directors");
            System.out.println("4. Users");
            System.out.println("0. Logout");


            Scanner sc = new Scanner(System.in);
            response = Integer.parseInt(sc.nextLine());

            switch (response) {
                case 1:
                    response = 0;
                    MovieMenu.showMovieActionsMenu();
                    break;
                case 2:
                    response = 0;
                    CategoryMenu.showCategoryActionsMenu();
                    break;
                case 3:
                    response = 0;
                    DirectorMenu.showDirectorActionsMenu();
                    break;
                case 4:
                    response = 0;
                    UserMenu.showUserActionsMenu();
                    break;
                case 0:
                    System.out.println("Good bye " + UIMenu.userLogged.getName());
                    UIMenu.showMenu();
                    break;
                default:
                    System.out.println("Please select a correct answer");
                    break;
            }
        } while (response != 0);
    }
}
