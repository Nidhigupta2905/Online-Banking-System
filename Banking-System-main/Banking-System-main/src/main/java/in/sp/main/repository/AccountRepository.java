package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>
{

}
