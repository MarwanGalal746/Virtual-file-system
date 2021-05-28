import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class contiguousAllocation {
    private Directory root;
    private String blocks;
    private String fileName;


    public contiguousAllocation() throws IOException {
        fileName="contiguousAllocation.txt";
        root = new Directory();
        root=root.getData(fileName);
        blocks= root.getBlocks(fileName);
    }

    public String getBlocks() {
        return blocks;
    }

    public void setBlocks(String blocks) {
        this.blocks = blocks;
    }

    public Directory getRoot() {
        return root;
    }

    public void setRoot(Directory root) {
        this.root = root;
    }

    public void DisplayDiskStatus(){
        int allocatedSpace=0;
        for(int i=0 ; i<blocks.length();i++){
            if(blocks.charAt(i)=='1') allocatedSpace++;
        }
        System.out.println("Empty space: " + (1000-allocatedSpace) + " KB");
        System.out.println("Free space: " + (allocatedSpace) + " KB");
        System.out.println("Empty Blocks in the Disk: " + (1000-allocatedSpace) + " blocks");
        System.out.println("Allocated  Blocks in the Disk: " + (allocatedSpace) + " blocks");
    }

    public void DisplayDiskStructure(){
        System.out.println(root.getDirStruct());
    }

    public void CreateFile(String path){

    }
}
