import java.io.*;
import java.util.*;
import java.lang.*;

public class InjectMutants{
	
	public static void main(String[] args) throws Exception{
		
		File file = new File("Fault List.txt");  //open the fault list
	    BufferedReader reader = new BufferedReader(new FileReader(file));
        int totalNumOfOperators = 0;

	    String info[];                                                             //holds mutant information from Fault List.txt
	    int index = 0;
	    String line = reader.readLine();
	    line = reader.readLine();                                                  //start reading from second line because first line is just headings

	    while (line.length()>0 && line != null){
            totalNumOfOperators++;                                                 //generate 3 mutants for each operator
            line = reader.readLine();
	    }
        reader.close();

        reader = new BufferedReader(new FileReader(file));                         //open new reader to read mutant info
        int mutant_line[] = new int[totalNumOfOperators];                          //stores line number where mutant is to be injected
        char mutant1[] = new char[totalNumOfOperators];
        char mutant2[] = new char[totalNumOfOperators];
        char mutant3[] = new char[totalNumOfOperators];
        int mutant_position[] = new int[totalNumOfOperators];                      //position in line where mutant is to be inserted
        
        line = reader.readLine();
        line = reader.readLine();                                                  //start from second line of Fault List.txt

        while (line.length()>0 && line != null){
            info = line.split("\\s+");                                             //split line
          
            mutant_line[index] = Integer.parseInt(info[1]);                        //line in SUT
            mutant1[index] = info[4].charAt(0);
            mutant2[index] = info[5].charAt(0);
            mutant3[index] = info[6].charAt(0);
            mutant_position[index] = Integer.parseInt(info[2]);                    //position of the mutant in line of code
            
            index++; line = reader.readLine();
        }
        reader.close();
        
        
        file = new File("Arithmetics.java");                                            //open original software under test to generate mutated copies
        for(int i = 0; i < mutant_line.length; i++){
    
            reader = new BufferedReader(new FileReader(file));                      //reads from SUT
            
            String name1 = "mutant" + (i+1) + "1.java";                     //mutated software name
            String name2 = "mutant" + (i+1) + "2.java";
            String name3 = "mutant" + (i+1) + "3.java";
            FileWriter writer1 = new FileWriter(name1);
            FileWriter writer2 = new FileWriter(name2);                             //opening 3 writers to create 3 mutated copies for each operator
            FileWriter writer3 = new FileWriter(name3);
            
            int lineNumber = 0;
            String code = reader.readLine();
            
            while(code != null){                                                     //while not end of program
                lineNumber++;
                if(lineNumber == 1){
                    writer1.write("public class mutant" + (i+1) + "1{");
                    writer2.write("public class mutant" + (i+1) + "2{");
                    writer3.write("public class mutant" + (i+1) + "3{");
                }else{
                    if(lineNumber == mutant_line[i]){                                    //mutant should be injected in this line
                        for(int j = 0 ; j < code.length(); j++){
                            if(j == mutant_position[i]){                                 //mutant will be injected at this position in line
                                writer1.write(mutant1[i]);
                                writer2.write(mutant2[i]);
                                writer3.write(mutant3[i]);
                            }
                            else {
                                writer1.write(code.charAt(j));
                                writer2.write(code.charAt(j));
                                writer3.write(code.charAt(j));
                            }
                        }
                    }else {
                        writer1.write(code);
                        writer2.write(code);
                        writer3.write(code);
                    }
                }
               
                writer1.write("\n"); writer2.write("\n"); writer3.write("\n");
                code = reader.readLine();
            }
            reader.close();
            writer1.close(); writer2.close(); writer3.close();
        }
	}
}
