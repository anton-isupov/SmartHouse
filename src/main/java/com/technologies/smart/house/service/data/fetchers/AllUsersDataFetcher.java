package com.technologies.smart.house.service.data.fetchers;

import com.google.common.collect.Lists;
import com.technologies.smart.house.model.User;
import com.technologies.smart.house.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllUsersDataFetcher implements DataFetcher<List<User>> {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return Lists.newArrayList(userRepository.findAll());
    }
}
