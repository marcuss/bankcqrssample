package co.marcuss.cqrs.core.utils;

import java.util.UUID;

public final class PrefixedUIID implements java.io.Serializable, Comparable<PrefixedUIID> {

    private final UUID id;
    private final String prefix;

    public PrefixedUIID(String prefix, UUID id) {
        this.prefix = prefix;
        this.id = id;
    }

    public int compareTo(PrefixedUIID o) {
        if (this.id.compareTo(o.id) == 0) {
            return prefix.compareTo(o.prefix);
        }
        return this.id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return prefix + id.toString();
    }
}
