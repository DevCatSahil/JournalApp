package com.sahil.JournalEntry.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;

@Document(collection = "journal_Entry")
@Data
public class JournalEntry {

    @Id
    private ObjectId id;
    private String title;
    private String description;
    private LocalDateTime date;
}
