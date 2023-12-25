package com.neo4j.demo.course;

import java.util.List;
import java.util.Optional;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface CourseRepository extends Neo4jRepository<Course, Long> {
    Optional<Course> findCourseByIdentifier(String identifier);

    @Query("MATCH (:User {username: $username})-[:ENROLLED_IN]->(courses:Course) RETURN courses")
    List<Course> findAllEnrolledCoursesByUsername(String username);
}
