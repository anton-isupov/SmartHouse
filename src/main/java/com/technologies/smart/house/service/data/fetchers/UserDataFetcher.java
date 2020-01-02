package com.technologies.smart.house.service.data.fetchers;

import com.technologies.smart.house.model.User;
import com.technologies.smart.house.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDataFetcher implements DataFetcher<User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public User get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        return userRepository.getOne(id);
    }
}
