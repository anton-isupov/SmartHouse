package com.technologies.smart.house.service;

import com.technologies.smart.house.repository.UserRepository;
import com.technologies.smart.house.service.data.fetchers.groups.*;
import com.technologies.smart.house.service.data.fetchers.users.*;
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
    @Autowired
    private CreateUserDataFetcher createUserDataFetcher;
    @Autowired
    private RemoveUserDataFetcher removeUserDataFetcher;
    @Autowired
    private GroupDataFetcher groupDataFetcher;
    @Autowired
    private AllGroupsDataFetcher allGroupsDataFetcher;
    @Autowired
    private CreateGroupDataFetcher createGroupDataFetcher;
    @Autowired
    private RemoveGroupDataFetcher removeGroupDataFetcher;
    @Autowired
    private EditUserDataFetcher editUserDataFetcher;
    @Autowired
    private EditGroupDataFetcher editGroupDataFetcher;

    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring ->
                        typeWiring
                                .dataFetcher("allUsers", allUsersDataFetcher)
                                .dataFetcher("user", userDataFetcher)
                                .dataFetcher("allGroups", allGroupsDataFetcher)
                                .dataFetcher("group", groupDataFetcher)
                )
                .type("Mutation", typeWiring ->
                        typeWiring
                                .dataFetcher("createUser", createUserDataFetcher)
                                .dataFetcher("removeUser", removeUserDataFetcher)
                                .dataFetcher("editUser", editUserDataFetcher)
                                .dataFetcher("createGroup", createGroupDataFetcher)
                                .dataFetcher("removeGroup", removeGroupDataFetcher)
                                .dataFetcher("editGroup", editGroupDataFetcher)
                )
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }


}
