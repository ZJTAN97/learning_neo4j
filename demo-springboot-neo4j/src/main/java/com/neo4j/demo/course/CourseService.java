package com.neo4j.demo.course;

import com.neo4j.demo.course.enrolment.CourseEnrolment;
import com.neo4j.demo.user.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findCourseByIdentifier(String identifier) {
        return courseRepository
                .findCourseByIdentifier(identifier)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public Boolean getEnrolmentStatus(String username, String courseIdentifier) {
        return userRepository.findEnrolmentStatus(username, courseIdentifier);
    }

    public CourseEnrolment enroll(String username, String courseIdentifier) {
        return userRepository.createEnrolmentRelationship(username, courseIdentifier);
    }

    public List<Course> getAllEnrolledCoursesByUsername(String username) {
        return courseRepository.findAllEnrolledCoursesByUsername(username);
    }
}
