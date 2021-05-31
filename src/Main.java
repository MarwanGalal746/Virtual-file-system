import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static void commands(allocation obj) throws IOException {
        String cmd = "";
        while(!cmd.equals("exit")){
            System.out.print("$");
            cmd=in.nextLine();
            String[] cmdSections = cmd.split(" ");
            if(cmdSections[0].equals("CreateFile")){
                obj.createFile(cmdSections[1], Integer.parseInt(cmdSections[2]));

            } else if(cmdSections[0].equalsIgnoreCase("CreateFolder")) {
                obj.createFolder(cmdSections[1]);
            } else if(cmdSections[0].equalsIgnoreCase(("DeleteFile"))) {
                Directory dir = Directory.checkPath(cmdSections[1], obj.getRoot()  );
                String fileName[] = cmdSections[1].split("/");
                obj.deleteFile(dir, fileName[fileName.length-1]);
            } else if(cmdSections[0].equalsIgnoreCase(("DeleteFolder"))){
                obj.deleteFolder(cmdSections[1]);
            } else if(cmdSections[0].equalsIgnoreCase(("DisplayDiskStatus"))){
                obj.DisplayDiskStatus();
            } else if(cmdSections[0].equalsIgnoreCase(("DisplayDiskStructure"))) {
                obj.DisplayDiskStructure();
            } else {
                break;
            }
            obj.getRoot().saveToFile(obj.getFileName(), obj.getBlocks());

        }
    }

    public static void start() throws IOException {
        String again="yes";
        while(again.equals("yes")){
            String choice;
            System.out.println("Which technique do you want to apply?");
            System.out.println("1- Contiguous allocation.");
            System.out.println("2- Linked allocation.");
            System.out.println("3- Indexed allocation.");

            choice = in.nextLine();
            allocation obj;
            if(choice.equals("1")){
                obj = new contiguousAllocation("contiguousAllocation.txt");
            } else if(choice.equals("2")){
                obj= new LinkedAllocation("linkedAllocation.txt");
            } else {
                obj = new IndexedAllocation("indexedAllocation.txt");
            }
            commands(obj);
        }
    }

    public static void main(String[] args) throws IOException {
        start();
    }
}
