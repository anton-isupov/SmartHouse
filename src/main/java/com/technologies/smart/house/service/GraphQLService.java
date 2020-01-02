package com.technologies.smart.house.service;

import com.technologies.smart.house.model.User;
import com.technologies.smart.house.repository.UserRepository;
import com.technologies.smart.house.service.data.fetchers.AllUsersDataFetcher;
import com.technologies.smart.house.service.data.fetchers.UserDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    @Value("classpath:users.graphql")
    Resource resource;

    @Autowired
    UserRepository userRepository;

    private GraphQL graphQL;
    @Autowired
    private AllUsersDataFetcher allUsersDataFetcher;
    @Autowired
    private UserDataFetcher userDataFetcher;

    @PostConstruct
    public void loadSchema() throws IOException {
        loadDataIntoHSQL();

        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {

        // Load books into repository
        Stream.of(
                new User("0", "test-login", "test-password", "test-email"),
                new User("1", "test-login-1", "test-password-1", "test-email-1")
        ).forEach(user -> {
            userRepository.save(user);
        });

    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring ->
                        typeWiring
                                .dataFetcher("allUsers", allUsersDataFetcher)
                                .dataFetcher("user", userDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }


}
