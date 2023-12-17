package com.example.multidatabase.sms.repository;


import com.example.multidatabase.sms.entity.Sms;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SmsRepositoryTest {

    @Autowired
    private SmsRepository smsRepository;

    private Long SMS_ID;


    @BeforeEach
    void setUp() {
        Sms message1 = new Sms(1L, "08123456789", "Hello");
        Sms message2 = new Sms(2L, "01012345678", "Hello2");
        Sms message3 = new Sms(3L, "01012345678", "Hello3");

        smsRepository.saveAll(List.of(message1, message2, message3));

        SMS_ID = message1.getId();
    }

    @AfterEach
    void tearDown() {
        smsRepository.deleteAll();
    }


    @DisplayName("SMS 레포지토리와 연결된 DB에서 ID로 조회가 가능하다.")
    @Test
    void testFindById() {
        // given
        // when
        List<Sms> all = smsRepository.findAll();
        Sms message = smsRepository.findById(SMS_ID).orElseThrow();

        // then
        Assertions.assertThat(message).isNotNull();
    }

    @DisplayName("SMS 레포지토리와 연결된 DB에서 전화번호로 조회가 가능하다.")
    @Test
    void testFindByPhoneNumber() {
        // given
        // when
        List<Sms> messages = smsRepository.findByPhoneNumber("01012345678");

        // then
        Assertions.assertThat(messages.size()).isEqualTo(2);
    }
}