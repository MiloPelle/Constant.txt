import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

public class constants{

    private static int tempLineSkip;
    private static int i;
    private class group{
        int lineSkip;
        int arrLength;
        static constant a;
        HashMap<String,constant> assignedConstants;
        

        private static class constant{
            String datatype;
            String name;
            String value;
            private constant(String datatype, String name, String value){
                this.datatype = datatype;
                this.name = name;
                this.value = value;
            }
        }
    }


    private static Scanner constScanner;

    private static String stringLoop;
    private static Path project;
    Stream<String> streamLines;
    int lines;

    

    HashMap<group, String[]> constantTree = new HashMap<group, String[]>();
    HashMap<String, group> objectName = new HashMap<String, group>();


    constants() throws FileNotFoundException, IOException{
        project = FileSystems.getDefault().getPath(null, "constants.txt");
        streamLines = Files.lines(project);
        new generateConstantTree().start();
        
    }

    private class generateConstantTree{
        public void start(){
            findGroups();
            setHashMap();
        }
        private void findGroups(){
            while(i<lines){
                stringLoop = streamLines.skip(i-1).findFirst().get();
                String subStringLoop = stringLoop.substring(1, stringLoop.length()-1);
                if(stringLoop.endsWith(":")){
                    objectName.put(subStringLoop, new group());
                    tempLineSkip = 0;
                }
                i++;
            }
            i=0;
            tempLineSkip=0;
        }
        private void setHashMap(){
            while(i<lines){
                stringLoop = streamLines.skip(i-1).findFirst().get();
                String subStringLoop = stringLoop.substring(0, stringLoop.length()-1);
                String subStringLoop2 = stringLoop;
                if(stringLoop.endsWith(":")){
                    stringLoop=null;
                    while(!stringLoop.endsWith(":")){
                        stringLoop = streamLines.skip(i-1).findFirst().get();
                        subStringLoop2 = stringLoop.substring(0, stringLoop.length()-1);

                        switch(subStringLoop.substring(0, stringLoop.indexOf(" ")-1)){
                        case("Double"):
                        String nameConstant = stringLoop.substring(stringLoop.indexOf(" ")+1, stringLoop.indexOf("=")-1);
                        String value = stringLoop.substring(stringLoop.indexOf("=")+1);
                        objectName.get(subStringLoop).assignedConstants.put(nameConstant,new group.constant("Double", nameConstant, value));
                        case("int"):
                        String nameConstant2 = stringLoop.substring(stringLoop.indexOf(" ")+1, stringLoop.indexOf("=")-1);
                        String value2 = stringLoop.substring(stringLoop.indexOf("=")+1);
                        objectName.get(subStringLoop).assignedConstants.put(nameConstant2,new group.constant("Double", nameConstant2, value2));
                        }
                        
                        if(stringLoop.substring(0, stringLoop.indexOf("=")-1).equals("double")){

                        }
                        objectName.get(subStringLoop2).arrLength=tempLineSkip;
                        i++;
                        tempLineSkip++;
                        
                    }

                }
                objectName.get(subStringLoop).arrLength = tempLineSkip;
                i++;
                subStringLoop = null;
                subStringLoop2= null;
                stringLoop = null;

            }

        }


    }
    public int findInt(String group, String constantName){
        return Integer.parseInt(objectName.get(group).assignedConstants.get(constantName).value);
    }
    public Double finDouble(String group, String constantName){ 
        return Double.parseDouble(objectName.get(group).assignedConstants.get(constantName).value);
    }
}
