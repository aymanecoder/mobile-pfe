package com.example.mobile_pfe.matchActivities;

import com.example.mobile_pfe.sevices.MatchRequest;

public class MatchRepository {
    // Other existing code in MatchRepository

    private MatchRequest matchRequest;

    // Singleton instance
    private static MatchRepository instance;

    private MatchRepository() {
        // Private constructor to enforce singleton pattern
        matchRequest = new MatchRequest();
    }

    public static MatchRepository getInstance() {
        if (instance == null) {
            instance = new MatchRepository();
        }
        return instance;
    }

    public MatchRequest getMatchRequest() {
        return matchRequest;
    }

    // Add this method to save/update the MatchRequest
    public void saveMatchRequest(MatchRequest matchRequest) {
        this.matchRequest = matchRequest;
    }
}
