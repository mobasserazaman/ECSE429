import java.io.*;
import java.util.*;

public class GenerateMutants{
    public static void main(String[] args)throws Exception{
        
        PrintWriter write = new PrintWriter("Fault List.txt"); // library of mutants
        Scanner scan = new Scanner(new FileInputStream(args[0]));  // program under test is passed as argument
        
        String lineOfCode; //line of code under test
        int lineNumber=0;
        
        int additionCount=0; int subtractionCount=0; int multCount=0; int divCount=0;
        char operator;
        
        // write.print("Line under test  " + "  Original operator  " + "  Mutant inserted  " + "  Position in Line \n");
        write.print("Line under test " + " Character position " + " Original operator " + " Mutant 1 " + " Mutant 2 " + "  Mutant 3 \n");
        
        while(scan.hasNextLine()){ // while not end of program
            
            lineNumber++;
            lineOfCode = scan.nextLine(); // get next line
            
            for(int i = 0; i < lineOfCode.length(); i++){ // looking for arithmetic operators in lineOfCode
                
                operator = lineOfCode.charAt(i);
                
                if(operator == '+'){
                    subtractionCount++;
                    multCount++;
                    divCount++;
                    write.print("\t" + lineNumber + "\t\t" + i + "\t\t    " + operator + "\t\t   " + "-\t     " + "*\t\t" + "/\t\n");
                }
                else if(operator =='-'){
                    additionCount++;
                    multCount++;
                    divCount++;
                    write.print("\t" + lineNumber + "\t\t" + i + "\t\t    " + operator + "\t\t   " + "+\t     " + "*\t\t" + "/\t\n");
                }
                else if(operator =='*'){
                    additionCount++;
                    subtractionCount++;
                    divCount++;
                    write.print("\t" + lineNumber + "\t\t" + i + "\t\t    " + operator + "\t\t   " + "+\t     " + "-\t\t" + "/\t\n");
                }
                else if(operator =='/'){
                    additionCount++;
                    subtractionCount++;
                    multCount++;
                    write.print("\t" + lineNumber + "\t\t" + i + "\t\t    " + operator + "\t\t   " + "+\t     " + "-\t\t" + "*\t\n");
                }
            }
            
        }
        
        scan.close();
        write.print("\nMutants Generated\nPlus : " + additionCount+", Minus : "+subtractionCount+", Multiplication : "+multCount+", Division :  "+divCount+"\n");
        write.close();
        
    }
    
}
