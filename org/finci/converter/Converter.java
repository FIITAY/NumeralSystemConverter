package org.finci.converter;

public class Converter {
    public static String convert(int iBase, int fBase, String number){
        //checking the input
        if(iBase < 1 || iBase > Character.MAX_RADIX
        || fBase < 1 || fBase > Character.MAX_RADIX){
            return "error: illegal initial or target base.";
        }
        //if there is friction seperating to two strings
        String friction = null;
        if(number.contains(".")){
            friction = number.substring(number.indexOf('.')+1);//do not include the point
            number = number.substring(0,number.indexOf('.'));
        }
        //converting the number
        long num;
        String frictionPartFinal;
        try {
            //making the part of the number before the dot
             num = parseNum(iBase, number);
            //making the friction part
            frictionPartFinal = setFrac(friction, iBase, fBase);
        }catch(Exception exp){
            return "error: illegal number format";
        }
        //returning the converted number
        if(fBase == 1){
            String output = "";
            for(int i =0;i<num;i++){
                output += "1";
            }
            return  output;
        }else{
            return print(Long.toString(num,fBase), frictionPartFinal);
        }

    }

    private static long parseNum(int srcRadix, String number){
        long num =0l;
        if(srcRadix == 1){
            for(int i=0;i<number.length();i++){
                if(number.charAt(i)=='1'){
                    num++;
                }
            }
        }else{
            num = Long.parseLong(number, srcRadix);
        }
        return num;
    }

    private static String setFrac(String friction, int srcRadix, int base){
        String fin ="";
        if(friction != null) {
            //converting to int
            double temp = 0;
            for(int i = 0;i<friction.length(); i++){
                //adding the friction to temp as required
                temp += Long.parseLong(""+friction.charAt(i),srcRadix)/Math.pow(srcRadix,i+1);
            }
            //converting to the new radix
            //multiplying as in the formula
            int i = 1; //save the index
            while(temp<1 && i<=5){
                //taking the part that is bigger than 0 and adding it to the end of the friction
                fin += Long.toString((int)(temp * base),base);
                //removing the part before the . from temp
                temp = temp*base - ((int)(temp * base));
                i++;
            }
        }
        return fin;
    }

    private static String print(String num, String frac){
        if(!frac.isEmpty()){
            return (num+"."+frac);
        }else{
            return (num);
        }
    }

}
