package com.sahil.JournalEntry.service;

import com.sahil.JournalEntry.Repository.JournalEntryRepository;
import com.sahil.JournalEntry.model.JournalEntry;
import com.sahil.JournalEntry.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public List<JournalEntry> getAllEntriesForUser(){
        return userService.getCurrentUser().getJournalEntries();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void createNewEntry(JournalEntry journalEntry){
        User user =  userService.getCurrentUser();
        journalEntry.setDate(LocalDateTime.now());
         JournalEntry entry =journalEntryRepository.save(journalEntry);
         user.getJournalEntries().add(entry);
         userService.createNewUser(user);
    }

    public void updateEntry(JournalEntry newEntry, ObjectId id){
        JournalEntry entry= findEntry(id);
        if (entry!=null){
            entry.setTitle(newEntry.getTitle());
            entry.setDescription(newEntry.getDescription());
            journalEntryRepository.save(entry);
        }

    }

        public boolean DeleteEntry(ObjectId id){
            JournalEntry entry= findEntry(id);
            if(entry!=null){
                journalEntryRepository.delete(entry);
                User user =  userService.getCurrentUser();
                user.getJournalEntries().removeIf(x -> x.getId().equals(id));
                return userService.saveUser(user);
            }else{
                return false;
            }

        }

        public JournalEntry findEntry(ObjectId id){
            User user = userService.getCurrentUser();
            return user.getJournalEntries()
                    .stream()
                    .filter(x -> x.getId().equals(id))
                    .findFirst()
                    .orElse(null);

        }

}
