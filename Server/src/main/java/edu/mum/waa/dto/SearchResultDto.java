package edu.mum.waa.dto;

import org.springframework.data.domain.Pageable;

import java.util.List;

public class SearchResultDto<T> {

    private Pageable pageable;

    private List<T> result;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
