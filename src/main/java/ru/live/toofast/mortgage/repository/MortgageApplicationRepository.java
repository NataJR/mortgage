package ru.live.toofast.mortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.live.toofast.mortgage.entity.MortgageApplication;

import java.util.List;

public interface MortgageApplicationRepository extends JpaRepository<MortgageApplication, Long> {

    @Query("SELECT new MortgageApplication(t.id, t.name, t.status, t.declineReason) FROM MortgageApplication t where t.status = :status")
    List<MortgageApplication> findAllWhereStatusEquals(@Param("status") String status);

}
