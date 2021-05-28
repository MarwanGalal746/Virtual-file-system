import java.nio.file.Files;
import java.util.ArrayList;

public class Directory {
    private String path;
    private String name;
    private ArrayList<file> files;
    private ArrayList<Directory> subDir;

    public Directory(String path, String name, ArrayList<file> files, ArrayList<Directory> subDir) {
        this.path = path;
        this.name = name;
        this.files = files;
        this.subDir = subDir;
    }

    public Directory() {
        path="";
        name="";
        files = new ArrayList<file>();
        subDir = new ArrayList<Directory>();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
}
