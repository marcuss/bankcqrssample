package co.marcuss.acct.commons.dto;

public enum AccountType {
    SAVINGS("SV"), CHECKING("CH");
    private final String value;
    AccountType(String name) {
        this.value = name;
    }

    @Override
    public String toString() {
        return value;
    }
}
