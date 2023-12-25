package com.neo4j.demo.course.enrolment;

import com.neo4j.demo.course.Course;

public record CourseEnrolmentResponse(String name, String username, Course course) {}
