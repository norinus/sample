package com.lab.board.repository;

import com.lab.board.domain.CommonGroupCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommonGroupCodeRepository extends JpaRepository <CommonGroupCode, String>, JpaSpecificationExecutor<CommonGroupCode> {
}
