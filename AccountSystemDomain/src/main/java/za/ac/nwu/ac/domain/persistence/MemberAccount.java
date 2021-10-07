package za.ac.nwu.ac.domain.persistence;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TBL_ACCOUNT", schema = "ACCSYS")     //Table name & Username (Schema might not be needed)
public class MemberAccount implements Serializable {

    private static final long serialVersionUID = 1721935955949522116L;

    private Long userAccountId;
    private Long accountTypeId;
    private Integer accountBalance;
    private Long memberId;
    private LocalDate creationDate;

    public MemberAccount() {
    }

    public MemberAccount(Long userAccountId, Long accountTypeId, Integer accountBalance, Long memberId, LocalDate creationDate) {
        this.userAccountId = userAccountId;
        this.accountTypeId = accountTypeId;
        this.accountBalance = accountBalance;
        this.memberId = memberId;
        this.creationDate = creationDate;
    }

    public MemberAccount(Long accountTypeId, Integer accountBalance, Long memberId, LocalDate creationDate) {
        this.accountTypeId = accountTypeId;
        this.accountBalance = accountBalance;
        this.memberId = memberId;
        this.creationDate = creationDate;
    }

    public MemberAccount(Long userAccountId, Integer accountBalance, Long memberId) {
        this.userAccountId = userAccountId;
        this.accountBalance = accountBalance;
        this.memberId = memberId;
    }

    @Id
    @SequenceGenerator(name = "USER_ACCOUNT_ID_SEQ", sequenceName = "ACCSYS.USER_ACCOUNT_ID_SEQ", allocationSize = 1)       //SequenceName =?
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ACCOUNT_ID_SEQ")
    @Column(name = "PK_TBL_ACCOUNT") //Primary key column
    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    @Column(name = "FK_ACCOUNTTYPE")
    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    @Column(name = "TBL_ACCOUNT_BALANCE")
    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Column(name = "FK_TBL_MEMBER")
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Column(name = "CREATIONDATE")
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberAccount that = (MemberAccount) o;
        return Objects.equals(userAccountId, that.userAccountId) && Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(accountBalance, that.accountBalance) && Objects.equals(memberId, that.memberId) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccountId, accountTypeId, accountBalance, memberId, creationDate);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userAccountId=" + userAccountId +
                ", accountTypeId=" + accountTypeId +
                ", accountBalance=" + accountBalance +
                ", memberId=" + memberId +
                ", creationDate=" + creationDate +
                '}';
    }

    /*TODO update users to members /*/

}