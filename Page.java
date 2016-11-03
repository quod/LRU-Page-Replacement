public class Page {

    private int id;
    private long timestamp;

    public Page(int id) {
        this.id = id;
        this.timestamp = System.nanoTime();
    }

    public int getId() {
        return this.id;
    }

    public void setTimestamp() {
        this.timestamp = System.nanoTime();
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String toString() {
        return "Page { id: " + this.id + ", timestamp: " + this.timestamp + "}";
    }
    
}
