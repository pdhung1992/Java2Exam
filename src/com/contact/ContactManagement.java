package com.contact;

import com.util.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ContactManagement {
    Scanner input = new Scanner(System.in);

    Contact contact = null;
    public void addContact(){
        System.out.println("--- Add new contact ---");
        try {
            Contact iContact = new Contact();
            iContact.inputData();

            try {
                contact = checkNull(iContact);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            if (contact != null){
                Connection connection = DBUtil.getConnection();
                CallableStatement callableStatement = connection.prepareCall("{call addContact(?, ?, ?, ?)}");
                callableStatement.setString(1, contact.getName());
                callableStatement.setString(2, contact.getCompany());
                callableStatement.setString(3, contact.getEmail());
                callableStatement.setString(4, contact.getPhone());

                if(callableStatement.executeUpdate() > 0){
                    System.out.println("Contact added!");
                }

                callableStatement.close();
                connection.close();
            }

        }catch (Exception ex){

        }
    }

    public void findContactByName(){
        System.out.println("--- Find Contact By Name ---");
        try {
            System.out.print("Enter name to find: ");
            String fName = input.nextLine();
            Connection connection = DBUtil.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call findName(?)}");
            callableStatement.setString(1, fName);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()){
                Contact contact = new Contact(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                System.out.println("Contact found: ");
                System.out.printf("%-15s %-15s %-20s %-15s %n", "Name", "Company", "Email", "Phone number");
                contact.display();
            }else {
                System.out.println("Contact not found!");
            }
            callableStatement.close();
            connection.close();
        }catch (Exception ex){

        }
    }

    public void displayContact(){
        System.out.println("--- Display Contacts ---");
        System.out.printf("%-15s %-15s %-20s %-15s %n", "Name", "Company", "Email", "Phone number");
        try {
            Connection connection = DBUtil.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call getAllContact}");
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()){
                Contact contact = new Contact(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                contact.display();
            }
            callableStatement.close();
            connection.close();
        }catch (Exception ex){

        }
    }

    public Contact checkNull(Contact contact) throws Exception{
        if (contact.getName() == null){
            throw new Exception("Name is empty, can not add contact!");
        }else {
            return contact;
        }
    }
}
