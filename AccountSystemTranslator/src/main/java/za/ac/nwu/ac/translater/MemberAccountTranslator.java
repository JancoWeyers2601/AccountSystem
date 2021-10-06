package za.ac.nwu.ac.translater;

import za.ac.nwu.ac.domain.dto.MemberAccountDto;

public interface MemberAccountTranslator {

    MemberAccountDto getMemberByMemberIDandAccountID(Long member, Long account);

    MemberAccountDto updateMemberAccount(Integer val_new, Long member, Long account);
}
