package com.onlinelearning.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PdfListResponseDto {

    private int pdfId;
    private String pdfName;
    private String filePdf;
    private int lessonId;
}
