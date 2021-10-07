package za.ac.nwu.ac.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.ac.domain.persistence.MemberAccount;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@ApiModel(value = "MemberAccount", description = "The DTO representing the MemberAccount")
public class MemberAccountDto implements Serializable {

    private static final long serialVersionUID = 5001513593529715826L;

    private Long userAccountId;
    private Long accountTypeId;
    private Integer accountBalance;
    private Long memberId;

    public MemberAccountDto() {
    }

    public MemberAccountDto(Long userAccountId, Long accountTypeId, Integer accountBalance, Long memberId) {
        this.userAccountId = userAccountId;
        this.accountTypeId = accountTypeId;
        this.accountBalance = accountBalance;
        this.memberId = memberId;
    }
    public MemberAccountDto(MemberAccount memberAccount){
        this.userAccountId = memberAccount.getUserAccountId();
        this.accountTypeId = memberAccount.getAccountTypeId();
        this.accountBalance = memberAccount.getAccountBalance();
        this.memberId = memberAccount.getMemberId();
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
    public Long getMemberAccountId() {
        return userAccountId;
    }

    public void setMemberAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
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
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
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
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberAccountDto that = (MemberAccountDto) o;
        return Objects.equals(userAccountId, that.userAccountId) && Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(accountBalance, that.accountBalance) && Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccountId, accountTypeId, accountBalance, memberId);
    }

    @Override
    public String toString() {
        return "UserAccountDto{" +
                "userAccountId=" + userAccountId +
                ", accountTypeId=" + accountTypeId +
                ", accountBalance=" + accountBalance +
                ", memberId=" + memberId +
                '}';
    }
}