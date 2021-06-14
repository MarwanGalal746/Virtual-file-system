public class capability {
    private String digits;
    private String disk;
    private String path;

    public capability( String disk, String path,String digits) {
        this.digits = digits;
        this.disk = disk;
        this.path = path;
    }

    public String getDigits() {
        return digits;
    }

    public void setDigits(String digits) {
        this.digits = digits;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void print() {
        System.out.println(this.disk+" "+this.path+ " " + this.digits);
    }

}
