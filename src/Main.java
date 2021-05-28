import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("newFile.txt");
		BufferedReader br=new BufferedReader(fr);
		StringBuffer sb=new StringBuffer();
		String line;
		int x=00;
		while((line=br.readLine())!=null)
			x=Integer.parseInt(line);
		x++;
			System.out.println(x);

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