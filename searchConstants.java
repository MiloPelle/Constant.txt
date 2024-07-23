import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class searchConstants {

    
    

    private static String createList(String nameString) throws FileNotFoundException{

    String value = null;

    List<String> constantFile = new ArrayList<>();
    File constTxtFile = new File("C:\\Users\\milop\\OneDrive\\Documents\\Personal Github Repositories\\Constant.txt\\constants.txt");
    Scanner txtScanner = new Scanner(constTxtFile);

    while(txtScanner.hasNextLine()){

        constantFile.add(txtScanner.nextLine());
        
    }

    value = constantFile.get(constantFile.indexOf(nameString)+1);
    txtScanner.close();
    return value;

    }
    
    public static Double findDouble(String name) throws IOException{

        return Double.parseDouble(createList(name));

    }
}
