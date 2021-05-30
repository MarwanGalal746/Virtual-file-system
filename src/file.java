import java.util.ArrayList;

public class file {
    private String name;
    private ArrayList<Integer>allocatedBlocks;
    public static LinkedList linkedList;
    public file(String path, String name, ArrayList<Integer> allocatedBlocks) {
        this.allocatedBlocks = allocatedBlocks;
        this.name = name;
    }

    public file()
    {
        allocatedBlocks = new ArrayList<Integer>();
        linkedList = new LinkedList();
    }



    public String getName() {
        return name;
    }

    public ArrayList<Integer> getAllocatedBlocks() {
        return allocatedBlocks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllocatedBlocks(ArrayList<Integer> allocatedBlocks) {
        this.allocatedBlocks = allocatedBlocks;
    }

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

        public static void insert(LinkedList list, int data)
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
}
