package com.technologies.smart.house.service.data.fetchers.groups;

import com.technologies.smart.house.repository.GroupRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveGroupDataFetcher implements DataFetcher<Boolean> {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Boolean get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        groupRepository.deleteById(Long.decode(id));
        return true;
    }
}
