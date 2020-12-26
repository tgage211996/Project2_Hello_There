package com.ex.controller;

import com.ex.Models.Post;
import com.ex.Models.User;
import com.ex.persistence.PostRepo;
import com.ex.persistence.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
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

/**
 * this class is a Bean that handles incoming requests for information about Posts from the client side of the project
 */
@RestController
@RequestMapping(path="/post", method = { RequestMethod.GET, RequestMethod.POST })
public class PostController {

  static final Logger LOGGER = Logger.getLogger(PostController.class);


  @Autowired
    private PostRepo post;

    /**
     * This is a method that creates a post record in the database
     * @param request this is the http request parameters being sent to the server
     */
    @PostMapping(path = "/create")
    public void createPost(@RequestBody String request){
      LOGGER.debug("starting the process to create a Post");
        ObjectMapper om = new ObjectMapper();
        Post userData = null;

        try{
            userData = om.readValue(request, Post.class);
            post.createPost(userData.getAuthor(), userData.getContent(), Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), userData.getLocation(), userData.isFlagged());
        }catch(JsonProcessingException e){
          LOGGER.error("wrong information could have been given to properly create post");
            e.printStackTrace();
        }

    }

    /**
     * This method gets all posts that exist within the database
     * @return All posts that exists within the database
     */
    @GetMapping(path ="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Post>> getPosts(){
      LOGGER.info("starting the process of retrieving all posts within the database");
      LOGGER.error("something may be wrong with how the data was retrieved or something is wrong with database");
      return new ResponseEntity<>(post.getAllPosts(), HttpStatus.OK);


    }



    /**
     * This method gets all posts made by a certain user.
     * @param username this is the http request parameters being sent to the server
     * @return returns all posts made by a certain user.
     * @throws JsonProcessingException
     */
    @GetMapping(path="/userPosts", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Post>> getUPosts(@RequestParam String username) throws JsonProcessingException {

      LOGGER.info("starting the process of retrieving all posts for a user");
      LOGGER.error("something may be wrong with how the data was retrieved or something is wrong with database");

        System.out.println("Request: " + username);

        return new ResponseEntity<>(post.getUserPosts(username), HttpStatus.OK);

    }

  /**
   * this method is used to update a posts information
   * @param request
   */
  @PostMapping(path = "/update")
    public void updatePost(@RequestBody String request){

    LOGGER.info("starting the process of updating post information within the database");
    LOGGER.error("something may be wrong with how the data was retrieved or something is wrong with database");
        ObjectMapper om = new ObjectMapper();
        Post userData = null;

        post.updatePost(3,"Die by the hands of Johnny Silverhand");

    }

  /**
   * this method is used to flag a post
   */
  @PostMapping(path = "/flag")
    public void flagPost(){

        post.flag(3);

    }
}
