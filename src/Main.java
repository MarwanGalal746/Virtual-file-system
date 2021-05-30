import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        contiguousAllocation test = new contiguousAllocation();
        //System.out.println(test.getRoot().getDirStruct());
        //System.out.println(test.getBlocks());
        //LinkedAllocation linkedAllocation = new LinkedAllocation();
        //linkedAllocation.createFile("file.txt 7");
        Directory root = new Directory();
        root.setName("root");
        Directory dir1 = new Directory();
        dir1.setName("dir13");
        Directory dir2 = new Directory();
        dir2.setName("dir24");
        Directory dir3 = new Directory();
        dir3.setName("dir35");
        Directory dir4 = new Directory();
        dir4.setName("dir46");
        Directory dir5 = new Directory();
        dir5.setName("dir57");
        Directory dir6 = new Directory();
        dir6.setName("dir64");

        file file1 = new file();
        file file2 = new file();
        file file3 = new file();

        test.setRoot(root);
        root.addDir(dir1);
        dir1.addDir(dir2);
        //dir2.addDir(dir3);
        //dir3.addDir(dir4);
        //dir5.addDir(dir6);
        test.createFile("root/file241",5);
       // test.createFile("root/dir13/file2123124",3);
       // test.createFile("root/dir24/dir2/file31241241",5);
        test.deleteFile("root/dir1/file1");
        test.deleteFolder("root/dir1");

        /*test.createFolder("root/dir1/dir2/dir2.1");
        test.createFile("root/dir1/dir2/dir2.1/file2.11", 5);
        System.out.println(root.getDirStruct());

        for (int i = 0; i < root.getFiles().size(); i++) {
            System.out.println(root.getFiles().get(i));
        }*/
        root.saveToFile("contiguousAllocation.txt", test.getBlocks());
        //System.out.println(root.getData("indexedAllocation.txt"));



//        contiguousAllocation x = new contiguousAllocation();
//        x.DisplayDiskStatus();
//        x.DisplayDiskStructure();
//        Directory dir = new Directory();
 //       dir=dir.getData("contiguousAllocation.txt");
   //     System.out.println(dir.getDirStruct());
     //   dir.saveToFile("test.txt","111001010101010101010101111111000");
        //System.out.println(dir.getDirStruct());
        //dir.saveToFile("test.txt");
        //System.out.println(dir.getAllocBlocks("contiguousAllocation.txt"));
//        ArrayList<Boolean> res = new ArrayList<Boolean>(1000);
//        res.add(1000,false);
//        System.out.println(res.get(0));
//        Directory dir = new Directory();
     //  dir=dir.getData("contiguousAllocation.txt");
      // System.out.println(dir.getDirStruct());
		//String someString = "elephant";
//		long count = someString.chars().filter(ch -> ch == 'e').count();
//		System.out.println(count);
//		try (BufferedReader br = Files.newBufferedReader(Path.of("contiguousAllocation.txt"),
//				StandardCharsets.US_ASCII)) {
//			String line = br.readLine();
//			//String line;
//			String diskStruct;
//			while (line != null) {
//				line =line.trim();
//				String[] attributes = line.split(" ");
//
//				line = br.readLine();
//			}
//
//		} catch (IOException ioe) {
//			ioe.printStackTrace();

//		}

//		Directory dir = new Directory();
//		dir.setName("root");
//		Directory dir1 = new Directory();
//		Directory dir2 = new Directory();
//		dir1.setName("dir1");
//		dir2.setName("dir2");
//		file f1=new file();
//		file f2=new file();
//		f1.setName("file1");
//		f2.setName("file2");
//		dir1.addFile(f1);
//		dir2.addFile(f2);
//
//		file f3=new file();
//		f3.setName("file3");
//		dir.addFile(f3);
//		Directory dir3 = new Directory();
//		dir3.setName("dir3");
//		file f4=new file();
//		f4.setName("file4");
//		dir3.addFile(f4);
//		dir1.addDir(dir3);
//		dir.addDir(dir1);
//		dir.addDir(dir2);
//		System.out.println(dir.getDirStruct());

    }
}


// testing file class and directory class
/*Directory dir = new Directory();
	    dir.setName("root");
		Directory dir1 = new Directory();
		Directory dir2 = new Directory();
		dir1.setName("dir1");
		dir2.setName("dir2");
		file f1=new file();
		file f2=new file();
		f1.setName("file1");
		f2.setName("file2");
		dir1.addFile(f1);
		dir2.addFile(f2);

		file f3=new file();
		f3.setName("file3");
		dir.addFile(f3);
		Directory dir3 = new Directory();
		dir3.setName("dir3");
		file f4=new file();
		f4.setName("file4");
		dir3.addFile(f4);
		dir1.addDir(dir3);
		dir.addDir(dir1);
		dir.addDir(dir2);
		System.out.println(dir.getDirStruct());*/