public class Main {

    public static void main(String[] args) {
	    Directory dir = new Directory();
	    dir.setName("root");
		Directory dir1 = new Directory();
		Directory dir2 = new Directory();
		dir1.setName("dir1");
		dir2.setName("dir2");
		File f1=new File();
		File f2=new File();
		f1.setName("file1");
		f2.setName("file2");
		dir1.addFile(f1);
		dir2.addFile(f2);

		File f3=new File();
		f3.setName("file3");
		dir.addFile(f3);
		Directory dir3 = new Directory();
		dir3.setName("dir3");
		File f4=new File();
		f4.setName("file4");
		dir3.addFile(f4);
		dir1.addDir(dir3);
		dir.addDir(dir1);
		dir.addDir(dir2);
		System.out.println(dir.getDirStruct());

    }
}
