public class LRU {

    private int maxPageCount;
    private Page[] pages;
    private int currentPageCount;
    private int pageFaults = 0;
    
    public LRU(int maxPageCount) {
        this.maxPageCount = maxPageCount;
        this.pages = new Page[maxPageCount];
        this.currentPageCount = 0;
        this.pageFaults = 0;
    }

    public int getPageFaults() {
        return this.pageFaults;
    }

    public boolean hasPage(int pageId) {
        for (int i = 0; i < this.maxPageCount; i++) {
            Page page = this.pages[i];
            if (page != null && page.getId() == pageId) {
                page.setTimestamp();
                return true;
            }
        }
        return false;
    }

    public void addPage(int pageId) {
        if (!this.hasPage(pageId)) {
            Page newPage = new Page(pageId);
            this.pageFaults++;
            if (this.currentPageCount < this.maxPageCount) {
                this.pages[this.currentPageCount++] = newPage;
            } 
            else {
                this.handlePageFault(newPage);
            }
        }
    }

    public void handlePageFault(Page newPage) {
        int replaceIndex = -1;
        long minTimestamp = Long.MAX_VALUE;
        for (int i = 0; i < this.maxPageCount; i++) {
            long timestamp = this.pages[i].getTimestamp();
            if (timestamp < minTimestamp) {
                minTimestamp = timestamp;
                replaceIndex = i;
            }
        }
        if (replaceIndex != -1) {
            this.pages[replaceIndex] = newPage;
        }
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < this.maxPageCount; i++) {
            Page page = this.pages[i];
            out += (page == null ? " -" : " " + page.getId());
        }
        return out;
    }
}
