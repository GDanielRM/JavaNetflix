package org.example.dani.ui.menu;

import org.example.dani.dao.impl.UserImp;
import org.example.dani.dao.interfaces.IDao;
import org.example.dani.entity.User;
import org.example.dani.ui.UIAdmin;

import java.sql.SQLException;
import java.util.Scanner;

public class UserMenu {
    public static void showUserActionsMenu() throws SQLException {
        int response;
        do {
            System.out.println("\n\n");
            System.out.println("--> User Actions <--");
            System.out.println("Select an option:");
            System.out.println("1. List all Users");
            System.out.println("2. Create a new user");
            System.out.println("3. Update an user");
            System.out.println("4. Delete an user");
            System.out.println("0. Go back");


            Scanner sc = new Scanner(System.in);
            response = Integer.parseInt(sc.nextLine());

            switch (response) {
                case 1:
                    response = 0;
                    listAllUsers();
                    break;
                case 2:
                    response = 0;
                    createNewUser();
                    break;
                case 3:
                    response = 0;
                    userUpdate();
                    break;
                case 4:
                    response = 0;
                    deleteUser();
                    break;
                case 0:
                    UIAdmin.showAdminMenu();
                    break;
                default:
                    System.out.println("Please select a correct answer");
                    break;
            }
        } while (response != 0);
    }

    private static void listAllUsers() throws SQLException {
        System.out.println("\n");
        System.out.println("-=-=- USER LIST -=-=-");
        IDao<User> userIDao = new UserImp();
        userIDao.findAll().forEach(System.out::println);

        showUserActionsMenu();
    }

    private static void createNewUser() {
        try {
            System.out.println("\n");
            System.out.println("-=-=- CREATE A NEW USER -=-=-");

            User newUser = new User();

            Scanner sc = new Scanner(System.in);

            System.out.println("- Enter the user name:");
            newUser.setName(String.valueOf(sc.nextLine()));

            System.out.println("- Enter the user lastname:");
            newUser.setLastname(String.valueOf(sc.nextLine()));

            System.out.println("- Enter the user email:");
            newUser.setEmail(String.valueOf(sc.nextLine()));

            System.out.println("- Enter the user password:");
            newUser.setPassword(String.valueOf(sc.nextLine()));

            System.out.println("- Enter the user type [1. Admin/2. User]:");
            newUser.setUserType(Integer.valueOf(sc.nextLine()));

            IDao<User> userIDao = new UserImp();
            userIDao.save(newUser);

            System.out.println("User successfully created");
            showUserActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void userUpdate() {
        try {
            System.out.println("\n");
            System.out.println("-=-=- UPDATE A USER -=-=-");

            Scanner sc = new Scanner(System.in);

            System.out.println("- Enter the ID of the user to update:");
            int userId = Integer.parseInt(sc.nextLine());

            IDao<User> userIDao = new UserImp();
            User existingUser = userIDao.getById(userId);

            if (existingUser == null) {
                System.out.println("User not found");
                showUserActionsMenu();
                return;
            }

            System.out.println("- Current name: " + existingUser.getName());
            System.out.println("- Enter a new name (or press Enter to keep the current):");
            String newName = sc.nextLine();
            if (!newName.isEmpty()) {
                existingUser.setName(newName);
            }

            System.out.println("- Current Lastname: " + existingUser.getLastname());
            System.out.println("- Enter new lastname (or press Enter to keep the current):");
            String newLastname = sc.nextLine();
            if (!newLastname.isEmpty()) {
                existingUser.setLastname(newLastname);
            }

            System.out.println("- Current Email: " + existingUser.getEmail());
            System.out.println("- Enter new email (or press Enter to keep the current):");
            String newEmail = sc.nextLine();
            if (!newEmail.isEmpty()) {
                existingUser.setEmail(newEmail);
            }

            System.out.println("- Current Password: " + existingUser.getPassword());
            System.out.println("- Enter new password (or press Enter to keep the current):");
            String newPassword = sc.nextLine();
            if (!newPassword.isEmpty()) {
                existingUser.setPassword(newPassword);
            }

            System.out.println("- Current User Type: " + existingUser.getUserType());
            System.out.println("- Enter new user type [1. Admin/2. User] (or press Enter to keep the current):");
            String newUserType = sc.nextLine();
            if (!newUserType.isEmpty()) {
                existingUser.setUserType(Integer.parseInt(newUserType));
            }

            userIDao.save(existingUser);

            System.out.println("User successfully updated");
            showUserActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteUser() {
        try {
            System.out.println("\n");
            System.out.println("-=-=- DELETE A USER -=-=-");
            System.out.println("- Enter user ID to delete: ");
            Scanner sc = new Scanner(System.in);
            int userId = Integer.parseInt(sc.nextLine());

            IDao<User> userIDao = new UserImp();
            userIDao.delete(userId);

            System.out.println("User successfully deleted");
            showUserActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
