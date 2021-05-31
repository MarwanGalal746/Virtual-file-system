import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class IndexedAllocation extends allocation{

    public IndexedAllocation(String fileName) throws IOException {
        super(fileName);
    }

    private int getEmptyBlockSize(){
        int emptyBS = 0;
        for(int i = 0 ; i < blocks.length(); i++){
            if(blocks.charAt(i)=='0'){
                emptyBS++;
            }
        }
        return emptyBS;
    }


    public void createFile(String path, int size){
        Directory parent;
        size++;
        parent = Directory.checkPath(path, root);
        String[] pathSections = path.split("/");
        String newFileName = pathSections[pathSections.length-1];
        if(parent != null){
            if(!parent.fileExist(newFileName)){
                if(getEmptyBlockSize() >= size){
                    String newBlocks = "";
                    file newFile = new file();
                    newFile.setName(newFileName);
                    ArrayList<Integer> allocatedBlocks = new ArrayList<>();
                    //111100111000  blocks
                    //11111
                    for (int i = 0; i < blocks.length(); i++) {
                        if(blocks.charAt(i) == '0'){
                            newBlocks = newBlocks + "1";
                            allocatedBlocks.add(i);
                            size--;
                        }else{
                            newBlocks = newBlocks + "1";
                        }
                        if(size == 0){
                            blocks = newBlocks + blocks.substring(newBlocks.length());
                            newFile.setAllocatedBlocks(allocatedBlocks);
                            parent.addFile(newFile);
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
        System.out.println();
    }

    @Override
    public void deleteFile(Directory parent, String fileName) {
        file temp;
        for (int i = 0; i < parent.getFiles().size(); i++) {
            temp = parent.getFiles().get(i);
            if(fileName.equalsIgnoreCase(temp.getName())){
//                //   3 5
//                //0001010110
//                //000 + 0 + 010110
                ArrayList<Integer> allocatedBlocks = temp.getAllocatedBlocks();
                for (int j = 0; j < allocatedBlocks.size(); j++) {
                    this.blocks = this.blocks.substring(0,allocatedBlocks.get(j))+'0'+ this.blocks.substring(allocatedBlocks.get(j) + 1);
                }
                parent.getFiles().remove(i);
                return;
            }
        }
        System.out.println("Error File Not Exist");
    }
}
