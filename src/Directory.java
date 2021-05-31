import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Directory {
    private String name;
    private ArrayList<file> files;
    private ArrayList<Directory> subDir;
    private Directory temp;

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

    int dirExist(String dirName){
        int index = -1;
        for (int i = 0; i < subDir.size(); i++) {
            if(subDir.size() > 0 && dirName.equalsIgnoreCase(subDir.get(i).name)){
                index = i;
                break;
            }
        }
        return index;
    }

    static Directory checkPath(String path, Directory root){
        Directory temp = root;
        int index;
        path = path.trim();
        String[] pathSections = path.split("/");
        if(!temp.name.equalsIgnoreCase(pathSections[0])){
            return null;
        }
        for (int i = 1; i < pathSections.length - 1; i++) {
            index = temp.dirExist(pathSections[i]);
            if(index != -1){
                temp = temp.subDir.get(index);
            }else{
                return null;
            }
        }
        return temp;
    }

    boolean fileExist(String fileName){
        boolean found = false;
        for (int i = 0; i < files.size(); i++) {
            if(files.size() > 0 && fileName.equalsIgnoreCase(files.get(i).getName())){
                found = true;
                break;
            }
        }
        return found;
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
//    public String removeFile(String fileName, String blocks){
//        file temp;
//        for(int i=0 ; i<files.size();i++)
//            System.out.print(files.get(i).getName() + " ");
//        System.out.println();
//        for (int i = 0; i < files.size(); i++) {
//            temp = files.get(i);
//            if(fileName.equalsIgnoreCase(temp.getName())){
////                //   3 5
////                //0001010110
////                //000 + 0 + 010110
////                ArrayList<Integer> allocatedBlocks = temp.getAllocatedBlocks();
////                for (int j = 0; j < allocatedBlocks.size(); j++) {
////                    blocks = blocks.substring(0,allocatedBlocks.get(j))+'0'+ blocks.substring(allocatedBlocks.get(j) + 1);
////                }
//                files.remove(i);
//                break;
//            }
//        }
//        for(int i=0 ; i<files.size();i++)
//            System.out.print(files.get(i).getName() + " ");
//        System.out.println();
//        return blocks;
//    }
/*
    public boolean removeDir(String dirName, String blocks){
        Directory temp;
        for (int i = 0; i < subDir.size(); i++) {
            if(subDir.get(i).getName() == dirName){
                temp = subDir.get(i);
                for (int j = 0; j < temp.files.size(); j++) {
                    temp.removeFile(temp.files.get(j).getName(), blocks);
                }
                //temp.removeDir()
                return true;
            }
        }
        return false;
    }
    */
    //x subdir => z y
    /*
    public void removeThisDir(String blocks){
        Directory temp;
        for (int i = 0; i < subDir.size(); i++) {
            temp = subDir.get(i);
            for (int j = 0; j < temp.files.size(); j++) {
                temp.removeFile(temp.files.get(j).getName(), blocks);
            }
            for (int j = 0; j < temp.subDir.size(); j++) {
                temp.subDir.get(j).removeThisDir(blocks);
            }
        }
    }*/
//    public String removeThisDir(String blocks){
//        for (int j = 0; j < this.files.size(); j++) {
//            blocks = this.removeFile(this.files.get(j).getName(), blocks);
//        }
//        for (int i = 0; i < subDir.size(); i++) {
//            temp = subDir.get(i);
//            blocks = temp.removeThisDir(blocks);
//        }
//        return blocks;
//    }

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
        if(data.size() == 2) return root;
        if(data.size()>0){
            root.setName(data.get(0));
            Stack<Directory>allDirs = new Stack<>();
            Stack<Integer>spaces = new Stack<>();
            allDirs.push(root);
            spaces.push(0);
            for(int i=1 ; i<data.size();i++){
                int numOfSpaces= (int) data.get(i).chars().filter(ch -> ch == ' ').count();
                data.set(i,data.get(i).trim());
                String[] content=data.get(i).split(" ");
                if(content.length>1){
                    //numOfSpaces-=2;
                    file newFile = new file();
                    newFile.setName(content[0]);
                    ArrayList<Integer>temp = new ArrayList<>();
                    for(int r=1;r<content.length;r++){
                        temp.add(Integer.parseInt(content[r]));
                    }
                    numOfSpaces -= content.length -1;
                    newFile.setAllocatedBlocks(temp);
                    allDirs.peek().addFile(newFile);
                    if(data.get(i+1).charAt(0)==('0') || data.get(i+1).charAt(0)==('1'))
                        break;
                    else if((int) data.get(i+1).chars().filter(ch -> ch == ' ').count() < numOfSpaces){
                        allDirs.pop();
                        spaces.pop();
                        while((int) data.get(i+1).chars().filter(ch -> ch == ' ').count() <= spaces.peek()){
                            allDirs.pop();
                            spaces.pop();
                        }
                    }
                } else {
                    Directory newDir = new Directory();
                    newDir.setName(content[0]);
                    allDirs.peek().addDir(newDir);
                    allDirs.push(newDir);
                    spaces.push(numOfSpaces);
                    if(data.get(i+1).charAt(0)==('0') || data.get(i+1).charAt(0)==('1'))
                        break;
                    else if((int) data.get(i+1).chars().filter(ch -> ch == ' ').count() <= numOfSpaces){
                        allDirs.pop();
                        spaces.pop();
                        while((int) data.get(i+1).chars().filter(ch -> ch == ' ').count() <= spaces.peek()){
                            allDirs.pop();
                            spaces.pop();
                        }
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

    private String UtilsaveToFile(String res, int level){
        res+=printSpaces("",level);
        res+=this.name;
        res+='\n';
        if(this.files.size()>0){
            for(int i=0 ; i<files.size();i++){
                res+=printSpaces("",level+1);
                res+=files.get(i).getName();
                for(int r=0;r<files.get(i).getAllocatedBlocks().size();r++){
                    res+=' ';
                    res+=String.valueOf(files.get(i).getAllocatedBlocks().get(r));
                }
                res+='\n';
            }
        }
        if(this.subDir.size()==0) return res;
        for(int i=0 ; i<this.subDir.size();i++){
            res+=this.subDir.get(i).UtilsaveToFile("", level+1);
        }
        return res;
    }

    public void saveToFile(String f,String blocks) throws IOException {
        BufferedWriter bw = null;
        try {
            String res=this.UtilsaveToFile("",0);
            res+=blocks;
            File destFile = new File(f);
            FileWriter fw = new FileWriter(destFile);
            bw = new BufferedWriter(fw);
            bw.write(res);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter "+ex);
            }
        }
    }
}
