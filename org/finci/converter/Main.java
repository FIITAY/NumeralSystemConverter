package org.finci.converter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //getting the information from the user
        int srcRadix,base;
        String number;
        if(sc.hasNextInt())
            srcRadix = sc.nextInt();
        else
            srcRadix=-1;
        if(sc.hasNext())
            number = sc.next();
        else
            number="err";
        if(sc.hasNextInt())
            base = sc.nextInt();
        else
            base = -1;
        //check that there wasnt empty var
        if(srcRadix != -1 && !number.equals("err") && base != -1){
            //converting
            String output = Converter.convert(srcRadix, base, number);
            //printing the number
            System.out.println(output);
        }else{
            //error
            System.out.println("error: missing argumants");
        }
    }




}
