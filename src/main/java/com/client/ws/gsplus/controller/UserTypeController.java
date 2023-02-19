package com.client.ws.gsplus.controller;

import com.client.ws.gsplus.model.jpa.UserType;
import com.client.ws.gsplus.service.UserTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users-type")
@RequiredArgsConstructor
public class UserTypeController {

    private final UserTypeService userTypeService;
    @GetMapping
    public ResponseEntity<List<UserType>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userTypeService.findAll());
    }
}
