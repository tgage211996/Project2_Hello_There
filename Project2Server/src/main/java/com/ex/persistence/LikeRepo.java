package com.ex.persistence;

import com.ex.Models.Like;
import com.ex.Models.Post;
import com.ex.Models.User;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LikeRepo {
    private SessionFactory sessionFactory;

    /**
     * This method is setting up the sessionfactory for the Post repository
     * @param sessionFactory
     */
    @Autowired
    public LikeRepo(SessionFactory sessionFactory) {
        System.out.println("Creating Like Repo");
        this.sessionFactory = sessionFactory;
    }

    /**
     * This method is used to like to a post of a paticular user
     * @param post parameters of the post being liked
     * @param user parameters of the user liking the post
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void likePost(Post post, User user) {
        Session s = sessionFactory.getCurrentSession();
        Like likeCreation = new Like();
        likeCreation.setPost(post);
        likeCreation.setUser(user);
        s.save(likeCreation);
    }


  /**
   * This method is used to get the number of likes associated with a post
   * @param postId the parameter contains information need to identify a specific post
   * @return returns the number of likes
   */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public List<Like> getLikes(String postId){
        Session s = sessionFactory.getCurrentSession();
        Post desiredPost = new Post();
        desiredPost.setId(Integer.parseInt(postId));

        Query hql = s.createQuery("From Like Where post_id_ref =:identifier");
        hql.setParameter("identifier", desiredPost);
        return hql.list();
    }

  /**
   * This method is used to give the user the ability to take away a like given by the user
   * @param postId
   * @param userId
   */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void deleteLike(String postId, String userId){
      Session s = sessionFactory.getCurrentSession();
      Post post = new Post();
      post.setId(Integer.parseInt(postId));
      System.out.println(post);

      User user = new User();
      user.setId(Integer.parseInt(userId));
      System.out.println(user);

      Query hql = s.createQuery("Delete From Like Where post_id_ref = :postID and user_id_ref = :userID");
      hql.setParameter("postID", user.getId());
      hql.setParameter("userID", post.getId());
      hql.executeUpdate();


    }

}
