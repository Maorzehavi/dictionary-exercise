package com.example.eldarwebexercise.conroller;

import com.example.eldarwebexercise.model.dto.request.EntryRequest;
import com.example.eldarwebexercise.model.dto.request.ExampleRequest;
import com.example.eldarwebexercise.model.dto.response.EntryExamplesResponse;
import com.example.eldarwebexercise.model.dto.response.EntryResponse;
import com.example.eldarwebexercise.model.dto.response.ExampleResponse;
import com.example.eldarwebexercise.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/vi/dictionary")
public class DictionaryController {


    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @PostMapping("/add-entry")
    public EntryResponse createEntry(@RequestBody EntryRequest entryRequest) {
        return dictionaryService.createEntry(entryRequest)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry not created"));
    }

    @GetMapping("/get-entry/{word}")
    public EntryResponse getEntry(@PathVariable String word) {
        return dictionaryService.getEntry(word)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found"));
    }

    @GetMapping("/get-entry-examples/{word}")
    public EntryExamplesResponse getEntryExamples(@PathVariable String word) {
        return dictionaryService.getEntryWithExamples(word)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found"));
    }

    @PutMapping("/update-entry/{word}")
    public EntryResponse updateEntry(@PathVariable String word, @RequestBody EntryRequest entryRequest) {
        return dictionaryService.updateEntry(word, entryRequest)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found"));
    }

    @DeleteMapping("/delete-entry/{word}")
    public EntryResponse deleteEntry(@PathVariable String word) {
        return dictionaryService.deleteEntry(word)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found"));
    }

    @PostMapping("/add-example/{word}")
    public ExampleResponse addExample(@PathVariable String word, @RequestBody ExampleRequest exampleRequest) {
        return dictionaryService.createExample(word, exampleRequest)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found"));
    }


}
