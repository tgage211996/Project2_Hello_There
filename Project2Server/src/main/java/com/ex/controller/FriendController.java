package com.ex.controller;

import com.ex.Models.Friend;

import com.ex.persistence.FriendRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * this class is a Bean that handles incoming requests for Friend information from the client side of the project
 */
@RestController
@RequestMapping(path="/friend", method = { RequestMethod.GET, RequestMethod.POST })
public class FriendController {

  static final Logger LOGGER = Logger.getLogger(FriendController.class);



  @Autowired
    private FriendRepo friend;


    /**]
     * this is a mapping to a method that creates friend record in the connected database
     */
    @PostMapping(path = "/create")
    public void createFriend(@RequestBody String request) {
      LOGGER.debug("started establishing friendship");


        ObjectMapper om = new ObjectMapper();
        Friend friendData = null;

        try{
            friendData = om.readValue(request, Friend.class);
            friend.addFriend(friendData.getUser(), friendData.getFriend());
        }catch(JsonProcessingException e){
          LOGGER.error("information given may be incorrect or the friendship relation already exists, or certain user's dont exist");
            e.printStackTrace();
        }
    }


    /**
     * this is a mapping that calls a method that retrieves all of a user's friends from the database
     * @param userId this is the http request parameters being sent to the server
     * @return returns a list of friendship records
     * @throws JsonProcessingException
     */
    @GetMapping(path ="/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Friend>> getAllFriends(@RequestParam String userId)  {
      LOGGER.debug("started getting user's friends");
      LOGGER.error("information given may be incorrect or the friendship relation already exists, or certain user's dont exist");

        return new ResponseEntity<>(friend.getAllF(userId), HttpStatus.OK);
    }



    }
