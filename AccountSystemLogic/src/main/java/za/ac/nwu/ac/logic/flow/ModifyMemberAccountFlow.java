package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.MemberAccountDto;

import javax.transaction.Transactional;

public interface ModifyMemberAccountFlow
{
    @Transactional
    MemberAccountDto Add_Currency (Integer val,Long member,Long account);

    @Transactional
    MemberAccountDto Remove_Currency (Integer val,Long member,Long account);


}
