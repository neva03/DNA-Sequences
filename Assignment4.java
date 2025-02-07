package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment4 {

    public static void main(String[] args) throws FileNotFoundException{
        String fileName;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file's pathname: ");
        fileName = scanner.nextLine();
        
        Scanner fileScanner = new Scanner(new File(fileName));
       
        DNA dna = new DNA();
        
        while(fileScanner.hasNextLine()){ //reads the file
            String row = fileScanner.nextLine();
            String[] parts = row.split(" ");
            
            String method = parts[0];

            if(method.equals("insert")){ //if the direction is insert
                String sequence = parts[1];
                dna.insert(sequence);
            }else if(method.equals("search")){//if the direction is search
                String sequenceDescriptor = parts[1];
                dna.search(sequenceDescriptor);
            }else if(method.equals("display")){//if the direction is display
                dna.display();
            }else{
                System.out.println("no directive");
            }          
        }
        
    }
    
}
