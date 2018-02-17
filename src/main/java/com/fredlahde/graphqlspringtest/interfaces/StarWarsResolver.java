package com.fredlahde.graphqlspringtest.interfaces;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fredlahde.graphqlspringtest.entities.Droid;
import com.fredlahde.graphqlspringtest.entities.Episode;
import com.fredlahde.graphqlspringtest.entities.Human;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

public interface StarWarsResolver extends GraphQLQueryResolver {
    Character hero(Episode episode);

    Human human(String id, DataFetchingEnvironment env);

    Droid droid(String id);

    Character character(String id);

    List<Character> heroes();
}
