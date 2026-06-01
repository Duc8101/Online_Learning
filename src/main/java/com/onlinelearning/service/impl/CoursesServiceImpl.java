package com.onlinelearning.service.impl;

import com.onlinelearning.mapper.LessonMapper;
import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.*;
import com.onlinelearning.model.entity.Course;
import com.onlinelearning.model.entity.EnrollCourse;
import com.onlinelearning.model.entity.User;
import com.onlinelearning.model.enumeration.UserRole;
import com.onlinelearning.model.pagination.PageUrl;
import com.onlinelearning.model.pagination.PagingRepo;
import com.onlinelearning.repository.*;
import com.onlinelearning.service.CoursesService;
import com.onlinelearning.service.common.BaseService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CoursesServiceImpl extends BaseService implements CoursesService {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final EnrollCourseRepository enrollCourseRepository;
    private final LessonMapper lessonMapper;
    private final VideoRepository videoRepository;

    private static final int COURSES_PAGE = 6;

    @Override
    public ResponseBase list(Integer categoryId, Boolean orderBy, Integer page, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        Long studentId;

        if (user == null || user.getRoleId() != UserRole.STUDENT.getValue()) {
            studentId = null;
        } else {
            studentId = user.getUserId();
        }

        Map<String, Object> data = new HashMap<>();
        int currentPage = page == null ? 1 : page;

        List<CategoryListResponseDto> categories = categoryRepository.getAllCategories();
        Page<AllCoursesResponseDto> pageCourse = courseRepository.getAllCourses(categoryId, orderBy, studentId
                , PageRequest.of(currentPage - 1, COURSES_PAGE));

        PageUrl pageUrl = PageUrl.builder().preUrl("/Courses")
                .nextUrl("/Courses")
                .firstUrl("/Courses")
                .lastUrl("/Courses")
                .build();

        if (orderBy == null) {
            // if not choose category
            if (categoryId == null) {
                pageUrl.setPreUrl(pageUrl.getPreUrl() + String.format("?page=%d", currentPage - 1));
                pageUrl.setNextUrl(pageUrl.getNextUrl() + String.format("?page=%d", currentPage + 1));
                pageUrl.setLastUrl(pageUrl.getLastUrl() + String.format("?page=%d", pageCourse.getTotalPages()));
            } else {
                pageUrl.setPreUrl(pageUrl.getPreUrl() + String.format("?categoryId=%d&page=%d", categoryId, currentPage - 1));
                pageUrl.setNextUrl(pageUrl.getNextUrl() + String.format("?categoryId=%d&page=%d", categoryId, currentPage + 1));
                pageUrl.setFirstUrl(pageUrl.getFirstUrl() + String.format("?categoryId=%d", categoryId));
                pageUrl.setLastUrl(pageUrl.getLastUrl() + String.format("?categoryId=%d&page=%d", categoryId, pageCourse.getTotalPages()));
            }
        } else {
            // if not choose category
            if (categoryId == null) {
                pageUrl.setPreUrl(pageUrl.getPreUrl() + String.format("?orderBy=%b&page=%d", orderBy, currentPage - 1));
                pageUrl.setNextUrl(pageUrl.getNextUrl() + String.format("?orderBy=%b&page=%d", orderBy, currentPage + 1));
                pageUrl.setFirstUrl(pageUrl.getFirstUrl() + String.format("?orderBy=%b", orderBy));
                pageUrl.setLastUrl(pageUrl.getLastUrl() + String.format("?orderBy=%b&page=%d", orderBy, pageCourse.getTotalPages()));
            } else {
                pageUrl.setPreUrl(pageUrl.getPreUrl() + String.format("?categoryId=%d&orderBy=%b&page=%d", categoryId, orderBy, currentPage - 1));
                pageUrl.setNextUrl(pageUrl.getNextUrl() + String.format("?categoryId=%d&orderBy=%b&page=%d", categoryId, orderBy, currentPage + 1));
                pageUrl.setFirstUrl(pageUrl.getFirstUrl() + String.format("?categoryId=%d&orderBy=%b", categoryId, orderBy));
                pageUrl.setLastUrl(pageUrl.getLastUrl() + String.format("?categoryId=%d&orderBy=%b&page=%d", categoryId, orderBy, pageCourse.getTotalPages()));
            }
        }

        List<AllCoursesResponseDto> courses = pageCourse.getContent();

        // ------------------------- set pagination ----------------------------
        PagingRepo<AllCoursesResponseDto> pagination = new PagingRepo<AllCoursesResponseDto>(currentPage, pageCourse.getTotalPages())
                .withContent(courses).withUrl(pageUrl);

        // ------------------------- set data ----------------------------
        data.put("pagination", pagination);
        data.put("categories", categories);
        data.put("orderBy", orderBy);
        data.put("categoryId", categoryId);
        setValueForHeaderFooter(data, true, true, true, true);
        return new ResponseBase("courses/list", data);
    }

    @Override
    public ResponseBase detail(int courseId, HttpSession session) {
        UserProfileResponseDto user = (UserProfileResponseDto) session.getAttribute("user");
        Long studentId;

        if (user == null || user.getRoleId() != UserRole.STUDENT.getValue()) {
            studentId = null;
        } else {
            studentId = user.getUserId();
        }

        Map<String, Object> data = new HashMap<>();
        CourseDetailResponseDto course = courseRepository.getCourseDetail(courseId, studentId);
        if (course == null) {
            return new ResponseBase("redirect:/Courses", data);
        }

        setValueForHeaderFooter(data, true, true, true, true);

        List<LessonListForCourseDetailResponseDto> lessons = lessonRepository.getLessonsForCourseDetail(courseId);
        course.setLessons(lessons);
        data.put("course", course);
        return new ResponseBase("courses/detail", data);
    }

    @Override
    public ResponseBase enrollCourse(int courseId, long studentId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        Course course = courseRepository.getCourseByCourseId(courseId);
        if (course == null) {
            data.put("error", "Course not found");
            return new ResponseBase("shared/error", data);
        }

        User student = userRepository.findById(studentId).orElse(null);
        if (student == null) {
            data.put("error", "Student not found");
            return new ResponseBase("shared/error", data);
        }

        // if student does not enroll course
        if (!enrollCourseRepository.checkStudentEnrollCourse(courseId, studentId)) {
            EnrollCourse enrollCourse = EnrollCourse.builder()
                    .course(course).student(student)
                    .build();
            enrollCourseRepository.save(enrollCourse);
        }
        return new ResponseBase("redirect:/MyCourse", data);
    }

    @Override
    public ResponseBase learnCourse(int courseId, String fileVideo, String name, String filePdf, Integer lessonId, Integer videoId, Integer pdfId, long userId) {
        Map<String, Object> data = new HashMap<>();

        Boolean check = courseRepository.checkStudentEnrollCourse(courseId, userId).orElse(null);
        if (check == null) {
            setValueForHeaderFooter(data, true, true, true, true);
            data.put("error", "Course not found");
            return new ResponseBase("shared/error", data);
        }

        // if student does not enroll course
        if (!check) {
            return new ResponseBase("redirect:/MyCourse", data);
        }

        List<LessonListForLearnCourseResponseDto> lessons = lessonMapper.toLessonListForLearnCourseResponseDTOs(lessonRepository.getLessonsForLearnCourse(courseId));
        // if course doesn't have lesson
        if (lessons.isEmpty()) {
            return new ResponseBase("redirect:/MyCourse", data);
        }

        setValueForHeaderFooter(data, false, true, false, false);

        // if start to learn course
        if (fileVideo == null && filePdf == null) {
            List<VideoListResponseDto> videos = videoRepository.getVideosOfCourse(courseId, PageRequest.of(0, 1));
            if (!videos.isEmpty()) {
                fileVideo = videos.getFirst().getFileVideo();
                name = videos.getFirst().getVideoName();
                videoId = videos.getFirst().getVideoId();
                lessonId = videos.getFirst().getLessonId();
            }
        }

        data.put("lessons", lessons);
        data.put("filePdf", filePdf == null ? "" : filePdf);
        data.put("fileVideo", fileVideo == null ? "" : fileVideo);
        data.put("name", name == null ? "" : name);
        data.put("lessonId", lessonId == null ? 0 : lessonId);
        data.put("courseId", courseId);
        data.put("videoId", videoId);
        data.put("pdfId", pdfId);
        return new ResponseBase("courses/learn_course", data);
    }

    @Override
    public ResponseBase delete(int courseId) {
        Map<String, Object> data = new HashMap<>();

        CheckLessonAndEnrollCourseExist check = courseRepository.checkLessonAndEnrollCourseExist(courseId);
        if (check != null) {
            if (!check.isLessonExist() && !check.isEnrollCourseExist()) {
                courseRepository.deleteById(courseId);
            } else {
                courseRepository.setCourseDeleted(courseId);
            }
        }
        return new ResponseBase("redirect:/Courses", data);
    }
}
