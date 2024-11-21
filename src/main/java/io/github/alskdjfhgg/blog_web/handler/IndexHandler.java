package io.github.alskdjfhgg.blog_web.handler;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
public class IndexHandler {

    @GET
    public String index() {
        return "Hello World!";
    }
}