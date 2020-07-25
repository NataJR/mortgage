package ru.live.toofast.mortgage.service;

import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

public class PaymentAmountCalculator {
    public static void hasEnoughForLoan(MortgageRequest request, MortgageApplication application) {
        Float amountPerMonth = request.getCreditAmount() / (float) request.getPeriod();

        if (amountPerMonth >= 0.5*request.getSalary()) {
            application.setDeclineReason("LOW_SALARY");
            application.setStatus("DECLINED");
        }
    }
}
