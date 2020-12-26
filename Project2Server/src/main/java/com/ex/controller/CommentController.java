package com.ex.controller;

import com.ex.Models.Comment;
import com.ex.persistence.CommentRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path="/comment", method = { RequestMethod.GET, RequestMethod.POST })
public class CommentController {
  static final Logger LOGGER = Logger.getLogger(CommentController.class);

    @Autowired
    private CommentRepo comment;

  /**
   * This method is calling the method to create a comment on a user's post
   * @param request contains information need to create a comment
   */
  @PostMapping(path = "/create")
    public void createComment(@RequestBody String request){
    LOGGER.debug("started creating a comment");

        ObjectMapper om = new ObjectMapper();
        Comment data = null;
        try {
            data = om.readValue(request, Comment.class);
            comment.createComment(data.getAssociatedPost(), data.getAuthor(), data.getContent(),Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
            LOGGER.error("wrong data was possibly entered");
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

  /**
   * This method is used to all the posts assiciated with a post
   * @param postId the id of the post in focus
   * @return returns a list of comment objects
   */
    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> getPostComments(@RequestParam String postId) {
      LOGGER.debug("started fetching comments");
      LOGGER.error("incorrect information was given");
        return new ResponseEntity<>(comment.getPostComments(postId), HttpStatus.OK);
    }



}
