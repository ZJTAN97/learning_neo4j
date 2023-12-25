package com.neo4j.demo.course;

import com.neo4j.demo.lesson.LessonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<CourseResponse>> findAll() {

        List<Course> courses = courseService.findAll();

        List<CourseResponse> response = courses.stream()
                .map(course -> CourseResponse.builder()
                        .identifier(course.getIdentifier())
                        .title(course.getTitle())
                        .teacher(course.getTeacher())
                        .lessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()))
                        .build())
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<CourseResponse> findCourseByIdentifier(@PathVariable("identifier") String identifier) {

        Course course = courseService.findCourseByIdentifier(identifier);

        CourseResponse response = CourseResponse.builder()
                .identifier(course.getIdentifier())
                .title(course.getTitle())
                .teacher(course.getTeacher())
                .lessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()))
                .build();

        return ResponseEntity.ok(response);
    }
}
