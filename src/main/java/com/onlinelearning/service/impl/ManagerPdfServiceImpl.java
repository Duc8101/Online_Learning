package com.onlinelearning.service.impl;

import com.onlinelearning.mapper.LessonMapper;
import com.onlinelearning.mapper.PdfMapper;
import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.PdfCreateRequestDto;
import com.onlinelearning.model.dto.request.PdfUpdateRequestDto;
import com.onlinelearning.model.dto.response.PdfUpdateResponseDto;
import com.onlinelearning.model.dto.response.ViewLessonResponseDto;
import com.onlinelearning.model.entity.Lesson;
import com.onlinelearning.model.entity.Pdf;
import com.onlinelearning.repository.LessonRepository;
import com.onlinelearning.repository.PdfRepository;
import com.onlinelearning.service.ManagerPdfService;
import com.onlinelearning.service.common.BaseService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerPdfServiceImpl extends BaseService implements ManagerPdfService {

    final LessonRepository lessonRepository;
    final PdfRepository pdfRepository;
    final LessonMapper lessonMapper;
    final PdfMapper pdfMapper;

    private void setData(Map<String, Object> data, int courseId, String name, String pdf, Integer lessonId) {
        setValueForHeaderFooter(data, false, true, false, false);
        List<ViewLessonResponseDto> lessons = lessonMapper.toViewLessonResponseDTOs(lessonRepository.getLessonsForManagerAndViewLesson(courseId));

        data.put("lessons", lessons);
        data.put("pdf", pdf);
        data.put("video", "");
        data.put("name", name);
        data.put("lessonId", lessonId == null ? 0 : lessonId);
        data.put("courseId", courseId);
    }

    @Override
    public ResponseBase create(PdfCreateRequestDto DTO) {
        Map<String, Object> data = new HashMap<>();

        Lesson lesson = lessonRepository.getByLessonId(DTO.getLessonId());
        if (lesson == null) {
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "Lesson not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        if (DTO.getPdfName().trim().isEmpty()) {
            setData(data, lesson.getCourse().getCourseId(), null, null, DTO.getLessonId());
            data.put("error", "Pdf name not empty");
            return new ResponseBase("manager_lesson/list", data);
        }

        Pdf pdf = pdfMapper.toPdf(DTO);
        pdf.setLesson(lesson);
        pdfRepository.save(pdf);
        return new ResponseBase(String.format("redirect:/ManagerLesson/%d?lessonId=%d&name=%s&pdf=%s", lesson.getCourse().getCourseId(), DTO.getLessonId(), DTO.getPdfName().trim(), DTO.getFilePdf()), data);
    }

    @Override
    public ResponseBase update(int pdfId, PdfUpdateRequestDto DTO) {
        Map<String, Object> data = new HashMap<>();

        PdfUpdateResponseDto pdf = pdfRepository.getPdf(pdfId);
        if (pdf == null) {
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "Pdf not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        if (DTO.getPdfName().trim().isEmpty()) {
            setData(data, pdf.getCourseId(), pdf.getPdfName(), pdf.getFilePdf(), pdf.getLessonId());
            data.put("error", "Pdf name not empty");
            return new ResponseBase("manager_lesson/list", data);
        }

        if (!DTO.getFilePdf().trim().isEmpty()) {
            pdf.setFilePdf(DTO.getFilePdf().trim());
        }

        pdf.setPdfName(DTO.getPdfName().trim());
        pdfRepository.updatePdf(pdf.getPdfName(), pdf.getFilePdf(), Instant.now(), pdfId);
        return new ResponseBase(String.format("redirect:/ManagerLesson/%d?lessonId=%d&name=%s&pdf=%s", pdf.getCourseId(), pdf.getLessonId(), DTO.getPdfName().trim(), DTO.getFilePdf().trim().isEmpty() ? pdf.getFilePdf() : DTO.getFilePdf().trim()), data);
    }

    @Override
    public ResponseBase delete(int pdfId, int courseId) {
        Map<String, Object> data = new HashMap<>();
        if (!pdfRepository.isPdfExist(pdfId)) {
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "Pdf not found or course might be deleted");
            return new ResponseBase("shared/error", data);
        }

        pdfRepository.deleteById(pdfId);
        return new ResponseBase(String.format("redirect:/ManagerLesson/%d", courseId), data);
    }
}
