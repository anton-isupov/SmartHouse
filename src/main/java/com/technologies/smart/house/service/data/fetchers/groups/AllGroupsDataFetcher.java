package com.technologies.smart.house.service.data.fetchers.groups;

import com.google.common.collect.Lists;
import com.technologies.smart.house.model.Group;
import com.technologies.smart.house.repository.GroupRepository;
import com.technologies.smart.house.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllGroupsDataFetcher implements DataFetcher<List<Group>> {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return Lists.newArrayList(groupRepository.findAll());
    }
}
