package ru.live.toofast.mortgage.service;

import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

public class MortgageChecker {
    public static void checkMortgage(MortgageRequest request, MortgageApplication mortgageApplication) {
        ComplianceChecker.isOnTerroristList(request, mortgageApplication);
        if (mortgageApplication.getStatus() == "DECLINED") {
            return;
        }

        CreditScoreChecker.hasGoodCreditScore(request, mortgageApplication);
        if (mortgageApplication.getStatus() == "DECLINED") {
            return;
        }

        PaymentAmountCalculator.hasEnoughForLoan(request, mortgageApplication);
        if (mortgageApplication.getStatus() == "DECLINED") {
            return;
        }

        //All is good
        mortgageApplication.setStatus("SUCCESS");
    }
}
