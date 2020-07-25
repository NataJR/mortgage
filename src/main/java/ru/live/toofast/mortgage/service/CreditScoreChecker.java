package ru.live.toofast.mortgage.service;

import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

import java.util.HashMap;

public class CreditScoreChecker {

    private static HashMap<String, Double> map = new HashMap<String, Double>();
    public static void hasGoodCreditScore(MortgageRequest request, MortgageApplication application) {
        Double rating = map.get(request.getName());

        if (rating == null) {
            return;
        }

        if (rating < 75.0) {
            application.setDeclineReason("SCORING_FAILED");
            application.setStatus("DECLINED");
        }
    }
}