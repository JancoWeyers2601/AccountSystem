package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.persistence.MemberAccount;

@Repository
public interface MemberAccountRepository extends JpaRepository<MemberAccount,Long>
{
    @Query(value = "SELECT " +
                    "ma " +
                    "From " +
                    "MemberAccount ma "+
                    "WHERE " +
                    "ma.memberId = :member " +
                    "AND ma.accountTypeId = :accountTypeId ")
    MemberAccount getMemberByMemberIDandAccountID(@Param("member") Long memberID , @Param("accountTypeId") Long accountTypeID);

    @Modifying
    @Query(value = "UPDATE " +
            "MemberAccount ma " +
            "SET ma.accountBalance = :accountBalance " +
            "WHERE ma.memberId = :member " +
            "AND ma.accountTypeId = :account ")
    void UpdateMemberAccount(@Param("accountBalance") Integer val_new,@Param("member") Long member,@Param("account") Long account);

}
