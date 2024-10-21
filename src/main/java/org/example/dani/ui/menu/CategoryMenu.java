package org.example.dani.ui.menu;

import org.example.dani.dao.impl.CategoryImp;
import org.example.dani.dao.interfaces.IDao;
import org.example.dani.entity.Category;
import org.example.dani.ui.UIAdmin;

import java.sql.SQLException;
import java.util.Scanner;

public class CategoryMenu {
    public static void showCategoryActionsMenu() throws SQLException {
        int response;
        do {
            System.out.println("\n\n--> Category Actions <--");
            System.out.println("Select an option:");
            System.out.println("1. List all categories");
            System.out.println("2. Create a new category");
            System.out.println("3. Update an category");
            System.out.println("4. Delete an category");
            System.out.println("0. Go back");


            Scanner sc = new Scanner(System.in);
            response = Integer.parseInt(sc.nextLine());

            switch (response) {
                case 1:
                    response = 0;
                    listAllCategories();
                    break;
                case 2:
                    response = 0;
                    createNewCategory();
                    break;
                case 3:
                    response = 0;
                    categoryUpdate();
                    break;
                case 4:
                    response = 0;
                    deleteCategory();
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

    private static void listAllCategories() throws SQLException {
        System.out.println("\n");
        System.out.println("-=-=- CATEGORY LIST -=-=-");
        IDao<Category> categoriIDao = new CategoryImp();
        categoriIDao.findAll().forEach(System.out::println);

        showCategoryActionsMenu();
    }

    private static void createNewCategory() {
        try {
            System.out.println("\n-=-=- CREATE A NEW CATEGORY -=-=-");

            Category newCategory = new Category();

            Scanner sc = new Scanner(System.in);

            System.out.println("- Enter the director name:");
            newCategory.setName(String.valueOf(sc.nextLine()));

            System.out.println("- Enter the category clasification:");
            newCategory.setClassification(String.valueOf(sc.nextLine()));

            IDao<Category> categoryIDao = new CategoryImp();
            categoryIDao.save(newCategory);

            System.out.println("Category successfully created");
            showCategoryActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void categoryUpdate() {
        try {
            System.out.println("\n-=-=- UPDATE A CATEGORY -=-=-");

            Scanner sc = new Scanner(System.in);

            System.out.println("- Enter the ID of the category to update:");
            int categoryId = Integer.parseInt(sc.nextLine());

            IDao<Category> categoryIDao = new CategoryImp();
            Category existingCategory = categoryIDao.getById(categoryId);

            if (existingCategory == null) {
                System.out.println("Category not found");
                showCategoryActionsMenu();
                return;
            }

            System.out.println("- Current name: " + existingCategory.getName());
            System.out.println("- Enter a new name (or press Enter to keep the current):");
            String newName = sc.nextLine();
            if (!newName.isEmpty()) {
                existingCategory.setName(newName);
            }

            System.out.println("- Current classification: " + existingCategory.getClassification());
            System.out.println("- Enter new classification (or press Enter to keep the current):");
            String classification = sc.nextLine();
            if (!classification.isEmpty()) {
                existingCategory.setClassification(classification);
            }

            categoryIDao.save(existingCategory);

            System.out.println("Category successfully updated");
            showCategoryActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteCategory() {
        try {
            System.out.println("\n");
            System.out.println("-=-=- DELETE A CATEGORY -=-=-");
            System.out.println("- Enter category ID to delete: ");
            Scanner sc = new Scanner(System.in);
            int categoryId = Integer.parseInt(sc.nextLine());

            IDao<Category> categoryIDao = new CategoryImp();
            categoryIDao.delete(categoryId);

            System.out.println("Category successfully deleted");
            showCategoryActionsMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
