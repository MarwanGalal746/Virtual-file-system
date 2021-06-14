import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String name;
    private String pass;
    private ArrayList<capability>allCapabilities;


    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
        this.allCapabilities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ArrayList<capability> getAllCapabilities() {
        return allCapabilities;
    }

    public void addCapability(capability c) {
        this.allCapabilities.add(c);
    }

    public String getCapabilityOfPath(String path , String disk){
        for(int i=0 ; i<allCapabilities.size() ; i++){
            if(allCapabilities.get(i).getDisk().equalsIgnoreCase(disk) &&
                    allCapabilities.get(i).getPath().equals(path))
                return allCapabilities.get(i).getDigits();
        }
        return "00";
    }

    public void print() {
        System.out.println(this.name+" "+this.pass);
        for(int i=0 ; i<allCapabilities.size();i++){
            allCapabilities.get(i).print();
        }
        System.out.println();
        System.out.println();
    }

    public void updateCapabilities(String path, String disk){
        for(int i=0 ; i<allCapabilities.size() ; i++) {
            if(path.contains(allCapabilities.get(i).getPath()) &&
                    allCapabilities.get(i).getDisk().equals(disk)){
                allCapabilities.remove(i);
                i--;
            }
        }
    }
}
