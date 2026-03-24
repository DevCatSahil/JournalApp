package com.sahil.JournalEntry.model;

import lombok.Data;

import java.util.List;

@Data
public class Quote{
    private String quote;
    private String author;
    private String work;
    private List<String> categories;
}
