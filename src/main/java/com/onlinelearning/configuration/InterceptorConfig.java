package com.onlinelearning.configuration;

import com.onlinelearning.interceptor.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InterceptorConfig implements WebMvcConfigurer {

    final AuthenticationInterceptor authenticationInterceptor;
    final StudentInterceptor studentInterceptor;
    final TeacherInterceptor teacherInterceptor;
    final AdminInterceptor adminInterceptor;
    final NoCacheInterceptor noCacheInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/Profile", "/ChangePassword");
        registry.addInterceptor(studentInterceptor)
                .addPathPatterns("/MyCourse", "/Courses/LearnCourse/*", "/TakeQuiz", "/StartQuiz", "/Courses/EnrollCourse/*");
        registry.addInterceptor(teacherInterceptor)
                .addPathPatterns("/ManagerCourse", "/ManagerCourse/*", "/ManagerLesson/*"
                        , "/ManagerPdf/*", "/ManagerVideo/*", "/ManagerQuiz", "/ManagerQuiz/*", "/ManagerQuestion"
                        , "/ManagerQuestion/*");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/Courses/Delete/*", "/ViewLesson/*", "/ManagerUser/*");
        registry.addInterceptor(noCacheInterceptor)
                .addPathPatterns("/Login", "/Register", "/ForgotPassword");
    }
}
