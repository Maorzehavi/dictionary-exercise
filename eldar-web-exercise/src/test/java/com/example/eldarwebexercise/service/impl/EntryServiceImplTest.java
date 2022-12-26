package com.example.eldarwebexercise.service.impl;

import com.example.eldarwebexercise.model.dto.request.EntryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EntryServiceImplTest {

    @Autowired
    private EntryServiceImpl entryService;

    @Test
    void createEntry() {
        System.out.println(entryService.createEntry(EntryRequest.builder()
                        .value("test")
                        .definition("test")
                .build()));
    }

    @Test
    void getAllEntries() {
        System.out.println(entryService.getAllEntries());
    }

    @Test
    void getAllEntriesWithExamples() {
        System.out.println(entryService.getAllEntriesWithExamples());
    }
}