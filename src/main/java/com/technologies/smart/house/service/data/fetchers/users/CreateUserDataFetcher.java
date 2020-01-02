package com.technologies.smart.house.service.data.fetchers.users;

import com.technologies.smart.house.model.User;
import com.technologies.smart.house.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserDataFetcher implements DataFetcher<User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public User get(DataFetchingEnvironment dataFetchingEnvironment) {
        String login = dataFetchingEnvironment.getArgument("login");
        String name = dataFetchingEnvironment.getArgument("name");
        String password = dataFetchingEnvironment.getArgument("password");
        String email = dataFetchingEnvironment.getArgument("email");

        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }
}
