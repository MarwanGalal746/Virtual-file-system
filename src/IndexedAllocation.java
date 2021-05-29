import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class IndexedAllocation {
    private Directory root;
    private String blocks;
    private String fileName;


    public IndexedAllocation() throws IOException {
        fileName="indexedAllocation.txt";
        root = new Directory();
        root = root.getData(fileName);
        blocks = root.getBlocks(fileName);
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

    public int getEmptyBlockSize(){
        int emptyBS = 0;
        for(int i = 0 ; i < blocks.length(); i++){
            if(blocks.charAt(i)=='0'){
                emptyBS++;
            }
        }
        return emptyBS;
    }


    public void DisplayDiskStatus(){
        int allocatedSpace = 0;
        for(int i = 0 ; i < blocks.length(); i++){
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

    public void createFile(String path, int size){
        Directory parent;
        parent = Directory.checkPath(path, root);
        String[] pathSections = path.split("/");
        String newFileName = pathSections[pathSections.length-1];
        if(parent != null){
            if(parent.fileExist(newFileName)){
                if(getEmptyBlockSize() >= size + 1){
                    String newBlocks = "";
                    file newFile = new file();
                    newFile.setName(newFileName);
                    ArrayList<Integer> allocatedBlocks = new ArrayList<>();
                    for (int i = 0; i < blocks.length(); i++) {
                        if(blocks.charAt(i) == '0'){
                            newBlocks = newBlocks + "1";
                            allocatedBlocks.add(i);
                            size--;
                        }else{
                            newBlocks = newBlocks + "0";
                        }
                        if(size == 0){
                            blocks = newBlocks + blocks.substring(newBlocks.length());
                            newFile.setAllocatedBlocks(allocatedBlocks);
                            break;
                        }
                    }
                }else {
                    System.out.println("Error There Is No Empty Space.");
                }
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
            if(parent.fileExist(newDirName)){
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
                parent.removeFile(tempFileName, blocks);
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
            if(parent.dirExist(newDirName) == -1){
                Directory tempDir = new Directory();
                tempDir.removeThisDir(blocks);

            }else{
                System.out.println("Error Folder Is Already Exist.");
            }
        }else {
            System.out.println("Error This Path Not Exist.");
        }
    }
}
