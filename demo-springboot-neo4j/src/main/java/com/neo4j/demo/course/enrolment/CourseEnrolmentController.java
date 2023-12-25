package com.neo4j.demo.course.enrolment;

import com.neo4j.demo.course.Course;
import com.neo4j.demo.course.CourseResponse;
import com.neo4j.demo.course.CourseService;
import com.neo4j.demo.lesson.LessonService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/enrolment")
@RequiredArgsConstructor
public class CourseEnrolmentController {

    private final CourseService courseService;

    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<CourseEnrolmentResponse> enrol(
            @RequestBody CourseEnrolmentRequest request, Principal principal) {

        CourseEnrolment enrolment = courseService.enroll(principal.getName(), request.courseIdentifier());

        CourseEnrolmentResponse response = new CourseEnrolmentResponse(
                enrolment.user().getName(), enrolment.user().getUsername(), enrolment.course());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> enrolments(Principal principal) {

        List<Course> courses = courseService.getAllEnrolledCoursesByUsername(principal.getName());

        List<CourseResponse> responses = courses.stream()
                .map(course -> CourseResponse.builder()
                        .identifier(course.getIdentifier())
                        .title(course.getTitle())
                        .teacher(course.getTeacher())
                        .lessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()))
                        .enroled(true)
                        .build())
                .toList();

        return ResponseEntity.ok(responses);
    }
}
