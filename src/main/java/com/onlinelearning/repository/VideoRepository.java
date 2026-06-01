package com.onlinelearning.repository;

import com.onlinelearning.model.dto.response.VideoListResponseDto;
import com.onlinelearning.model.entity.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

    @Query("SELECT new com.onlinelearning.model.dto.response.VideoListResponseDto(v.videoId, v.videoName, v.fileVideo, v.lesson.lessonId) "
            + "from Video v\n"
            + "where v.lesson.course.courseId = :courseId")
    List<VideoListResponseDto> getVideosOfCourse(int courseId, Pageable pageable);
}
