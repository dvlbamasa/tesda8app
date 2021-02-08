package com.tesda8.region8.util.enums;

public enum FeedbackQuery {
    QUESTION_1("1. Mabilis na serbisyo"),
    QUESTION_2("2. Mahusay na serbisyo"),
    QUESTION_3("3. Malinis at maayos na tanggapan"),
    QUESTION_4("4. May malasakit at nauunawaan ang serbisyo"),
    QUESTION_5("5. Makatwiran ang presyo ng piling serbisyo"),
    QUESTION_6("6. Mapagkakatiwalaan ang serbisyo"),
    QUESTION_7("7. Magalang at tapat na serbisyo"),
    QUESTION_8("8. Abot and Lahat ng serbisyo ng TESDA");

    public final String label;

    private FeedbackQuery(String label) {
        this.label = label;
    }

}
