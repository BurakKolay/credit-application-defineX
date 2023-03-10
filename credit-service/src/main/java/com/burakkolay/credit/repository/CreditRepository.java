package com.burakkolay.credit.repository;


import com.burakkolay.credit.model.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit,Long> {

    @Query(value = "SELECT * FROM credit WHERE credit_result=1",nativeQuery = true)
    List<Credit> getAllCreditWaiting();

    @Query(value = "SELECT * FROM credit WHERE applicant_id=(SELECT id FROM applicant WHERE identification_number=(:id))",nativeQuery = true)
    List<Credit> getCreditsByApplicantId(@Param("id")Long id);

    @Query(value = "SELECT * FROM credit ORDER BY id", nativeQuery = true)
    List<Credit> getAllCreditsOrderedById();

}
