package com.onlinelearning.configuration;

import com.onlinelearning.interceptor.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;
    private final StudentInterceptor studentInterceptor;
    private final TeacherInterceptor teacherInterceptor;
    private final AdminInterceptor adminInterceptor;
    private final NoCacheInterceptor noCacheInterceptor;

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
