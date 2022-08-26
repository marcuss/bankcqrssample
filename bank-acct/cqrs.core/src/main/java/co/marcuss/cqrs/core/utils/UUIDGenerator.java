package co.marcuss.cqrs.core.utils;

import java.util.UUID;

public class UUIDGenerator {

    public static String generateUIID(String prefix) {
        return new PrefixedUIID(prefix, generateUIID()).toString();
    }

    public static UUID generateUIID() {
        return UUID.randomUUID();
    }
}
