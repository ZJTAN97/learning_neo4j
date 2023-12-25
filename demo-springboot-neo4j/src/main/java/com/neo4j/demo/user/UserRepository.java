package com.neo4j.demo.user;

import com.neo4j.demo.course.enrolment.CourseEnrolment;
import com.neo4j.demo.security.User;
import java.util.Optional;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface UserRepository extends Neo4jRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    @Query(
            "MATCH (user: User), (course: Course) WHERE user.username = $username AND course.identifier = $courseIdentifier "
                    + "RETURN EXISTS((user)-[:ENROLLED_IN]->(course))")
    Boolean findEnrolmentStatus(String username, String courseIdentifier);

    @Query(
            "MATCH (user: User), (course: Course) WHERE user.username = $username AND course.identifier = $courseIdentifier "
                    + "CREATE (user)-[:ENROLLED_IN]->(course) RETURN user, course")
    CourseEnrolment createEnrolmentRelationship(String username, String courseIdentifier);
}
