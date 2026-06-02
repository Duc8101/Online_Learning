package com.onlinelearning.service;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.PdfCreateRequestDto;
import com.onlinelearning.model.dto.request.PdfUpdateRequestDto;

public interface ManagerPdfService {

    ResponseBase create(PdfCreateRequestDto DTO);

    ResponseBase update(int pdfId, PdfUpdateRequestDto DTO);

    ResponseBase delete(int pdfId, int courseId);
}
