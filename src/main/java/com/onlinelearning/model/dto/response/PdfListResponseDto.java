package com.onlinelearning.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PdfListResponseDto {

    int pdfId;
    String pdfName;
    String filePdf;
    int lessonId;
}
