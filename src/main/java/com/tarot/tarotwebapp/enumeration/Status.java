package com.tarot.tarotwebapp.enumeration;

public enum Status {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    CANCEL("CANCEL");

    private final String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
