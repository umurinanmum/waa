package edu.mum.waa.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

public class WaaPageable extends PageRequest {
    private static final long serialVersionUID = -4541509938956089562L;

    private int total;

    private int nextPage;

    private  int numberOfPages;

    public static WaaPageable of(int page, int size) {
        return of(page, size, Sort.unsorted());
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * Creates a new {@link PageRequest} with sort parameters applied.
     *
     * @param page zero-based page index.
     * @param size the size of the page to be returned.
     * @param sort must not be {@literal null}.
     * @since 2.0
     */
    public static WaaPageable of(int page, int size, Sort sort) {
        return new WaaPageable(page, size, sort);
    }

    public WaaPageable(int page, int size, Sort sort) {
        super(page, size);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
