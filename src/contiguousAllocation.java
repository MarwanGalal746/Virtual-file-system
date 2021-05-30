import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

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

    public int findMax(ArrayList<Integer> arr)
    {
        int max = -1;
        for(int i=0; i<arr.size(); i++)
        {
            if(arr.get(i) > max)
            {
                max = i;
            }
        }
        return max;
    }

    public int calculateEmptyBlocks(){
        int emptyBS = 0;
        ArrayList<Integer> emptyBlocks = new ArrayList<Integer>();
        ArrayList<Integer> blocksLocation = new ArrayList<Integer>();
        for(int i = 0 ; i < blocks.length(); i++){
            if(blocks.charAt(i)=='0'){
                emptyBS++;
            }
            else
            {
                emptyBlocks.add(emptyBS);
                blocksLocation.add(i);
                emptyBS=0;
            }
        }

        return blocksLocation.get(findMax(emptyBlocks));
    }
    public boolean emptyDisk()
    {
        boolean flag = true;
        for(int i=0; i<blocks.length(); i++)
        {
            if(blocks.charAt(i) != '0')
            {
                flag = false;
                break;
            }
        }
        return flag;
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

    public void createFile(String path,int size) {
        Directory parent;
        size++;
        parent = Directory.checkPath(path, root);
        String[] pathSections = path.split("/");
        String newFileName = pathSections[pathSections.length-1];
        if(parent != null){
            if(!parent.fileExist(newFileName)){
                file newFile = new file();
                newFile.setName(newFileName);
                int location;
                ArrayList<Integer> allocatedBlocks = new ArrayList<Integer>();
                if(emptyDisk())
                {
                    location = blocks.length() / 2 ;

                    for (int i = location; i < size; i++) {
                        blocks = blocks.substring(0, i) + "1" + blocks.substring(i + 1);
                        allocatedBlocks.add(i);
                    }
                }
                else {
                    calculateEmptyBlocks();
                    location = calculateEmptyBlocks();
                    for (int i = location; i < size; i++) {
                        blocks = blocks.substring(0, i) + "1" + blocks.substring(i + 1);
                        allocatedBlocks.add(i);
                    }
                }
                newFile.setAllocatedBlocks(allocatedBlocks);
                parent.addFile(newFile);
            }else{
                System.out.println("Error File Is Already Exist.");
            }
        }else {
            System.out.println("Error This Path Not Exist.");
        }
    }

    public void createFolder(String path){
        Directory parent;
        parent = Directory.checkPath(path, root);
        String[] pathSections = path.split("/");
        String newDirName = pathSections[pathSections.length-1];
        if(parent != null){
            if(!parent.fileExist(newDirName)){
                Directory newDir = new Directory();
                newDir.setName(newDirName);
                newDir.setSubDir(new ArrayList<>());
                newDir.setFiles(new ArrayList<>());
                parent.addDir(newDir);
            }else{
                System.out.println("Error File Is Already Exist.");
            }
        }else {
            System.out.println("Error This Path Not Exist.");
        }
    }

    public void deleteFile(String path){
        Directory parent;
        parent = Directory.checkPath(path, root);
        String[] pathSections = path.split("/");
        String tempFileName = pathSections[pathSections.length-1];
        if(parent != null){
            if(parent.fileExist(tempFileName)){
                blocks = parent.removeFile(tempFileName, blocks);
            }else{
                System.out.println("Error File Is Already Exist.");
            }
        }else {
            System.out.println("Error This Path Not Exist.");
        }
    }

    public void deleteFolder(String path){
        Directory parent;
        parent = Directory.checkPath(path, root);
        String[] pathSections = path.split("/");
        String newDirName = pathSections[pathSections.length-1];
        if(parent != null){
            int index = parent.dirExist(newDirName);
            if(index != -1){
                Directory tempDir = new Directory();
                tempDir = parent.getSubDir().get(index);
                System.out.println(tempDir.getName());
                blocks = tempDir.removeThisDir(blocks);
                parent.getSubDir().remove(index);

            }else{
                System.out.println("Error Folder Is Already Exist.");
            }
        }else {
            System.out.println("Error This Path Not Exist.");
        }
    }
}
