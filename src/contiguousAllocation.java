import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class contiguousAllocation {
    private Directory root;
    private String blocks;
    private String fileName;


    public contiguousAllocation() throws IOException {
        fileName="contiguousAllocation.txt";
        root = new Directory();
        root=root.getData(fileName);
        blocks= root.getBlocks(fileName);
    }

    public String getBlocks() {
        return blocks;
    }

    public void setBlocks(String blocks) {
        this.blocks = blocks;
    }

    public Directory getRoot() {
        return root;
    }

    public void setRoot(Directory root) {
        this.root = root;
    }



}
