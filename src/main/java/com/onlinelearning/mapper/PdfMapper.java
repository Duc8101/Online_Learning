package com.onlinelearning.mapper;

import com.onlinelearning.model.dto.request.PdfCreateRequestDto;
import com.onlinelearning.model.entity.Pdf;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PdfMapper {

    @Mapping(target = "pdfName", expression = "java(DTO.getPdfName().trim())")
    @Mapping(target = "filePdf", expression = "java(DTO.getFilePdf().trim())")
    Pdf toPdf(PdfCreateRequestDto DTO);
}
