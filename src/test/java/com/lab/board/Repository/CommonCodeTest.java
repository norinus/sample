package com.lab.board.Repository;

import com.lab.board.domain.CommonCode;
import com.lab.board.domain.CommonCodeKey;
import com.lab.board.domain.CommonGroupCode;
import com.lab.board.repository.CommonCodeRepository;
import com.lab.board.repository.CommonGroupCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest
@Slf4j
public class CommonCodeTest {

    @Autowired
    private CommonCodeRepository commonCodeRepository;

    @Test
    void insert(){

        //활동
        CommonCodeKey memberActiveStateCodeKey = new CommonCodeKey();
        memberActiveStateCodeKey.setCommonGroupCode("MEMBER_STATE_CODE");
        memberActiveStateCodeKey.setCommonCode("01");

        CommonCode memberActiveStateCode = new CommonCode();
        memberActiveStateCode.setId(memberActiveStateCodeKey);
        memberActiveStateCode.setCommonCodeName("활동");
        memberActiveStateCode.setIsUsed(true);
        memberActiveStateCode.setCreatedBy("SYSTEM");
        memberActiveStateCode.setCreatedDate(Instant.now());
        memberActiveStateCode.setLastModifiedBy("SYSTEM");
        memberActiveStateCode.setLastModifiedDate(Instant.now());
        commonCodeRepository.save(memberActiveStateCode);


        //활동제한
        CommonCodeKey memberRestrictStateCodeKey = new CommonCodeKey();
        memberRestrictStateCodeKey.setCommonGroupCode("MEMBER_STATE_CODE");
        memberRestrictStateCodeKey.setCommonCode("02");

        CommonCode memberRestrictStateCode = new CommonCode();
        memberRestrictStateCode.setId(memberRestrictStateCodeKey);
        memberRestrictStateCode.setCommonCodeName("활동제한");
        memberRestrictStateCode.setIsUsed(true);
        memberRestrictStateCode.setCreatedBy("SYSTEM");
        memberRestrictStateCode.setCreatedDate(Instant.now());
        memberRestrictStateCode.setLastModifiedBy("SYSTEM");
        memberRestrictStateCode.setLastModifiedDate(Instant.now());
        commonCodeRepository.save(memberRestrictStateCode);


        //활동중지
        CommonCodeKey membersSuspensionStateCodeKey = new CommonCodeKey();
        membersSuspensionStateCodeKey.setCommonGroupCode("MEMBER_STATE_CODE");
        membersSuspensionStateCodeKey.setCommonCode("03");

        CommonCode  membersSuspensionStateCode = new CommonCode();
        membersSuspensionStateCode.setId(membersSuspensionStateCodeKey);
        membersSuspensionStateCode.setCommonCodeName("활동중지");
        membersSuspensionStateCode.setIsUsed(true);
        membersSuspensionStateCode.setCreatedBy("SYSTEM");
        membersSuspensionStateCode.setCreatedDate(Instant.now());
        membersSuspensionStateCode.setLastModifiedBy("SYSTEM");
        membersSuspensionStateCode.setLastModifiedDate(Instant.now());
        commonCodeRepository.save(membersSuspensionStateCode);



        //탈퇴
        CommonCodeKey membersWithdrawStateCodeKey = new CommonCodeKey();
        membersWithdrawStateCodeKey.setCommonGroupCode("MEMBER_STATE_CODE");
        membersWithdrawStateCodeKey.setCommonCode("04");

        CommonCode membersWithdrawStateCode = new CommonCode();
        membersWithdrawStateCode.setId(membersWithdrawStateCodeKey);
        membersWithdrawStateCode.setCommonCodeName("탈퇴");
        membersWithdrawStateCode.setIsUsed(true);
        membersWithdrawStateCode.setCreatedBy("SYSTEM");
        membersWithdrawStateCode.setCreatedDate(Instant.now());
        membersWithdrawStateCode.setLastModifiedBy("SYSTEM");
        membersWithdrawStateCode.setLastModifiedDate(Instant.now());
        commonCodeRepository.save(membersWithdrawStateCode);





        //예약중
        CommonCodeKey reservationStateCodeKey = new CommonCodeKey();
        reservationStateCodeKey.setCommonGroupCode("RESERVATION_STATE_CODE");
        reservationStateCodeKey.setCommonCode("01");

        CommonCode reservationStateCode = new CommonCode();
        reservationStateCode.setId(reservationStateCodeKey);
        reservationStateCode.setCommonCodeName("확정 예약");
        reservationStateCode.setIsUsed(true);
        reservationStateCode.setCreatedBy("SYSTEM");
        reservationStateCode.setCreatedDate(Instant.now());
        reservationStateCode.setLastModifiedBy("SYSTEM");
        reservationStateCode.setLastModifiedDate(Instant.now());
        commonCodeRepository.save(reservationStateCode);


        //예약대기
        CommonCodeKey reservationWaitingStateCodeKey = new CommonCodeKey();
        reservationWaitingStateCodeKey.setCommonGroupCode("RESERVATION_STATE_CODE");
        reservationWaitingStateCodeKey.setCommonCode("02");

        CommonCode reservationWaitingStateCode = new CommonCode();
        reservationWaitingStateCode.setId(reservationWaitingStateCodeKey);
        reservationWaitingStateCode.setCommonCodeName("예약대기");
        reservationWaitingStateCode.setIsUsed(true);
        reservationWaitingStateCode.setCreatedBy("SYSTEM");
        reservationWaitingStateCode.setCreatedDate(Instant.now());
        reservationWaitingStateCode.setLastModifiedBy("SYSTEM");
        reservationWaitingStateCode.setLastModifiedDate(Instant.now());
        commonCodeRepository.save(reservationWaitingStateCode);


        //예약취소
        CommonCodeKey reservationCancelStateCodeKey = new CommonCodeKey();
        reservationCancelStateCodeKey.setCommonGroupCode("RESERVATION_STATE_CODE");
        reservationCancelStateCodeKey.setCommonCode("03");

        CommonCode reservationCancelStateCode = new CommonCode();
        reservationCancelStateCode.setId(reservationCancelStateCodeKey);
        reservationCancelStateCode.setCommonCodeName("예약취소");
        reservationCancelStateCode.setIsUsed(true);
        reservationCancelStateCode.setCreatedBy("SYSTEM");
        reservationCancelStateCode.setCreatedDate(Instant.now());
        reservationCancelStateCode.setLastModifiedBy("SYSTEM");
        reservationCancelStateCode.setLastModifiedDate(Instant.now());
        commonCodeRepository.save(reservationCancelStateCode);


        //예약보류
        CommonCodeKey reservationPendingStateCodeKey = new CommonCodeKey();
        reservationPendingStateCodeKey.setCommonGroupCode("RESERVATION_STATE_CODE");
        reservationPendingStateCodeKey.setCommonCode("04");

        CommonCode reservationPendingStateCode = new CommonCode();
        reservationPendingStateCode.setId(reservationPendingStateCodeKey);
        reservationPendingStateCode.setCommonCodeName("예약보류");
        reservationPendingStateCode.setIsUsed(true);
        reservationPendingStateCode.setCreatedBy("SYSTEM");
        reservationPendingStateCode.setCreatedDate(Instant.now());
        reservationPendingStateCode.setLastModifiedBy("SYSTEM");
        reservationPendingStateCode.setLastModifiedDate(Instant.now());
        commonCodeRepository.save(reservationPendingStateCode);

        //예약거절
        CommonCodeKey reservationRejectedStateCodeKey = new CommonCodeKey();
        reservationRejectedStateCodeKey.setCommonGroupCode("RESERVATION_STATE_CODE");
        reservationRejectedStateCodeKey.setCommonCode("05");

        CommonCode reservationRejectedStateCode = new CommonCode();
        reservationRejectedStateCode.setId(reservationRejectedStateCodeKey);
        reservationRejectedStateCode.setCommonCodeName("예약거절");
        reservationRejectedStateCode.setIsUsed(true);
        reservationRejectedStateCode.setCreatedBy("SYSTEM");
        reservationRejectedStateCode.setCreatedDate(Instant.now());
        reservationRejectedStateCode.setLastModifiedBy("SYSTEM");
        reservationRejectedStateCode.setLastModifiedDate(Instant.now());
        commonCodeRepository.save(reservationRejectedStateCode);
        
    }


}
