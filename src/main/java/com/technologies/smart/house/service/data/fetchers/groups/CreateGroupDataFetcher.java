package com.technologies.smart.house.service.data.fetchers.groups;

import com.technologies.smart.house.model.Group;
import com.technologies.smart.house.model.User;
import com.technologies.smart.house.repository.GroupRepository;
import com.technologies.smart.house.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class CreateGroupDataFetcher implements DataFetcher<Group> {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Group get(DataFetchingEnvironment dataFetchingEnvironment) {
        String name = dataFetchingEnvironment.getArgument("name");
        Integer permissions = dataFetchingEnvironment.getArgument("permissions");
        List<Integer> userIds = dataFetchingEnvironment.getArgument("users");
        Group group = new Group();
        group.setName(name);
        group.setPermissions(permissions);
        List<User> users = new LinkedList<>();
        userIds.forEach(id -> userRepository.findById(id.longValue()).ifPresent(users::add));
        group.setUsers(users);
        return groupRepository.save(group);
    }
}
