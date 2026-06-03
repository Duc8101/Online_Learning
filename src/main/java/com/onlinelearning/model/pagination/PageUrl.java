package com.onlinelearning.model.pagination;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageUrl {

    String preUrl;
    String nextUrl;
    String firstUrl;
    String lastUrl;
}
