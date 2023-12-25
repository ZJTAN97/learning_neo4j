package com.neo4j.demo.course.enrolment;

import com.neo4j.demo.course.Course;
import com.neo4j.demo.security.User;

public record CourseEnrolment(User user, Course course) {}
