package io.github.alskdjfhgg.blog_web.model.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}