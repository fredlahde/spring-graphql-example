package com.fredlahde.graphqlspringtest.queries;

import com.fredlahde.graphqlspringtest.entities.Droid;
import com.fredlahde.graphqlspringtest.entities.Episode;
import com.fredlahde.graphqlspringtest.entities.Human;
import com.fredlahde.graphqlspringtest.interfaces.Character;
import com.fredlahde.graphqlspringtest.interfaces.StarWarsResolver;
import com.fredlahde.graphqlspringtest.repositories.CharacterRepository;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
@Component
public class Query implements StarWarsResolver {

    private CharacterRepository characterRepository;

    @Autowired
    public Query(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public Character hero(Episode episode) {
        return episode != null ? characterRepository.getHeroes().get(episode) : characterRepository.getCharacters().get("1000");
    }

    @Override
    public Human human(String id, DataFetchingEnvironment env) {
        return (Human) characterRepository.getCharacters().values().stream()
                .filter(character -> character instanceof Human && character.getId().equals(id))
                .findFirst()
                .orElseGet(null);
    }

    @Override
    public Droid droid(String id) {
        return (Droid) characterRepository.getCharacters().values().stream()
                .filter(character -> character instanceof Droid && character.getId().equals(id))
                .findFirst()
                .orElseGet(null);
    }

    @Override
    public Character character(String id) {
        return characterRepository.getCharacters().get(id);
    }

    @Override
    public List<Character> heroes() {
        return new ArrayList<>(this.characterRepository.getCharacters().values());
    }
}