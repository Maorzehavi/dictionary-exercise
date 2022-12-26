package com.example.eldarwebexercise.service;

import com.example.eldarwebexercise.model.dto.request.EntryRequest;
import com.example.eldarwebexercise.model.dto.response.EntryExamplesResponse;
import com.example.eldarwebexercise.model.dto.response.EntryResponse;
import com.example.eldarwebexercise.model.dto.response.ExampleResponse;
import com.example.eldarwebexercise.model.entity.Entry;
import com.example.eldarwebexercise.model.entity.Example;

import java.util.List;
import java.util.Optional;

public interface EntryService {

    Optional<Long> getIdByValue(String value);

    Optional<EntryResponse> createEntry(EntryRequest entryRequest);

    Optional<EntryResponse> getEntry(Long id);

    Optional<EntryExamplesResponse> getEntryExamples(Long id);

    Optional<EntryResponse> updateEntry(Long id, EntryRequest entryRequest);

    Optional<EntryResponse> deleteEntry(Long id);

    Optional<List<EntryResponse>> getAllEntries();

    Optional<List<EntryExamplesResponse>> getAllEntriesWithExamples();

    // mapping methods

    Entry mapToEntry(EntryRequest entryRequest);

    EntryResponse mapToEntryResponse(Entry entry);

    EntryExamplesResponse mapToEntryExamplesResponse(Entry entry);


    Optional<Entry> getEntryEntity(Long entryId);
}
