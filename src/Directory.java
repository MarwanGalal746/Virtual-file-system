import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;

public class Directory {
    private String name;
    private ArrayList<file> files;
    private ArrayList<Directory> subDir;

    public Directory(String path, String name, ArrayList<file> files, ArrayList<Directory> subDir) {
        this.name = name;
        this.files = files;
        this.subDir = subDir;
    }

    public Directory() {
        name="";
        files = new ArrayList<file>();
        subDir = new ArrayList<Directory>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<file> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<file> files) {
        this.files = files;
    }

    public ArrayList<Directory> getSubDir() {
        return subDir;
    }

    public void setSubDir(ArrayList<Directory> subDir) {
        this.subDir = subDir;
    }

    public void addFile(file file){
        files.add(file);
    }

    public void addDir(Directory dir){
        subDir.add(dir);
    }

    private String printSpaces(String res, int level){
        for(int i=0 ; i<level*3; i++)
            res+=" ";
        return res;
    }

    private String UtilgetDirStruct(String res, int level){
        res+=printSpaces("",level);
        res+=this.name;
        res+='\n';
        if(this.files.size()>0){
            for(int i=0 ; i<files.size();i++){
                res+=printSpaces("",level+1);
                res+=files.get(i).getName();
                res+='\n';
            }
        }
        if(this.subDir.size()==0) return res;
        for(int i=0 ; i<this.subDir.size();i++){
            res+=this.subDir.get(i).UtilgetDirStruct("", level+1);
        }
        return res;
    }

    public String getDirStruct() {
        String res=this.UtilgetDirStruct("",0);
        return res;
    }

    private ArrayList<String> loadData(String f) throws IOException {
        ArrayList<String> res = new ArrayList<String>();
        try (BufferedReader br = Files.newBufferedReader(Path.of(f),
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null){
                res.add(line);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return res;
    }

    public Directory getData(String f) throws IOException {
        ArrayList<String>data = loadData(f);
        Directory root = new Directory();
        if(data.size()>0){
            root.setName(data.get(0));
            Stack<Directory>allDirs = new Stack<>();
            allDirs.push(root);
            for(int i=1 ; i<data.size();i++){
                int numOfSpaces= (int) data.get(i).chars().filter(ch -> ch == ' ').count();
                data.set(i,data.get(i).trim());
                String[] content=data.get(i).split(" ");
                if(content.length>1){
                    numOfSpaces-=2;
                    file newFile = new file();
                    newFile.setName(content[0]);
                    ArrayList<Integer>temp = new ArrayList<>();
                    for(int r=1;r<content.length;r++)
                        temp.add(Integer.parseInt(content[r]));
                    newFile.setAllocatedBlocks(temp);
                    allDirs.peek().addFile(newFile);
                    if(data.get(i+1).charAt(0)==('0') || data.get(i+1).charAt(0)==('1'))
                        break;
                    else if((int) data.get(i+1).chars().filter(ch -> ch == ' ').count() < numOfSpaces){
                        allDirs.pop();
                    }
                } else {
                    Directory newDir = new Directory();
                    newDir.setName(content[0]);
                    allDirs.peek().addDir(newDir);
                    allDirs.push(newDir);
                    if(data.get(i+1).charAt(0)==('0') || data.get(i+1).charAt(0)==('1'))
                        break;
                    else if((int) data.get(i+1).chars().filter(ch -> ch == ' ').count() <= numOfSpaces){
                        allDirs.pop();
                    }
                }
            }
        }
        return root;
    }

    public String getBlocks(String f){
        ArrayList<Boolean>res = new ArrayList<>();
        String temp="";
        try (BufferedReader br = Files.newBufferedReader(Path.of(f),
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null){
                temp=line;
                line = br.readLine();
                if(line==null)
                    break;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return temp;
    }


}
