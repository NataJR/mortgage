package ru.live.toofast.mortgage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageList;
import ru.live.toofast.mortgage.model.MortgageResponse;
import ru.live.toofast.mortgage.model.MortgageRequest;
import ru.live.toofast.mortgage.repository.MortgageApplicationRepository;
import ru.live.toofast.mortgage.service.MortgageChecker;

@RestController
public class MortgageController {

    private final MortgageApplicationRepository repository;

    public MortgageController(MortgageApplicationRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/mortgages")
    public MortgageList getAll(){
        return new MortgageList(repository.findAll());
    }

    @GetMapping("/mortgages/declined")
    public MortgageList getAllDeclined(){
        return new MortgageList(repository.findAllWhereStatusEquals("DECLINED"));
    }

    @GetMapping("/mortgages/success")
    public MortgageList getAllSuccess(){
        return new MortgageList(repository.findAllWhereStatusEquals("SUCCESS"));
    }

    @PostMapping("/mortgage")
    public MortgageResponse register(@RequestBody MortgageRequest request){

        MortgageApplication mortgageApplication = new MortgageApplication();
        mortgageApplication.setName(request.getName());
        MortgageChecker.checkMortgage(request, mortgageApplication);
        mortgageApplication = repository.save(mortgageApplication);

        MortgageResponse response = new MortgageResponse();
        response.setRequest(request);
        response.setId(mortgageApplication.getId());
        response.setResolution(mortgageApplication.getStatus());

        return response;
    }


}
