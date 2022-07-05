package com.chow.dao;

import com.chow.domain.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxLogDao extends JpaRepository<TxLog, String> {
}
