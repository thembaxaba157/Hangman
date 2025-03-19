package com.example;

import java.util.Scanner;


public class InputHandler {
    
    private final Scanner scanner;

    public InputHandler(){
        this.scanner = new Scanner(System.in);
    }

    public InputHandler(Scanner scanner){
        this.scanner = scanner;
    }

    public int enterInt(int optionSize, boolean isBackAvailable, boolean isMenuAvailable){
        int num = 0 ;
        while(true){
               
            try{
            
                num = this.scanner.nextInt();
                if((num>=1 && num<=optionSize) || (isBackAvailable && num==0) || (isMenuAvailable && num==99)){
                    break;
                }
                else{
                   
                    System.out.print("Select number from 1-"+Integer.toString(optionSize));
                    if(isBackAvailable){
                        System.out.print(" or 0 to go back");
                        }
                        if(isMenuAvailable){
                            System.out.print(" or 99 to go to menu");

            }
        }
    }
    catch(Exception e){
        System.out.print("Invalid input. Please enter a number.");
        continue;
}
        
        }

        return num;

}


}
