package com.lab.board.Repository;

import com.lab.board.domain.CommonGroupCode;
import com.lab.board.repository.CommonGroupCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest
@Slf4j
public class CommonGroupCodeTest {

    @Autowired
    private CommonGroupCodeRepository commonGroupCodeRepository;

    @Test
    void insert(){

        CommonGroupCode memberStateGroupCode = new CommonGroupCode();
        memberStateGroupCode.setCommonGroupCode("MEMBER_STATE_CODE");
        memberStateGroupCode.setCommonGroupCodeName("회원상태코드");
        memberStateGroupCode.setIsUsed(true);
        memberStateGroupCode.setCreatedBy("SYSTEM");
        memberStateGroupCode.setCreatedDate(Instant.now());
        memberStateGroupCode.setLastModifiedBy("SYSTEM");
        memberStateGroupCode.setLastModifiedDate(Instant.now());
        commonGroupCodeRepository.save(memberStateGroupCode);

        CommonGroupCode reservationStateGroupCode = new CommonGroupCode();
        reservationStateGroupCode.setCommonGroupCode("RESERVATION_STATE_CODE");
        reservationStateGroupCode.setCommonGroupCodeName("예약상태코드");
        reservationStateGroupCode.setIsUsed(true);
        reservationStateGroupCode.setCreatedBy("SYSTEM");
        reservationStateGroupCode.setCreatedDate(Instant.now());
        reservationStateGroupCode.setLastModifiedBy("SYSTEM");
        reservationStateGroupCode.setLastModifiedDate(Instant.now());
        commonGroupCodeRepository.save(reservationStateGroupCode);
    }


}
