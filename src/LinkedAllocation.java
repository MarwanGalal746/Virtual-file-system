import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LinkedAllocation {

    private Directory root;
    private String blocks;
    private String fileName;

    public static class LinkedList {

        Node head;
        static class Node
        {
            int position;
            Node next;

            Node(int pos)
            {
                position = pos;
                next = null;
            }
        }

        public static LinkedList insert(LinkedList list, int data)
        {
            Node new_node = new Node(data);
            new_node.next = null;

            if (list.head == null)
            {
                list.head = new_node;
            }
            else
            {
                Node last = list.head;
                while (last.next != null)
                {
                    last = last.next;
                }

                last.next = new_node;
            }

            return list;
        }

        public static void printList(LinkedList list)
        {
            Node currNode = list.head;
            System.out.print("File Indexes: ");

            while (currNode != null)
            {
                System.out.print(currNode.position + " ");
                currNode = currNode.next;
            }
        }
    }

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
        LinkedList file = new LinkedList();
        file f = new file();
        f.setName(pathSections[1]);
        ArrayList<Integer> allocatedBlocks = new ArrayList<>();
        if(parent != null && !parent.fileExist(newFileName))
        {
                for (int i = 0; i < size; i++)
                {
                    for (int j = 0; j < blocks.length(); j++)
                    {
                        String bit = String.valueOf(blocks.charAt(j));

                        if (bit.equalsIgnoreCase("0"))
                        {
                            file.insert(file, j);
                            allocatedBlocks.add(j);
                            blocks = blocks.replaceFirst("0", "1");
                            break;
                        }
                    }
                }

        }
        f.setAllocatedBlocks(allocatedBlocks);
        file.printList(file);
        //root.addFile(file);
    }
    public void deleteFile(String path)
    {

    }
}
