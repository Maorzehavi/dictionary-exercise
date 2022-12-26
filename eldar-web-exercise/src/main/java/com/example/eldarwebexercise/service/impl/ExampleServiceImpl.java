package com.example.eldarwebexercise.service.impl;

import com.example.eldarwebexercise.model.dto.request.ExampleRequest;
import com.example.eldarwebexercise.model.dto.response.ExampleResponse;
import com.example.eldarwebexercise.model.entity.Example;
import com.example.eldarwebexercise.repository.ExampleRepository;
import com.example.eldarwebexercise.service.EntryService;
import com.example.eldarwebexercise.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository exampleRepository;

    private final EntryService entryService;

    @Autowired
    public ExampleServiceImpl(ExampleRepository exampleRepository, @Lazy EntryService entryService) {
        this.exampleRepository = exampleRepository;
        this.entryService = entryService;
    }

    @Override
    public Optional<ExampleResponse> createExample(Long entryId, ExampleRequest exampleRequest) {
        var entry = entryService.getEntryEntity(entryId).orElseThrow(() -> new RuntimeException("Entry with id " + entryId + " not found"));
        var example = mapToExample(exampleRequest);
        example.setEntry(entry);
        var savedExample = exampleRepository.save(example);
        return Optional.of(mapToExampleResponse(savedExample));
    }

    @Override
    public Optional<ExampleResponse> updateExample(Long exampleId, Long entryId, ExampleRequest exampleRequest) {
        var example = getExampleEntity(exampleId).orElseThrow(() -> new RuntimeException("Example with id " + exampleId + " not found"));
        var entry = entryService.getEntryEntity(entryId).orElseThrow(() -> new RuntimeException("Entry with id " + entryId + " not found"));
        example.setEntry(entry);
        example.setSentence(exampleRequest.getSentence());
        var savedExample = exampleRepository.save(example);
        return Optional.of(mapToExampleResponse(savedExample));
    }

    @Override
    public Optional<ExampleResponse> deleteExample(Long exampleId) {
        var example = getExampleEntity(exampleId).orElseThrow(() -> new RuntimeException("Example with id " + exampleId + " not found"));
        exampleRepository.delete(example);
        return Optional.of(mapToExampleResponse(example));
    }

    @Override
    public Optional<ExampleResponse> getExample(Long exampleId) {
        var example = getExampleEntity(exampleId).orElseThrow(() -> new RuntimeException("Example with id " + exampleId + " not found"));
        return Optional.of(mapToExampleResponse(example));
    }

    @Override
    public Optional<Example> getExampleEntity(Long exampleId) {
        return exampleRepository.findById(exampleId);
    }

    @Override
    public Optional<List<ExampleResponse>> getAllByEntryId(Long entryId) {
        if (entryService.getEntryEntity(entryId).isEmpty())
            throw new RuntimeException("Entry with id " + entryId + " not found");
        var examples = exampleRepository.getAllByEntryId(entryId);
        var exampleResponses = examples.stream().map(this::mapToExampleResponse).toList();
        return Optional.of(exampleResponses);
    }


    @Override
    public Optional<List<ExampleResponse>> getAllExamples() {
        var examples = exampleRepository.findAll();
        var exampleResponses = examples.stream().map(this::mapToExampleResponse).toList();
        return Optional.of(exampleResponses);
    }

    @Override
    public ExampleResponse mapToExampleResponse(Example example) {
        return ExampleResponse.builder()
                .id(example.getId())
                .sentence(example.getSentence())
                .build();
    }

    @Override
    public Example mapToExample(ExampleRequest exampleRequest) {
        return Example.builder()
                .sentence(exampleRequest.getSentence())
                .build();
    }
}
