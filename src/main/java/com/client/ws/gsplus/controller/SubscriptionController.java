package com.client.ws.gsplus.controller;

import com.client.ws.gsplus.dto.SubscriptionTypeDto;
import com.client.ws.gsplus.model.jpa.SubscriptionType;
import com.client.ws.gsplus.service.SubscriptionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subscriptions-type")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionTypeService subscriptionTypeService;

    @GetMapping
    public ResponseEntity<List<SubscriptionType>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionType> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.findByIdOrElseThrow(id));
    }

    @PostMapping
    public ResponseEntity<SubscriptionType> create(@RequestBody @Valid SubscriptionTypeDto subscriptionType){
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionTypeService.create(subscriptionType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionType> update(@PathVariable("id")Long id,
                                                   @Valid @RequestBody SubscriptionTypeDto subscriptionType){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.update(id,subscriptionType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        subscriptionTypeService.delete(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
