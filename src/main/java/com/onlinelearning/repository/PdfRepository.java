package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.PdfListResponseDto;
import com.onlinelearning.model.dto.response.PdfUpdateResponseDto;
import com.onlinelearning.model.entity.Pdf;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PdfRepository extends JpaRepository<Pdf, Integer> {

    @Query("""
            select new com.onlinelearning.model.dto.response.PdfListResponseDto(p.pdfId, p.pdfName, p.filePdf, p.lessonId)
            from Pdf p
            where p.lessonId in (:lessonIds)""")
    List<PdfListResponseDto> getPdfsByLessonIds(List<Integer> lessonIds);

    @Query("""
            select new com.onlinelearning.model.dto.response.PdfUpdateResponseDto(p.pdfId, p.pdfName, p.filePdf, p.lessonId, c.courseId)
            from Pdf p join Lesson l on p.lessonId = l.lessonId join Course c on l.courseId = c.courseId
            where p.pdfId = :pdfId and c.deleted = false""")
    PdfUpdateResponseDto getPdf(int pdfId);

    @Transactional
    @Modifying
    @Query("update Pdf p set p.pdfName = :pdfName, p.filePdf = :filePdf, p.updatedAt = :updatedAt where p.pdfId = :pdfId")
    void updatePdf(String pdfName, String filePdf, Instant updatedAt, int pdfId);

    @Query("""
            SELECT exists (
                        select 1 from Pdf p join Lesson l on p.lessonId = l.lessonId join Course c on l.courseId = c.courseId
            where p.pdfId = :pdfId and c.deleted = false)""")
    boolean isPdfExist(int pdfId);
}
