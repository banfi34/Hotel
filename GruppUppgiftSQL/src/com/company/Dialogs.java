package com.company;

import java.util.Scanner;

public class Dialogs {
    private static Scanner sc;

    public static String userInput(){

        sc = new Scanner(System.in);
        String userInput;
        userInput = sc.nextLine();
        return userInput;
    }

    public static int promptInt(int min, int max){
        int newInput = -1;
        do{
            try{
                newInput = Integer.parseInt(userInput());
                if(max < newInput || newInput < min){
                    System.out.println("Invalid input");
                }

            }catch(Exception e){
                System.out.println("Invalid input");
            }
        }while(max < newInput || newInput < min);
        return newInput;
    }

    public static int promptInt(){
        int newInput = -1;
        do {
            try{
                newInput = Integer.parseInt(userInput());

            }catch(Exception e){
                System.out.println("Invalid input");
            }
        }while(newInput == -1);
        return newInput;

    }

}
