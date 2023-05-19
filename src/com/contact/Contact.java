package com.contact;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    String name;
    String company;
    String email;
    String phone;

    public Contact(String name, String company, String email, String phone) {
        this.name = name;
        this.company = company;
        this.email = email;
        this.phone = phone;
    }

    public Contact(){}

    Scanner input = new Scanner(System.in);

    public void inputData(){
        System.out.print("Enter Name: ");
        String iName = input.nextLine();
        try {
            name = checkName(iName);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.print("Enter Company: ");
        String iCompany = input.nextLine();
        try {
            company = checkCompany(iCompany);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.print("Enter Email: ");
        String iEmail = input.nextLine();
        try {
            email = checkEmail(iEmail);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.print("Enter Phone Number: ");
        String iPhone = input.nextLine();
        try {
            phone = checkPhone(iPhone);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void display(){
        System.out.printf("%-15s %-15s %-20s %-15s %n", name, company, email, phone);
    }

    public String checkName(String iName) throws Exception{
        if (iName.isEmpty()){
            throw new Exception("Name can not be Empty!");
        }else {
            return iName;
        }
    }

    public String checkCompany(String iCompany) throws Exception{
        if (iCompany.isEmpty()){
            throw new Exception("Company can not be Empty!");
        }else {
            return iCompany;
        }
    }

    private String checkEmail(String iEmail) throws Exception {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        if(iEmail.isEmpty()) {
            throw new Exception("Email can not be empty!");
        }

        Matcher matcher = pattern.matcher(iEmail);
        if(matcher.matches()) {
            return iEmail;
        } else {
            throw new Exception("Email must is correct format!");
        }
    }

    public String checkPhone(String iPhone) throws Exception{
        if (iPhone.isEmpty()){
            throw new Exception("Name can not be Empty!");
        }else {
            return iPhone;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
