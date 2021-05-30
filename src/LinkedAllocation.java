import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LinkedAllocation extends allocation {

    public LinkedAllocation(String fileName) throws IOException {
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


    public void createFile(String path, int size)
    {
        Directory parent;
        System.out.println(root);
        parent = Directory.checkPath(path, root);
        String[] pathSections = path.split("/");
        String newFileName = pathSections[pathSections.length-1];

        if(parent != null) {
            if (!parent.fileExist(newFileName)) {
                if (getEmptyBlockSize() >= size) {
                    file f = new file();
                    f.setName(newFileName);
                    ArrayList<Integer> allocatedBlocks = new ArrayList<>();

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < blocks.length(); j++) {
                            String bit = String.valueOf(blocks.charAt(j));

                            if (bit.equalsIgnoreCase("0")) {
                                System.out.println(j);
                                f.linkedList.insert(f.linkedList, j);
                                allocatedBlocks.add(j);
                                blocks = blocks.replaceFirst("0", "1");
                                break;
                            }
                        }
                    }
                    f.linkedList.printList(f.linkedList);
                    f.setAllocatedBlocks(allocatedBlocks);
                    parent.addFile(f);
                }
                else {
                    System.out.println("Error There Is No Empty Space.");
                }
            }else{
                System.out.println("Error File Is Already Exist.");
            }
        }else {
            System.out.println("Error This Path Not Exist.");
        }

    }

    @Override
    public void deAllocation(Directory parent, String fileName) {
        file temp;
        for (int i = 0; i < parent.getFiles().size(); i++)
            System.out.print(parent.getFiles().get(i).getName() + " ");
        System.out.println();
        for (int i = 0; i < parent.getFiles().size(); i++) {
            temp = parent.getFiles().get(i);
            if (fileName.equalsIgnoreCase(temp.getName())) {
//                //   3 5
//                //0001010110
//                //000 + 0 + 010110
//                ArrayList<Integer> allocatedBlocks = temp.getAllocatedBlocks();
//                for (int j = 0; j < allocatedBlocks.size(); j++) {
//                    blocks = blocks.substring(0,allocatedBlocks.get(j))+'0'+ blocks.substring(allocatedBlocks.get(j) + 1);
//                }
                for (int r = 0; r < parent.getFiles().get(i).getAllocatedBlocks().size(); r++) {
                    this.blocks = this.blocks.substring(0, parent.getFiles().get(i).getAllocatedBlocks().get(r)) +
                            "0" + this.blocks.substring(parent.getFiles().get(i).getAllocatedBlocks().get(r) + 1);
//                    if (r == parent.getFiles().get(i).getAllocatedBlocks().get(0)) {
//                        this.blocks = this.blocks.substring(0, r) + "0" + this.blocks.substring(r + 1);
//                        parent.getFiles().get(i).getAllocatedBlocks().remove(0);
//                    }
                }
                    parent.getFiles().remove(i);

                    break;
                }
            }
            for (int i = 0; i < parent.getFiles().size(); i++)
                System.out.print(parent.getFiles().get(i).getName() + " ");
            System.out.println();
    }
}