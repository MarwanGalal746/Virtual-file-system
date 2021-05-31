import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class allocation {
    protected Directory root;
    protected String blocks;
    protected String fileName;
    protected int numOfBlocks;

    public allocation(String fileName) throws IOException {
        File temp = new File(fileName);
        this.fileName=fileName;
        root = new Directory();
        numOfBlocks=1000;
        if(temp.length() == 0){
            root.setName("root");
            blocks = "";
            for (int i = 0; i < numOfBlocks; i++) {
                blocks += "0";
            }
        }else{
            root = root.getData(fileName);
            blocks = root.getBlocks(fileName);
        }
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

    public String getFileName() {
        return fileName;
    }

    public void setRoot(Directory root) {
        this.root = root;
    }

    public void DisplayDiskStatus(){
        int allocatedSpace=0;
        for(int i=0 ; i<blocks.length();i++){
            if(blocks.charAt(i)=='1') allocatedSpace++;
        }
        System.out.println("Empty space: " + (numOfBlocks-allocatedSpace) + " KB");
        System.out.println("Free space: " + (allocatedSpace) + " KB");
        System.out.println("Empty Blocks in the Disk: " + (numOfBlocks-allocatedSpace) + " blocks");
        for(int i=0 ; i<numOfBlocks ; i++){
            if(blocks.charAt(i) == '0')
                System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Allocated  Blocks in the Disk: " + (allocatedSpace) + " blocks");
        for(int i=0 ; i<numOfBlocks ; i++){
            if(blocks.charAt(i) == '1')
                System.out.print(i + " ");
        }
        System.out.println();
    }

    public void DisplayDiskStructure(){
        System.out.println(root.getDirStruct());
    }

    abstract public void createFile(String path,int size);

    abstract public void deleteFile(Directory parent, String fileName);



    public void createFolder(String path){
        Directory parent;
        parent = Directory.checkPath(path, root);
        String[] pathSections = path.split("/");
        String newDirName = pathSections[pathSections.length-1];
        if(parent != null){
            if(parent.dirExist(newDirName) == -1){
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


    private void removeThisDir(Directory dir, String blocks){
        int x= dir.getFiles().size();
        for (int j = 0;dir.getFiles().size()>0; ) {
            deleteFile(dir,dir.getFiles().get(j).getName());
        }
        for (int i = 0; i<dir.getSubDir().size();i++) {
            Directory temp = dir.getSubDir().get(i);
            removeThisDir(temp, blocks);
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
//                System.out.println(tempDir.getName());
                removeThisDir(tempDir,blocks);
                parent.getSubDir().remove(index);

            }else{
                System.out.println("Error Folder Is Already Exist.");
            }
        }else {
            System.out.println("Error This Path Not Exist.");
        }
        System.out.println();
    }





}
