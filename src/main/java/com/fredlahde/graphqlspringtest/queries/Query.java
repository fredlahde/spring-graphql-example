package com.fredlahde.graphqlspringtest.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fredlahde.graphqlspringtest.entities.Droid;
import com.fredlahde.graphqlspringtest.entities.Episode;
import com.fredlahde.graphqlspringtest.entities.Human;
import com.fredlahde.graphqlspringtest.interfaces.Character;
import com.fredlahde.graphqlspringtest.repositories.CharacterRepository;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private CharacterRepository characterRepository;

    public Character hero(Episode episode) {
        return episode != null ? characterRepository.getHeroes().get(episode) : characterRepository.getCharacters().get("1000");
    }

    public Human human(String id, DataFetchingEnvironment env) {
        return (Human) characterRepository.getCharacters().values().stream()
                .filter(character -> character instanceof Human && character.getId().equals(id))
                .findFirst()
                .orElseGet(null);
    }

    public Droid droid(String id) {
        return (Droid) characterRepository.getCharacters().values().stream()
                .filter(character -> character instanceof Droid && character.getId().equals(id))
                .findFirst()
                .orElseGet(null);
    }

    public Character character(String id) {
        return characterRepository.getCharacters().get(id);
    }
}