package com.sahil.JournalEntry.Repository;

import com.sahil.JournalEntry.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

     User findByUsername(String username);

}
