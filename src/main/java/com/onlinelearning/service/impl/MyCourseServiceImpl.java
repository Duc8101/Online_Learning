package com.onlinelearning.service.impl;

import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.response.StudentOrTeacherCoursesResponseDto;
import com.onlinelearning.model.pagination.PageUrl;
import com.onlinelearning.model.pagination.PagingRepo;
import com.onlinelearning.repository.CourseRepository;
import com.onlinelearning.service.MyCourseService;
import com.onlinelearning.service.common.BaseService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyCourseServiceImpl extends BaseService implements MyCourseService {

    final CourseRepository courseRepository;

    static final int MY_COURSE_PAGE = 6;

    @Override
    public ResponseBase myCourse(Integer page, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        int currentPage = page == null ? 1 : page;

        Page<StudentOrTeacherCoursesResponseDto> pageCourses = courseRepository.getStudentCourses(userId
                , PageRequest.of(currentPage - 1, MY_COURSE_PAGE));

        PageUrl pageUrl = PageUrl.builder()
                .firstUrl("/MyCourse")
                .preUrl(String.format("/MyCourse?page=%d", currentPage - 1))
                .nextUrl(String.format("/MyCourse?page=%d", currentPage + 1))
                .lastUrl(String.format("/MyCourse?page=%d", pageCourses.getTotalPages()))
                .build();

        PagingRepo<StudentOrTeacherCoursesResponseDto> pagination = new PagingRepo<StudentOrTeacherCoursesResponseDto>(currentPage, pageCourses.getTotalPages())
                .withContent(pageCourses.getContent()).withUrl(pageUrl);
        data.put("pagination", pagination);
        return new ResponseBase().withData(data).withViewName("my_course");
    }
}
