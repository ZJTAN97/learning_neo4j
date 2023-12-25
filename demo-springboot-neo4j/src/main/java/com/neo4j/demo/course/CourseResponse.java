package com.neo4j.demo.course;

import com.neo4j.demo.lesson.Lesson;
import java.util.List;
import lombok.Builder;

@Builder(toBuilder = true)
public record CourseResponse(String identifier, String title, String teacher, List<Lesson> lessons, Boolean enroled) {}
