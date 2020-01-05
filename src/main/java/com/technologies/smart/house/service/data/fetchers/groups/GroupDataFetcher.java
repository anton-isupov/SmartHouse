package com.technologies.smart.house.service.data.fetchers.groups;

import com.technologies.smart.house.model.Group;
import com.technologies.smart.house.repository.GroupRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GroupDataFetcher implements DataFetcher<Group> {
    @Autowired
    GroupRepository groupRepository;

    @Override
    public Group get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        return groupRepository.findById(Long.decode(id)).orElse(null);
    }
}
