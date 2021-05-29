import java.io.IOException;

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
            System.out.print("LinkedList: ");

            while (currNode != null)
            {
                System.out.print(currNode.position + " ");
                currNode = currNode.next;
            }
        }
    }

    public LinkedAllocation() throws IOException {
        fileName="LinkedAllocation.txt";
        root = new Directory();
        root=root.getData(fileName);
        blocks= "10010110001000";
    }

    public void createFile(String path)
    {
        String[] info = path.split(" ");
        int size = Integer.parseInt(info[1]);
        /*info[0]=info[0].replace("\\","@");
        String[] Name = info[0].split("@");
        int size = Integer.parseInt(info[1]);
        this.fileName= Name[1];*/
        LinkedList file = new LinkedList();

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < blocks.length(); j++)
            {
                String bit = String.valueOf(blocks.charAt(j));

                if(bit.equalsIgnoreCase("0"))
                {
                    file.insert(file, j);
                    blocks=blocks.replaceFirst("0","1");
                    break;
                }
            }
        }
        file.printList(file);
        //root.addFile(file);
    }
    public void deleteFile(String path)
    {

    }
}
