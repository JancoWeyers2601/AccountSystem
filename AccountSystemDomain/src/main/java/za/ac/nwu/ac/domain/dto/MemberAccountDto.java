package za.ac.nwu.ac.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.MemberAccount;

import java.io.Serializable;
import java.util.Objects;


@ApiModel(value = "UserAccount", description = "The DTO representing the UserAccount")
public class MemberAccountDto implements Serializable {

    private static final long serialVersionUID = 5001513593529715826L;

    private Long Account;
    private Long accountType;
    private Integer accountBalance;
    private Long member;
    

    public MemberAccountDto() {
    }

    public MemberAccountDto(Long userAccountId, Long accountType, Integer accountBalance, Long memberId) {
        this.Account = userAccountId;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.member = memberId;
    }

    public MemberAccountDto(MemberAccount memberAccount)
    {
        this.Account = memberAccount.getUserAccountId();
        this.accountType = memberAccount.getAccountTypeId();
        this.accountBalance = memberAccount.getAccountBalance();
        this.member = memberAccount.getMemberId();
    }

    @ApiModelProperty(
            position = 1,
            value = "User Account ID",
            name = "UserAccountId",
            notes = "Name of the ID of the specific UserAccountId",
            dataType = "java.lang.Long",
            example = "7",                              /*Any number as ID number*/
            allowEmptyValue = false,
            required = true
    )
    public Long getUserAccountId() {
        return Account;
    }

    public void setUserAccountId(Long userAccountId) {
        this.Account = userAccountId;
    }

    @ApiModelProperty(
            position = 2,
            value = "Account Type ID",
            name = "AccountTypeId",
            notes = "Name of the ID of the specific AccountTypeId",
            dataType = "java.lang.Long",
            example = "8",                              /*Any number as ID number*/
            allowEmptyValue = false,
            required = true
    )
    public Long getAccountTypeId() {
        return accountType;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountType = accountTypeId;
    }

    @ApiModelProperty(
            position = 3,
            value = "Account Type ID",
            name = "AccountTypeId",
            notes = "Name of the ID of the specific AccountTypeId",
            dataType = "java.lang.Long",
            example = "8",                              /*Any number as ID number*/
            allowEmptyValue = false,
            required = true
    )
    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    @ApiModelProperty(
            position = 4,
            value = "Member ID",
            name = "MemberId",
            notes = "Name of the ID of the specific MemberId",
            dataType = "java.lang.Long",
            example = "9",                              /*Any number as ID number*/
            allowEmptyValue = false,
            required = true
    )
    public Long getMember() {
        return member;
    }

    public void setMember(Long member) {
        this.member = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberAccountDto that = (MemberAccountDto) o;
        return Objects.equals(Account, that.Account) && Objects.equals(accountType, that.accountType) && Objects.equals(accountBalance, that.accountBalance) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Account, accountType, accountBalance, member);
    }

    @Override
    public String toString() {
        return "UserAccountDto{" +
                "userAccountId=" + Account +
                ", accountTypeId=" + accountType +
                ", accountBalance=" + accountBalance +
                ", memberId=" + member +
                '}';
    }
}