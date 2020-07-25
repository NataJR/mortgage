package ru.live.toofast.mortgage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;
import ru.live.toofast.mortgage.service.ComplianceChecker;
import ru.live.toofast.mortgage.service.CreditScoreChecker;
import ru.live.toofast.mortgage.service.PaymentAmountCalculator;
import sun.jvm.hotspot.utilities.Assert;

@SpringBootTest
class MortgageApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testComplianceChecker() {
        MortgageRequest request = new MortgageRequest();
        request.setName("Nata");
        request.setCreditAmount(400L);
        request.setPeriod(2L);
        request.setSalary(200L);

        MortgageApplication mortgageApplication = new MortgageApplication();
        mortgageApplication.setName("Nata");

        ComplianceChecker.isOnTerroristList(request, mortgageApplication);
        Assertions.assertNull(mortgageApplication.getDeclineReason());
    }

    @Test
    void testPaymentAmountCalculatorSuccess() {
        MortgageRequest request = new MortgageRequest();
        request.setName("Nata");
        request.setCreditAmount(400L);
        request.setPeriod(2L);
        request.setSalary(800L);

        MortgageApplication mortgageApplication = new MortgageApplication();
        mortgageApplication.setName("Nata");

        PaymentAmountCalculator.hasEnoughForLoan(request, mortgageApplication);
        Assertions.assertNull(mortgageApplication.getDeclineReason());
    }

    @Test
    void testPaymentAmountCalculatorFail() {
        MortgageRequest request = new MortgageRequest();
        request.setName("Nata");
        request.setCreditAmount(600L);
        request.setPeriod(2L);
        request.setSalary(200L);

        MortgageApplication mortgageApplication = new MortgageApplication();
        mortgageApplication.setName("Nata");

        PaymentAmountCalculator.hasEnoughForLoan(request, mortgageApplication);
        Assertions.assertEquals(mortgageApplication.getDeclineReason(), "LOW_SALARY");
    }

    @Test
    void testCreditScoreChecker() {
        MortgageRequest request = new MortgageRequest();
        request.setName("Nata");
        request.setCreditAmount(400L);
        request.setPeriod(2L);
        request.setSalary(200L);

        MortgageApplication mortgageApplication = new MortgageApplication();
        mortgageApplication.setName("Nata");

        CreditScoreChecker.hasGoodCreditScore(request, mortgageApplication);
        Assertions.assertNull(mortgageApplication.getDeclineReason());
    }

}
