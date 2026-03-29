package com.sahil.JournalEntry.Repository;

import com.sahil.JournalEntry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getAllSAUsers(){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is("sahil"));
        return mongoTemplate.find(query, User.class);
    }
}
