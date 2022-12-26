package com.example.eldarwebexercise.service;

import com.example.eldarwebexercise.model.dto.request.EntryRequest;
import com.example.eldarwebexercise.model.dto.request.ExampleRequest;
import com.example.eldarwebexercise.model.dto.response.EntryExamplesResponse;
import com.example.eldarwebexercise.model.dto.response.EntryResponse;
import com.example.eldarwebexercise.model.dto.response.ExampleResponse;

import java.util.List;
import java.util.Optional;

public interface DictionaryService {

    Optional<EntryResponse> getEntry(String word);
    Optional<EntryExamplesResponse> getEntryWithExamples(String word);

    Optional<EntryResponse> createEntry(EntryRequest entryRequest);

    Optional<EntryResponse> updateEntry(String word, EntryRequest entryRequest);

    Optional<EntryResponse> deleteEntry(String word);

    Optional<List<EntryResponse>> getAllEntries();

    Optional<List<EntryExamplesResponse>> getAllEntriesWithExamples();

    Optional<ExampleResponse> createExample(String word, ExampleRequest exampleRequest);

    Optional<ExampleResponse> updateExample(String word,Long exampleId, ExampleRequest exampleRequest);

    Optional<ExampleResponse> deleteExample(String word, Long exampleId);

    Optional<List<ExampleResponse>> getAllExamples(String word);




}
