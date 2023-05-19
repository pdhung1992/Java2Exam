package com.contact;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        ContactManagement contactManagement = new ContactManagement();
        System.out.println("=== Address book program ===");
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("1. Add new contact");
            System.out.println("2. Find contact by name");
            System.out.println("3. Display contacts");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            String sChoice = input.nextLine();

            int choice = 0;

            try {
                choice = checkChoice(sChoice);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

            switch (choice){
                case 1:
                    contactManagement.addContact();
                    break;
                case 2:
                    contactManagement.findContactByName();
                    break;
                case 3:
                    contactManagement.displayContact();
                    break;
                case 4:
                    System.out.println("Program ended!");
                    return;
            }
        }while (true);
    }

    public static int checkChoice(String sChoice) throws Exception{
        if(sChoice.equals("")){
            throw new Exception("Choose can not be empty! ");
        }
        int choice;
        try{
            choice = Integer.parseInt(sChoice);
        }
        catch (Exception e){
            throw new Exception("Choice must be a number!");
        }
        if (choice > 0 && choice < 5){
            return choice;
        }else {
            throw new Exception("Choice must be a number form 1 to 4!");
        }
    }
}
