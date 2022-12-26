package com.example.eldarwebexercise.service;

import com.example.eldarwebexercise.model.dto.request.ExampleRequest;
import com.example.eldarwebexercise.model.dto.response.EntryResponse;
import com.example.eldarwebexercise.model.dto.response.ExampleResponse;
import com.example.eldarwebexercise.model.entity.Example;

import java.util.List;
import java.util.Optional;

public interface ExampleService {

    Optional<ExampleResponse> createExample(Long entryId,ExampleRequest exampleRequest);

    Optional<ExampleResponse> updateExample(Long exampleId,Long entryId, ExampleRequest exampleRequest);

    Optional<ExampleResponse> deleteExample(Long exampleId);

    Optional<ExampleResponse> getExample(Long exampleId);

    Optional<Example> getExampleEntity(Long exampleId);

    Optional<List<ExampleResponse>> getAllByEntryId(Long entryId);

    Optional<List<ExampleResponse>> getAllExamples();

    ExampleResponse mapToExampleResponse(Example example);

    Example mapToExample(ExampleRequest exampleRequest);
}
