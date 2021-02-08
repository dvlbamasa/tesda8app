package com.tesda8.region8.util.enums;

public enum FeedbackResponse {

    VERY_SATISFACTORY("Very Satisfactory"),
    SATISFACTORY("Satisfactory"),
    POOR("Poor");

    public final String label;

    private FeedbackResponse(String label) {
        this.label = label;
    }
}
