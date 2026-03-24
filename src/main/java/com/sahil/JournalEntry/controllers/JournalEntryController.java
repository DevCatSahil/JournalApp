package com.sahil.JournalEntry.controllers;

import com.sahil.JournalEntry.model.JournalEntry;
import com.sahil.JournalEntry.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getJournalEntries(){
        return journalEntryService.getAllEntriesForUser();
    }

    @GetMapping("id/{id}")
    public Optional<JournalEntry> getJournalEntryById(@PathVariable ObjectId id){
        return journalEntryService.getEntryById(id);
    }

    @PostMapping
    public boolean newEntry(@RequestBody JournalEntry newEntry){
        journalEntryService.createNewEntry(newEntry);
        return true;
    }

    @PutMapping("{id}")
    public void updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
         journalEntryService.updateEntry(newEntry,id);

    }

    @DeleteMapping("{id}")
    public boolean deleteEntry(@PathVariable ObjectId id){
        return journalEntryService.DeleteEntry(id);
    }

}
