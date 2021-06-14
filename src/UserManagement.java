import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class UserManagement {
    private ArrayList<User>allUsers;

    public UserManagement() {

        allUsers = new ArrayList<>();
        File temp = new File("user.txt");
        File temp2 = new File("capabilities.txt");
        if(temp.length()>0){
            loadUsers();
        }
        if(temp2.length()>0){
            loadCapabilities();
        }

    }
    public UserManagement(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }


    private String UtilSaveUsers(String res){
        for(int i=0 ; i<allUsers.size() ;i++) {
            res+=allUsers.get(i).getName();
            res+=',';
            res+=allUsers.get(i).getPass();
            if(i!=allUsers.size()-1) res+=',';
        }
        return res;
    }

    public void saveUsers() {
        BufferedWriter bw = null;
        try {
            String res=this.UtilSaveUsers("");
            File destFile = new File("user.txt");
            FileWriter fw = new FileWriter(destFile);
            bw = new BufferedWriter(fw);
            bw.write(res);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter "+ex);
            }
        }
    }

    public void loadUsers (){
        try (BufferedReader br = Files.newBufferedReader(Path.of("user.txt"),
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            String[] all=line.split(",");
            for(int i=0 ; i<all.length-1 ; i+=2){
                User newUser = new User(all[i],all[i+1]);
                allUsers.add(newUser);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    // {  <x,y>, <x,y>                 }
    private String UtilSaveCapabilities(String res){
        for(int i=0 ; i<allUsers.size() ;i++) {
            for(int r=0 ; r<allUsers.get(i).getAllCapabilities().size(); r++) {
                res+=allUsers.get(i).getAllCapabilities().get(r).getDisk();
                res+=',';
                res+=allUsers.get(i).getAllCapabilities().get(r).getPath();
                res+=',';
                res+=allUsers.get(i).getName();
                res+=',';
                res+=allUsers.get(i).getAllCapabilities().get(r).getDigits();
                res+=',';
            }
        }
        return res;
    }

    public void saveCapabilities() {
        BufferedWriter bw = null;
        try {
            String res=this.UtilSaveCapabilities("");
            File destFile = new File("capabilities.txt");
            FileWriter fw = new FileWriter(destFile);
            bw = new BufferedWriter(fw);
            bw.write(res);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter "+ex);
            }
        }
    }

    public void loadCapabilities (){
        try (BufferedReader br = Files.newBufferedReader(Path.of("capabilities.txt"),
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            String[] all=line.split(",");
            for(int i=0 ; i<all.length-3 ; i+=4){
                capability cap = new capability(all[i],all[i+1],all[i+3]);
                String userName = all[i+2];
                User getUser = new User("","");
                for(int r=0 ; r<allUsers.size();r++){
                    if(allUsers.get(r).getName().equalsIgnoreCase(userName)){
                        getUser = allUsers.get(r);
                        break;
                    }
                }
                getUser.addCapability(cap);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void print(){
        for(int i=0 ; i<allUsers.size() ; i++){
            allUsers.get(i).print();
        }
    }

    public void createUser(String name, String pass){
        if(name.equalsIgnoreCase("admin")){
            System.out.println("You can't set your username as 'admin'");
        }
        for(int i=0 ; i< allUsers.size();i++){
            if(allUsers.get(i).getName().equalsIgnoreCase(name)){
                System.out.println("This user name is already exists.");
                return;
            }
        }
        User newUser = new User(name,pass);
        allUsers.add(newUser);
    }

    public boolean isFound(String name, String pass){
        for(int i=0 ; i< allUsers.size() ; i++) {
            if(allUsers.get(i).getName().equalsIgnoreCase(name) && allUsers.get(i).getPass().equals(pass)){
                return true;
            }
        }
        return false;
    }

    public User logIn(String name, String pass){
        for(int i=0 ; i< allUsers.size() ; i++) {
            if(allUsers.get(i).getName().equalsIgnoreCase(name) && allUsers.get(i).getPass().equals(pass)){
                return allUsers.get(i);
            }
        }
        return new User("","");
    }

    public void grant(String name, String path , String digits, String disk) {
        User newUser = new User("", "");
        for(int i=0 ; i<allUsers.size();i++) {
            if(allUsers.get(i).getName().equalsIgnoreCase(name)){
                newUser=allUsers.get(i);
                break;
            }
        }
        if(newUser.getName().equalsIgnoreCase("") && newUser.getPass().equalsIgnoreCase("")){
            System.out.println("There is no user with this name.");
            return;
        }
        capability cap = new capability(disk, path, digits);
        for(int i=0;i<newUser.getAllCapabilities().size();i++){
            if(newUser.getAllCapabilities().get(i).getPath().equalsIgnoreCase(path)){
                newUser.getAllCapabilities().set(i,cap);
                return;
            }
        }
        newUser.addCapability(cap);
    }

    public void updateCapabilities(String path, String disk){
        for(int i=0 ; i<allUsers.size() ; i++) {
            allUsers.get(i).updateCapabilities(path, disk);
        }
    }

}
