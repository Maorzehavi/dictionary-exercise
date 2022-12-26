package com.example.eldarwebexercise.service.impl;

import com.example.eldarwebexercise.SystemException;
import com.example.eldarwebexercise.model.dto.request.EntryRequest;
import com.example.eldarwebexercise.model.dto.response.EntryExamplesResponse;
import com.example.eldarwebexercise.model.dto.response.EntryResponse;
import com.example.eldarwebexercise.model.entity.Entry;
import com.example.eldarwebexercise.repository.EntryRepository;
import com.example.eldarwebexercise.service.EntryService;
import com.example.eldarwebexercise.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;

    private final ExampleService exampleService;

    @Autowired
    public EntryServiceImpl(EntryRepository entryRepository, ExampleService exampleService) {
        this.entryRepository = entryRepository;
        this.exampleService = exampleService;
    }

    @Override
    public Optional<Long> getIdByValue(String value) {
        return entryRepository.getIdByValue(value);
    }

    @Override
    public Optional<EntryResponse> createEntry(EntryRequest entryRequest) {
        if (entryRepository.existsByValue(entryRequest.getValue()))
            throw new SystemException("Entry with value " + entryRequest.getValue() + " already exists");
        var entry = mapToEntry(entryRequest);
        var savedEntry = entryRepository.save(entry);
        return Optional.of(mapToEntryResponse(savedEntry));
    }

    @Override
    public Optional<EntryResponse> getEntry(Long id) {
        return entryRepository.findById(id).map(this::mapToEntryResponse);
    }

    @Override
    @Transactional
    public Optional<EntryExamplesResponse> getEntryExamples(Long id) {
        return entryRepository.findById(id).map(this::mapToEntryExamplesResponse);
    }

    @Override
    @Transactional
    @Modifying
    public Optional<EntryResponse> updateEntry(Long id, EntryRequest entryRequest) {
        return entryRepository.findById(id).map(entry -> {
            entry.setValue(entryRequest.getValue());
            entry.setDefinition(entryRequest.getDefinition());
            var savedEntry = entryRepository.save(entry);
            return mapToEntryResponse(savedEntry);
        });
    }

    @Override
    @Transactional
    @Modifying
    public Optional<EntryResponse> deleteEntry(Long id) {
        return entryRepository.findById(id).map(entry -> {
            entryRepository.delete(entry);
            return mapToEntryResponse(entry);
        });
    }

    @Override
    public Optional<List<EntryResponse>> getAllEntries() {
        return Optional.of(entryRepository.findAll()).map(entries -> {
            var entryResponses = new ArrayList<EntryResponse>();
            entries.forEach(entry -> entryResponses.add(mapToEntryResponse(entry)));
            return entryResponses;
        });
    }

    @Override
    @Transactional
    public Optional<List<EntryExamplesResponse>> getAllEntriesWithExamples() {
        return Optional.of(entryRepository.findAll()).map(entries -> {
            var entryExamplesResponses = new ArrayList<EntryExamplesResponse>();
            entries.forEach(entry -> entryExamplesResponses.add(mapToEntryExamplesResponse(entry)));
            return entryExamplesResponses;
        });
    }

    @Override
    public Entry mapToEntry(EntryRequest entryRequest) {
        return Entry.builder()
                .value(entryRequest.getValue())
                .definition(entryRequest.getDefinition())
                .build();
    }

    @Override
    public EntryResponse mapToEntryResponse(Entry entry) {
        return EntryResponse.builder()
                .id(entry.getId())
                .value(entry.getValue())
                .definition(entry.getDefinition())
                .build();
    }

    @Override
    public EntryExamplesResponse mapToEntryExamplesResponse(Entry entry) {
        var examples = entry.getExamples().stream()
                .map(exampleService::mapToExampleResponse)
                .collect(Collectors.toList());
        return EntryExamplesResponse.builder()
                .entry(mapToEntryResponse(entry))
                .examples(examples)
                .build();
    }

    @Override
    public Optional<Entry> getEntryEntity(Long entryId) {
        return entryRepository.findById(entryId);
    }
}
