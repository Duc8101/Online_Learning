package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.VideoListResponseDto;
import com.onlinelearning.model.dto.response.VideoUpdateResponseDto;
import com.onlinelearning.model.entity.Video;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

    @Query("""
            SELECT new com.onlinelearning.model.dto.response.VideoListResponseDto(v.videoId, v.videoName, v.fileVideo, v.lessonId)
            from Video v
            where v.lessonId in (:lessonIds)""")
    List<VideoListResponseDto> getVideosByLessonIds(List<Integer> lessonIds);

    @Query("""
            SELECT new com.onlinelearning.model.dto.response.VideoListResponseDto(v.videoId, v.videoName, v.fileVideo, v.lessonId)
            from Video v join Lesson l on v.lessonId = l.lessonId
            where l.courseId = :courseId""")
    List<VideoListResponseDto> getVideosOfCourse(int courseId, Pageable pageable);

    @Query("""
            select new com.onlinelearning.model.dto.response.VideoUpdateResponseDto(v.videoId, v.videoName, v.fileVideo, v.lessonId, l.courseId)
            from Video v join Lesson l on v.lessonId = l.lessonId JOIN Course c on l.courseId = c.courseId
            where v.videoId = :videoId and c.deleted = false""")
    VideoUpdateResponseDto getVideo(int videoId);

    @Transactional
    @Modifying
    @Query("update Video v set v.videoName = :videoName, v.fileVideo = :fileVideo, v.updatedAt = :updatedAt where v.videoId = :videoId")
    void updateVideo(String videoName, String fileVideo, Instant updatedAt, int videoId);

    @Query("SELECT exists (select 1 from Video v join Lesson l on v.lessonId = l.lessonId JOIN Course c on l.courseId = c.courseId where v.videoId = :videoId and c.deleted = false)")
    boolean isVideoExist(int videoId);
}
