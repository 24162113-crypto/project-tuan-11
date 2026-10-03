package vn.hcmute.dto;

import java.util.List;

public class PageResult_24162113<T> {
    private final List<T> items;
    private final int page;
    private final int totalPages;
    private final long total;

    public PageResult_24162113(List<T> items, int page, int totalPages, long total) {
        this.items = items;
        this.page = page;
        this.totalPages = totalPages;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotal() {
        return total;
    }
}
