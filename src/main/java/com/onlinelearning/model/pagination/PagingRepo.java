package com.onlinelearning.model.pagination;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingRepo<T> {

    private int currentPage;
    private String preUrl;
    private String nextUrl;
    private String firstUrl;
    private String lastUrl;
    private List<T> content = new ArrayList<>();
    private int numberPage;

    public PagingRepo(int currentPage, int numberPage) {
        this.currentPage = currentPage;
        this.numberPage = numberPage;
    }

    public PagingRepo<T> withContent(List<T> content) {
        this.content = content;
        return this;
    }

    public PagingRepo<T> withUrl(PageUrl pageUrl) {
        this.preUrl = pageUrl.getPreUrl();
        this.nextUrl = pageUrl.getNextUrl();
        this.firstUrl = pageUrl.getFirstUrl();
        this.lastUrl = pageUrl.getLastUrl();
        return this;
    }
}
