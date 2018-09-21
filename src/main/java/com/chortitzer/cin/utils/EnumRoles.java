package com.chortitzer.cin.utils;

public enum EnumRoles {

    ADMIN("ADMIN"),
    BALANCEADOS("BALANCEADOS"),
    BASCULA("BASCULA"),
    ETIQUETAS("ETIQUETAS");

    private final String text;

    /**
     * @param text
     */
    EnumRoles(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
