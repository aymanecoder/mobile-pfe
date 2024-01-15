package com.example.mobile_pfe.GroupActivity;

import java.util.Random;

public class RandomGroupNameGenerator {

    private static final String[] adjectives = {
            "Swift", "Quiet", "Mighty", "Gentle", "Fierce", "Brave", "Clever", "Bright", "Wise", "Happy", "Serene", "Dynamic"
    };

    private static final String[] nouns = {
            "Eagles", "Forests", "Panthers", "Rivers", "Mountains", "Lions", "Stars", "Warriors", "Knights", "Dreams", "Oceans", "Wizards"
    };

    private static final Random random = new Random();

    public static String generateGroupName() {
        String adjective = adjectives[random.nextInt(adjectives.length)];
        String noun = nouns[random.nextInt(nouns.length)];
        return adjective + noun;
    }
}
