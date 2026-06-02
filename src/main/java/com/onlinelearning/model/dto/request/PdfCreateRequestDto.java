package com.onlinelearning.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PdfCreateRequestDto {

    private String pdfName;
    private String filePdf;
    private int lessonId;
}
