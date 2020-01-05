package com.technologies.smart.house.service.data.fetchers.groups;

import com.technologies.smart.house.model.Group;
import com.technologies.smart.house.model.User;
import com.technologies.smart.house.repository.GroupRepository;
import com.technologies.smart.house.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class EditGroupDataFetcher implements DataFetcher<Group> {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Group get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        Integer permissions = dataFetchingEnvironment.getArgument("permissions");
        String name = dataFetchingEnvironment.getArgument("name");
        List<Integer> userIds = dataFetchingEnvironment.getArgument("users");
        if (id == null) return null; //TODO: return Exception. No valid parameters

        Group group = groupRepository.findById(Long.decode(id)).orElse(null);
        if (group == null) return null;

        if (name != null) group.setName(name);
        if (permissions != null) group.setPermissions(permissions);
        List<User> users = new LinkedList<>();
        if (userIds != null && !userIds.isEmpty()) {
            userIds.forEach(ID -> userRepository.findById(ID.longValue()).ifPresent(users::add));
        }
        return groupRepository.save(group);
    }
}
