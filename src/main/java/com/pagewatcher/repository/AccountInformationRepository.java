package com.pagewatcher.repository;

import com.pagewatcher.entity.AccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInformationRepository extends JpaRepository<AccountInformation, String> {


}
