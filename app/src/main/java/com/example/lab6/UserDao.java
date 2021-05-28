package com.example.lab6;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    public void addFeedback( User u);

    @Query("select * from user")
    public List<User> getAll();

    @Query("delete from user where id=:id")
    public void deleteFeedback(int id);

    @Query("update user SET name = :name, rating = :rating, comment =:comment  where id =:id")
    void editFeedback(String name, Float rating, String comment, int id);

    @Query("select id from user where name=:name")
    int getId(String name);

    @Query("select rating from user where id=:id")
    Float getRating(int id);

    @Query("select comment from user where id=:id")
    String getComment(int id);
}
