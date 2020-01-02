package com.technologies.smart.house.resource;

import com.technologies.smart.house.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/users")
@RestController
public class UserResource {

    @Autowired
    GraphQLService graphQLService;

    @PostMapping
    public ResponseEntity<Object> usersExecute(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

}
