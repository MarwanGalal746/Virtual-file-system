import java.util.ArrayList;

public class file {
    private String path;
    private String name;
    private ArrayList<Integer>allocatedBlocks;

    public file(String path, String name, ArrayList<Integer> allocatedBlocks) {
        this.path = path;
        this.allocatedBlocks = allocatedBlocks;
        this.name = name;
    }

    public file() {
        path="";
        allocatedBlocks = new ArrayList<Integer>();
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getAllocatedBlocks() {
        return allocatedBlocks;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllocatedBlocks(ArrayList<Integer> allocatedBlocks) {
        this.allocatedBlocks = allocatedBlocks;
    }
}
