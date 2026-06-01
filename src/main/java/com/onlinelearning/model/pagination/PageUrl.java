package com.onlinelearning.model.pagination;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageUrl {

    private String preUrl;
    private String nextUrl;
    private String firstUrl;
    private String lastUrl;
}
