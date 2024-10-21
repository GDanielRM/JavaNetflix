package org.example.dani.ui.menu;

import org.example.dani.dao.impl.DirectorImp;
import org.example.dani.dao.interfaces.IDao;
import org.example.dani.entity.Director;
import org.example.dani.ui.UIAdmin;

import java.sql.SQLException;
import java.util.Scanner;

public class DirectorMenu {
    public static void showDirectorActionsMenu() throws SQLException {
        int response;
        do {
            System.out.println("\n\n");
            System.out.println("--> Director Actions <--");
            System.out.println("Select an option:");
            System.out.println("1. List all directors");
            System.out.println("2. Create a new director");
            System.out.println("3. Update an director");
            System.out.println("4. Delete an director");
            System.out.println("0. Go back");


            Scanner sc = new Scanner(System.in);
            response = Integer.parseInt(sc.nextLine());

            switch (response) {
                case 1:
                    response = 0;
                    listAllDirectors();
                    break;
                case 2:
                    response = 0;
                    createNewDirector();
                    break;
                case 3:
                    response = 0;
                    directorUpdate();
                    break;
                case 4:
                    response = 0;
                    deleteDirector();
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

    private static void listAllDirectors() throws SQLException {
        System.out.println("\n");
        System.out.println("-=-=- DIRECTOR LIST -=-=-");
        IDao<Director> directorIDao = new DirectorImp();
        directorIDao.findAll().forEach(System.out::println);

        showDirectorActionsMenu();
    }

    private static void createNewDirector() {
        try {
            System.out.println("\n");
            System.out.println("-=-=- CREATE A NEW DIRECTOR -=-=-");

            Director newDiretor = new Director();

            Scanner sc = new Scanner(System.in);

            System.out.println("- Enter the director name:");
            newDiretor.setName(String.valueOf(sc.nextLine()));

            System.out.println("- Enter the director country origin:");
            newDiretor.setCountryOrigin(String.valueOf(sc.nextLine()));

            System.out.println("- Has the director won an Oscar? [1-Yes|2-No]:");
            newDiretor.setOscar(Integer.valueOf(sc.nextLine()));

            IDao<Director> directorIDao = new DirectorImp();
            directorIDao.save(newDiretor);

            System.out.println("Director successfully created");
            showDirectorActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void directorUpdate() {
        try {
            System.out.println("\n");
            System.out.println("-=-=- UPDATE A DIRECTOR -=-=-");

            Scanner sc = new Scanner(System.in);

            System.out.println("- Enter the ID of the director to update:");
            int directorId = Integer.parseInt(sc.nextLine());

            IDao<Director> directorIDao = new DirectorImp();
            Director existingDirector = directorIDao.getById(directorId);

            if (existingDirector == null) {
                System.out.println("Director not found");
                showDirectorActionsMenu();
                return;
            }

            System.out.println("- Current name: " + existingDirector.getName());
            System.out.println("- Enter a new name (or press Enter to keep the current):");
            String newName = sc.nextLine();
            if (!newName.isEmpty()) {
                existingDirector.setName(newName);
            }

            System.out.println("- Current country origin: " + existingDirector.getCountryOrigin());
            System.out.println("- Enter new country origin (or press Enter to keep the current):");
            String countryOrigin = sc.nextLine();
            if (!countryOrigin.isEmpty()) {
                existingDirector.setCountryOrigin(countryOrigin);
            }

            System.out.println("- Currently this director has an Oscar: " + existingDirector.getOscar());
            System.out.println("- Enter new value (or press Enter to keep the current):");
            String newOscar = sc.nextLine();
            if (!newOscar.isEmpty()) {
                existingDirector.setOscar(Integer.parseInt(newOscar));
            }

            directorIDao.save(existingDirector);

            System.out.println("Director successfully updated");
            showDirectorActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteDirector() {
        try {
            System.out.println("\n");
            System.out.println("-=-=- DELETE A DIRECTOR -=-=-");
            System.out.println("- Enter director ID to delete: ");
            Scanner sc = new Scanner(System.in);
            int directorId = Integer.parseInt(sc.nextLine());

            IDao<Director> directorIDao = new DirectorImp();
            directorIDao.delete(directorId);

            System.out.println("Director successfully deleted");
            showDirectorActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
