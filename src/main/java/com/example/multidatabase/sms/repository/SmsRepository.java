package com.example.multidatabase.sms.repository;

import com.example.multidatabase.sms.entity.Sms;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository extends JpaRepository<Sms, Long> {

    List<Sms> findByPhoneNumber(String phoneNumber);
}
