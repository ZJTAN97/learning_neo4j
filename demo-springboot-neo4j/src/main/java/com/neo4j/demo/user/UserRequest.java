package com.neo4j.demo.user;

import lombok.Builder;

@Builder(toBuilder = true)
public record UserRequest(String name, String username, String password, String roles) {}
