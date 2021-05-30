import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LinkedAllocation {

    private Directory root;
    private String blocks;
    private String fileName;



    public LinkedAllocation() throws IOException
    {
        File temp = new File("LinkedAllocation.txt");
        fileName="LinkedAllocation.txt";
        root = new Directory();
        if(temp.length() == 0){
            blocks = "";
            for (int i = 0; i < 1000; i++) {
                blocks += "0";
            }
        }else{
            root = root.getData(fileName);
            blocks = root.getBlocks(fileName);
        }
    }

    public void createFile(String path, int size)
    {
        Directory parent;
        parent = Directory.checkPath(path, root);
        String[] pathSections = path.split("/");
        String newFileName = pathSections[pathSections.length-1];
        file f = new file();
        f.setName(pathSections[1]);
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

        //root.addFile(file);
    }
    public void deleteFile(String path)
    {

    }
}
