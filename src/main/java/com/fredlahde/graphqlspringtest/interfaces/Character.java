package com.fredlahde.graphqlspringtest.interfaces;

import com.fredlahde.graphqlspringtest.entities.Episode;

import java.util.List;

public interface Character {
    String getId();
    String getName();
    List<Character> getFriends();
    List<Episode> getAppearsIn();
}
