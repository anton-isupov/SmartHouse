package com.technologies.smart.house.service.data.fetchers.users;

import com.technologies.smart.house.model.User;
import com.technologies.smart.house.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditUserDataFetcher implements DataFetcher<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        String login = dataFetchingEnvironment.getArgument("login");
        String name = dataFetchingEnvironment.getArgument("name");
        String password = dataFetchingEnvironment.getArgument("password");
        String email = dataFetchingEnvironment.getArgument("email");
        if (id == null) return null; //TODO: return Exception. No valid parameters

        User user = userRepository.findById(Long.decode(id)).orElse(null);
        if (user == null) return null;

        if (name != null) user.setName(name);
        if (password != null) user.setPassword(password);
        if (email != null) user.setEmail(email);
        if (login != null) user.setLogin(login);
        return userRepository.save(user);
    }
}
