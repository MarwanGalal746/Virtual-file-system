import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);

    public static String getDigits(User usr, String path , String disk) {
        for(int i=0 ; i<usr.getAllCapabilities().size();i++) {
            if(path.contains(usr.getAllCapabilities().get(i).getPath()) &&
                    usr.getAllCapabilities().get(i).getDisk().equals(disk) &&
                    path.length() > usr.getAllCapabilities().get(i).getPath().length()){
                return usr.getAllCapabilities().get(i).getDigits();
            }
        }
        return "00";
    }

    public static void commands(allocation obj) throws IOException {
        User ourUser = new User("admin", "admin");
        UserManagement Manager = new UserManagement();
        String cmd = "";
        while(!cmd.equals("exit")){
            System.out.print("$");
            cmd=in.nextLine();
            String[] cmdSections = cmd.split(" ");
            if(cmdSections[0].equals("CreateFile")){
                String digits = getDigits(ourUser, cmdSections[1], obj.getFileName());
                if(ourUser.getName().equalsIgnoreCase("admin") || digits.charAt(0)=='1'){
                    obj.createFile(cmdSections[1], Integer.parseInt(cmdSections[2]));
                } else {
                    System.out.println("You have not access to create a file in this directory");
                }

            } else if(cmdSections[0].equalsIgnoreCase("CreateFolder")) {
                String digits = getDigits(ourUser, cmdSections[1], obj.getFileName());
                if(ourUser.getName().equalsIgnoreCase("admin") || digits.charAt(0)=='1'){
                    obj.createFolder(cmdSections[1]);
                } else {
                    System.out.println("You have not access to create a folder in this directory");
                }

            } else if(cmdSections[0].equalsIgnoreCase(("DeleteFile"))) {
                String digits = getDigits(ourUser, cmdSections[1], obj.getFileName());
                if(ourUser.getName().equalsIgnoreCase("admin") || digits.charAt(1)=='1'){
                    Directory dir = Directory.checkPath(cmdSections[1], obj.getRoot()  );
                    String fileName[] = cmdSections[1].split("/");
                    obj.deleteFile(dir, fileName[fileName.length-1]);
                } else {
                    System.out.println("You have not access to delete a file in this directory");
                }

            } else if(cmdSections[0].equalsIgnoreCase(("DeleteFolder"))){
                String digits = getDigits(ourUser, cmdSections[1], obj.getFileName());
                if(ourUser.getName().equalsIgnoreCase("admin") || digits.charAt(1)=='1'){
                    obj.deleteFolder(cmdSections[1]);
                    Manager.updateCapabilities(cmdSections[1], obj.getFileName());
                } else {
                    System.out.println("You have not access to delete a file in this directory");
                }

            } else if(cmdSections[0].equalsIgnoreCase(("DisplayDiskStatus"))){
                obj.DisplayDiskStatus();
            } else if(cmdSections[0].equalsIgnoreCase(("DisplayDiskStructure"))) {
                obj.DisplayDiskStructure();
            } else if(cmdSections[0].equalsIgnoreCase("TellUser")) {
                System.out.println(ourUser.getName());
            } else if(cmdSections[0].equalsIgnoreCase("CUser")){
                if(ourUser.getName().equalsIgnoreCase("admin")){
                    Manager.createUser(cmdSections[1], cmdSections[2]);
                } else {
                    System.out.println("You are not the admin, Only the admin can use this command");
                }
            } else if(cmdSections[0].equalsIgnoreCase("Grant")){
                if(ourUser.getName().equalsIgnoreCase("admin")){
                    Manager.grant(cmdSections[1], cmdSections[2], cmdSections[3], obj.getFileName());
                } else {
                    System.out.println("You are not the admin, Only the admin can use this command");
                }
            } else if(cmdSections[0].equalsIgnoreCase("Login")){
                if(cmdSections[1].equalsIgnoreCase("admin") && cmdSections[2].equalsIgnoreCase("admin")){
                    ourUser = new User("admin", "admin");
                } else{
                    if(Manager.isFound(cmdSections[1], cmdSections[2])){
                        ourUser = Manager.logIn(cmdSections[1], cmdSections[2]);
                    } else {
                        System.out.println("Username and password are not matching.");
                    }
                }

            }
            else {
                break;
            }
            obj.getRoot().saveToFile(obj.getFileName(), obj.getBlocks());
            Manager.saveUsers();
            Manager.saveCapabilities();
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
