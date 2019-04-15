package lk.royalBank.repository;

import lk.royalBank.entity.ATMcard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ATMRepository extends JpaRepository<ATMcard,String> {
}
