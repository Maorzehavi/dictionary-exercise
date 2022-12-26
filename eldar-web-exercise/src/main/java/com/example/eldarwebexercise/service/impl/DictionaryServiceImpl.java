package com.example.eldarwebexercise.service.impl;

import com.example.eldarwebexercise.SystemException;
import com.example.eldarwebexercise.model.dto.request.EntryRequest;
import com.example.eldarwebexercise.model.dto.request.ExampleRequest;
import com.example.eldarwebexercise.model.dto.response.EntryExamplesResponse;
import com.example.eldarwebexercise.model.dto.response.EntryResponse;
import com.example.eldarwebexercise.model.dto.response.ExampleResponse;
import com.example.eldarwebexercise.service.DictionaryService;
import com.example.eldarwebexercise.service.EntryService;
import com.example.eldarwebexercise.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DictionaryServiceImpl implements DictionaryService {

    private final EntryService entryService;

    private final ExampleService exampleService;

    @Autowired
    public DictionaryServiceImpl(EntryService entryService, ExampleService exampleService) {
        this.entryService = entryService;
        this.exampleService = exampleService;
    }


    @Override
    public Optional<EntryResponse> getEntry(String word) {
        long id = entryService.getIdByValue(word).orElseThrow(() -> new SystemException("Entry with value " + word + " not found"));
        return entryService.getEntry(id);
    }

    @Override
    public Optional<EntryExamplesResponse> getEntryWithExamples(String word) {
        long id = entryService.getIdByValue(word).orElseThrow(() -> new SystemException("Entry with value " + word + " not found"));
        return entryService.getEntryExamples(id);
    }

    @Override
    public Optional<EntryResponse> createEntry(EntryRequest entryRequest) {
        return entryService.createEntry(entryRequest);
    }

    @Override
    public Optional<EntryResponse> updateEntry(String word, EntryRequest entryRequest) {
        long id = entryService.getIdByValue(word).orElseThrow(() -> new SystemException("Entry with value " + word + " not found"));
        return entryService.updateEntry(id, entryRequest);
    }

    @Override
    public Optional<EntryResponse> deleteEntry(String word) {
        long id = entryService.getIdByValue(word).orElseThrow(() -> new SystemException("Entry with value " + word + " not found"));
        return entryService.deleteEntry(id);
    }

    @Override
    public Optional<List<EntryResponse>> getAllEntries() {
        return entryService.getAllEntries();
    }

    @Override
    public Optional<List<EntryExamplesResponse>> getAllEntriesWithExamples() {
        return entryService.getAllEntriesWithExamples();
    }

    @Override
    public Optional<ExampleResponse> createExample(String word, ExampleRequest exampleRequest) {
        long id = entryService.getIdByValue(word).orElseThrow(() -> new SystemException("Entry with value " + word + " not found"));
//        assert id == exampleRequest.getEntryId();
        return exampleService.createExample(id,exampleRequest);
    }

    @Override
    public Optional<ExampleResponse> updateExample(String word, Long exampleId, ExampleRequest exampleRequest) {
        long id = entryService.getIdByValue(word).orElseThrow(() -> new SystemException("Entry with value " + word + " not found"));
//        assert id == exampleRequest.getEntryId();
        return exampleService.updateExample(exampleId, id,exampleRequest);
    }

    @Override
    public Optional<ExampleResponse> deleteExample(String word, Long exampleId) {
        long id = entryService.getIdByValue(word).orElseThrow(() -> new SystemException("Entry with value " + word + " not found"));
        return exampleService.deleteExample(exampleId);
    }

    @Override
    public Optional<List<ExampleResponse>> getAllExamples(String word) {
        long id = entryService.getIdByValue(word).orElseThrow(() -> new SystemException("Entry with value " + word + " not found"));
        return exampleService.getAllByEntryId(id);
    }
}
