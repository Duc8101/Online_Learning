package com.onlinelearning.service.impl;

import com.onlinelearning.mapper.CourseMapper;
import com.onlinelearning.model.ResponseBase;
import com.onlinelearning.model.dto.request.CourseCreateUpdateRequestDto;
import com.onlinelearning.model.dto.response.CategoryListResponseDto;
import com.onlinelearning.model.dto.response.CheckLessonAndEnrollCourseExist;
import com.onlinelearning.model.dto.response.StudentOrTeacherCoursesResponseDto;
import com.onlinelearning.model.entity.Category;
import com.onlinelearning.model.entity.Course;
import com.onlinelearning.model.entity.User;
import com.onlinelearning.model.pagination.PageUrl;
import com.onlinelearning.model.pagination.PagingRepo;
import com.onlinelearning.repository.*;
import com.onlinelearning.service.ManagerCourseService;
import com.onlinelearning.service.common.BaseService;
import com.onlinelearning.util.DataUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerCourseServiceImpl extends BaseService implements ManagerCourseService {

    final CourseRepository courseRepository;
    final CategoryRepository categoryRepository;
    final UserRepository userRepository;
    final LessonRepository lessonRepository;
    final EnrollCourseRepository enrollCourseRepository;
    final CourseMapper courseMapper;

    static final int MANAGER_COURSE_LIST_PAGE = 3;

    @Override
    public ResponseBase list(int page, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);

        Page<StudentOrTeacherCoursesResponseDto> pageCourses = courseRepository.getTeacherCourses(userId
                , PageRequest.of(page - 1, MANAGER_COURSE_LIST_PAGE));

        PageUrl pageUrl = PageUrl.builder()
                .firstUrl("/ManagerCourse")
                .nextUrl(String.format("/ManagerCourse?page=%d", page + 1))
                .preUrl(String.format("/ManagerCourse?page=%d", page - 1))
                .lastUrl(String.format("/ManagerCourse?page=%d", pageCourses.getTotalPages()))
                .build();

        PagingRepo<StudentOrTeacherCoursesResponseDto> pagination = new PagingRepo<StudentOrTeacherCoursesResponseDto>(page, pageCourses.getTotalPages())
                .withContent(pageCourses.getContent()).withUrl(pageUrl);

        data.put("pagination", pagination);
        return new ResponseBase("manager_course/list", data);
    }

    @Override
    public ResponseBase create() {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        setDataCategories(data);
        return new ResponseBase("manager_course/list", data);
    }

    void setDataCategories(Map<String, Object> data) {
        List<CategoryListResponseDto> categories = categoryRepository.getAllCategories();
        data.put("categories", categories);
    }

    @Override
    public ResponseBase create(CourseCreateUpdateRequestDto DTO, long userId) {
        Map<String, Object> data = new HashMap<>();
        setValueForHeaderFooter(data, true, true, true, true);
        setDataCategories(data);

        if (courseRepository.isCourseExist(DTO.getCourseName().trim(), DTO.getCategoryId())) {
            data.put("error", "Course already exists");
            return new ResponseBase("manager_course/create", data);
        }

        User creator = userRepository.findById(userId).orElse(null);
        Category category = categoryRepository.findById(DTO.getCategoryId()).orElse(null);

        Course course = courseMapper.toCourse(DTO);
        course.setCategory(category);
        course.setCreator(creator);
        courseRepository.save(course);

        data.put("success", "Create successful");
        return new ResponseBase("manager_course/create", data);
    }

    @Override
    public ResponseBase update(int courseId, long userId) {
        Map<String, Object> data = new HashMap<>();
        StudentOrTeacherCoursesResponseDto course = courseRepository.getTeacherCourse(userId, courseId);
        if (course == null) {
            return new ResponseBase("redirect:/ManagerCourse", data);
        }

        setValueForHeaderFooter(data, true, true, true, true);
        setDataCategories(data);

        data.put("course", course);
        return new ResponseBase("manager_course/update", data);
    }

    @Override
    public ResponseBase update(int courseId, CourseCreateUpdateRequestDto DTO, long userId) {
        Map<String, Object> data = new HashMap<>();
        StudentOrTeacherCoursesResponseDto course = courseRepository.getTeacherCourse(userId, courseId);
        if (course == null) {
            return new ResponseBase("redirect:/ManagerCourse", data);
        }

        setValueForHeaderFooter(data, true, true, true, true);
        setDataCategories(data);
        data.put("course", course);

        if (courseRepository.isCourseExist(DTO.getCourseName().trim(), DTO.getCategoryId(), courseId)) {
            data.put("error", "Course already exists");
            return new ResponseBase("manager_course/update", data);
        }

        course.setCourseName(DTO.getCourseName());
        course.setDescription(DataUtil.trimToNull(DTO.getDescription()));
        course.setCategoryId(DTO.getCategoryId());
        course.setImage(DTO.getImage());
        courseRepository.updateCourseInfo(course.getCourseName(), course.getDescription(), course.getCategoryId()
                , course.getImage(), Instant.now(), course.getCourseId());

        data.put("course", course);
        data.put("success", "Update successful");
        return new ResponseBase("manager_course/update", data);
    }

    @Override
    public ResponseBase delete(int courseId, long userId) {
        Map<String, Object> data = new HashMap<>();

        CheckLessonAndEnrollCourseExist check = courseRepository.checkLessonAndEnrollCourseExist(courseId, userId);
        if (check != null) {
            if (!check.isLessonExist() && !check.isEnrollCourseExist()) {
                courseRepository.deleteById(courseId);
            } else {
                courseRepository.setCourseDeleted(courseId);
            }
        }
        return new ResponseBase("redirect:/ManagerCourse", data);
    }
}
