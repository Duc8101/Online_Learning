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

    @Query("SELECT new com.onlinelearning.model.dto.response.VideoListResponseDto(v.videoId, v.videoName, v.fileVideo, v.lesson.lessonId) "
            + "from Video v\n"
            + "where v.lesson.course.courseId = :courseId")
    List<VideoListResponseDto> getVideosOfCourse(int courseId, Pageable pageable);

    @Query("select new com.onlinelearning.model.dto.response.VideoUpdateResponseDto(v.videoId, v.videoName, v.fileVideo, v.lesson.lessonId, v.lesson.course.courseId) from Video v where v.videoId = :videoId and v.lesson.course.deleted = false")
    VideoUpdateResponseDto getVideo(int videoId);

    @Transactional
    @Modifying
    @Query("update Video v set v.videoName = :videoName, v.fileVideo = :fileVideo, v.updatedAt = :updatedAt where v.videoId = :videoId")
    void updateVideo(String videoName, String fileVideo, Instant updatedAt, int videoId);

    @Query("SELECT exists (select 1 from Video v where v.videoId = :videoId and v.lesson.course.deleted = false)")
    boolean isVideoExist(int videoId);
}
