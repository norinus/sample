package com.lab.board.repository;

import com.lab.board.domain.CommonCode;
import com.lab.board.domain.CommonCodeKey;
import com.lab.board.domain.CommonGroupCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommonCodeRepository extends JpaRepository<CommonCode, CommonCodeKey>, JpaSpecificationExecutor<CommonCode> {

}
