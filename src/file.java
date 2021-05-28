import java.util.ArrayList;

public class file {
    private String name;
    private ArrayList<Integer>allocatedBlocks;

    public file(String path, String name, ArrayList<Integer> allocatedBlocks) {
        this.allocatedBlocks = allocatedBlocks;
        this.name = name;
    }

    public file() {
        allocatedBlocks = new ArrayList<Integer>();
    }


    public String getName() {
        return name;
    }

    public ArrayList<Integer> getAllocatedBlocks() {
        return allocatedBlocks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllocatedBlocks(ArrayList<Integer> allocatedBlocks) {
        this.allocatedBlocks = allocatedBlocks;
    }
}
