package ru.live.toofast.mortgage.service;

import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

import java.util.HashSet;
import java.util.Set;

public class ComplianceChecker {
    private static Set<String> terrorists = new HashSet<String>();

    public static void isOnTerroristList(MortgageRequest request, MortgageApplication application) {
        String clientName = request.getName();

        if (terrorists.contains(clientName)) {
            application.setDeclineReason("TERRORIST");
            application.setStatus("DECLINED");
        }
    }
}
