package com.neo4j.demo.user;

import lombok.Builder;

@Builder(toBuilder = true)
public record UserResponse(String name, String username, String roles) {}
