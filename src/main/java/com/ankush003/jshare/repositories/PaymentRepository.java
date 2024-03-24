package com.ankush003.jshare.repositories;

import com.ankush003.jshare.dao.impl.PaymentDaoImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDaoImpl, String> {

}
