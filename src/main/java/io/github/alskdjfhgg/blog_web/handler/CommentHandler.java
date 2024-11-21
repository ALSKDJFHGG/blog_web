package io.github.alskdjfhgg.blog_web.handler;

import io.github.alskdjfhgg.blog_web.model.Article;
import io.github.alskdjfhgg.blog_web.model.Comment;
import io.github.alskdjfhgg.blog_web.model.User;
import io.github.alskdjfhgg.blog_web.repository.ArticleRepository;
import io.github.alskdjfhgg.blog_web.repository.CommentRepository;
import io.github.alskdjfhgg.blog_web.repository.UserRepository;
import io.github.alskdjfhgg.blog_web.security.Secured;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.HashMap;
import java.util.Map;

@Path("/comments")
public class CommentHandler {

    @Inject
    private CommentRepository commentRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ArticleRepository articleRepository;

    @POST
    @Path("/")
    @Secured({"user", "admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createComment(Comment comment, @Context SecurityContext securityContext) {
        User user = userRepository.findByID(Integer.valueOf(securityContext.getUserPrincipal().getName()));
        comment.setUser(user);
        Article article = articleRepository.findByID(comment.getArticleId());
        comment.setArticle(article);
        comment.setCreatedAt(System.currentTimeMillis() / 1000);
        commentRepository.create(comment);
        Map<String, Object> res = new HashMap<>();
        res.put("code", Response.Status.OK);
        return Response.status(Response.Status.OK).entity(res).build();
    }

    @POST
    @Path("/{id}")
    @Secured({"user", "admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteComment(@PathParam("id") Integer id) {
        commentRepository.delete(id);
        Map<String, Object> res = new HashMap<>();
        res.put("code", Response.Status.OK);
        return Response.status(Response.Status.OK).entity(res).build();
    }
}