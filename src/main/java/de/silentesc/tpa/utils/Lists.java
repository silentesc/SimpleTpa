package de.silentesc.tpa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Lists {
    private final List<UUID> invinciblePlayers = new ArrayList<>();

    // Getters
    public List<UUID> getInvinciblePlayers() {
        return invinciblePlayers;
    }
}
