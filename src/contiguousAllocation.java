import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class contiguousAllocation extends allocation{

    public contiguousAllocation(String fileName) throws IOException {
        super(fileName);
    }

    private int findMax(ArrayList<Integer> arr)
    {
        int max = -1,index = -1;
        for(int i=0; i<arr.size(); i++)
        {
            if(arr.get(i) > max)
            {
                index=i;
                max = arr.get(i);
            }
        }
        return index;
    }

    private int calculateEmptyBlocks(){
        int emptyBS = 0;
        ArrayList<Integer> emptyBlocks = new ArrayList<Integer>();
        ArrayList<Integer> blocksLocation = new ArrayList<Integer>();
        for(int i = 0 ; i < getBlocks().length(); i++){
            if(blocks.charAt(i)=='0'){
                blocksLocation.add(i);
                emptyBS++;
            }
            else
            {
                emptyBlocks.add(emptyBS);
                emptyBS=0;
            }
        }
        return blocksLocation.get(findMax(emptyBlocks));
    }
    private boolean emptyDisk()
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

    public void createFile(String path,int size) {
        Directory parent;
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

                    for (int i = location; i < location+size; i++) {
                        blocks = blocks.substring(0, i) + "1" + blocks.substring(i + 1);
                    }
                }
                else {
                    calculateEmptyBlocks();
                    location = calculateEmptyBlocks();
                    for (int i = location; i < location+size; i++) {
                       this.blocks = blocks.substring(0, i) + "1" + blocks.substring(i + 1);
                    }
                }
                allocatedBlocks.add(location);
                allocatedBlocks.add(size);
                newFile.setAllocatedBlocks(allocatedBlocks);
                parent.addFile(newFile);
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
//        for(int i=0 ; i<parent.getFiles().size();i++)
//            System.out.print(parent.getFiles().get(i).getName() + " ");
        //System.out.println();
        for (int i = 0; i < parent.getFiles().size(); i++) {
            temp = parent.getFiles().get(i);
            if(fileName.equalsIgnoreCase(temp.getName())){
                for(int r=parent.getFiles().get(i).getAllocatedBlocks().get(0); r<parent.getFiles().get(i).getAllocatedBlocks().get(0)+parent.getFiles().get(i).getAllocatedBlocks().get(1) ; r++) {
                    this.blocks = this.blocks.substring(0, r) + "0" + this.blocks.substring(r + 1);
                }
                parent.getFiles().remove(i);
                return;
            }
        }
        System.out.println("Error File Not Exist");
    }
}
