package com.onlinelearning.model.pagination;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PagingRepo<T> {

    int currentPage;
    String preUrl;
    String nextUrl;
    String firstUrl;
    String lastUrl;
    List<T> content = new ArrayList<>();
    int numberPage;

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
