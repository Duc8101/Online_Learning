package com.onlinelearning.controller;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.PdfCreateRequestDto;
import com.onlinelearning.model.dto.request.PdfUpdateRequestDto;
import com.onlinelearning.service.ManagerPdfService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ManagerPdf")
@AllArgsConstructor
public class ManagerPdfController {

    private final ManagerPdfService managerPdfService;

    @PostMapping("/Create")
    public ModelAndView create(PdfCreateRequestDto DTO) {
        ResponseBase responseBase = managerPdfService.create(DTO);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @PostMapping("/Update/{pdfId}")
    public ModelAndView update(@PathVariable int pdfId, PdfUpdateRequestDto DTO) {
        ResponseBase responseBase = managerPdfService.update(pdfId, DTO);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }

    @GetMapping({ "/Delete/{pdfId}"})
    public ModelAndView delete(@PathVariable int pdfId, int courseId) {
        ResponseBase responseBase = managerPdfService.delete(pdfId, courseId);
        return new ModelAndView(responseBase.getViewName(), responseBase.getData());
    }
}
