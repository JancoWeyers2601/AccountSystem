package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.MemberAccountDto;

import javax.transaction.Transactional;

public interface ModifyMemberAccountFlow
{
    @Transactional
    MemberAccountDto removeCurrency(Integer subtractVal, Long memberId, Long accountTypeId);

    @Transactional
    MemberAccountDto addCurrency(Integer AddVal, Long memberId, Long accountTypeId);
}
