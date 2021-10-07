package za.ac.nwu.ac.translater.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.MemberAccountDto;
import za.ac.nwu.ac.domain.persistence.MemberAccount;
import za.ac.nwu.ac.repo.persistence.MemberAccountRepository;
import za.ac.nwu.ac.translater.MemberAccountTranslator;

import javax.crypto.Mac;
import java.util.logging.Logger;

@Component
public class MemberAccountTranslatorImpl implements MemberAccountTranslator
{
   /* private static final Logger LOGGER = Logger*/
    private final MemberAccountRepository memberAccountRepository;

    public MemberAccountTranslatorImpl(MemberAccountRepository memberAccountRepository)
    {
        this.memberAccountRepository = memberAccountRepository;
    }

    @Override
    public MemberAccountDto getMemberByMemberIDandAccountID(Long member, Long account)
    {
        try
        {
            MemberAccount ma = memberAccountRepository.getMemberByMemberIDandAccountID(member,account);

            return new MemberAccountDto(ma);
        }
        catch (Exception e)
        {
            throw new RuntimeException("unable to read from DB",e);
        }

    }

    @Override
    public MemberAccountDto updateMemberAccount(Integer val_new, Long member, Long account)
    {
        try
        {
            MemberAccount ma = new MemberAccount(account,val_new,member);

            memberAccountRepository.UpdateMemberAccount(val_new,member,account);

            return new MemberAccountDto(ma);
        }
        catch (Exception e)
        {
            throw new RuntimeException("unable to update DB",e);
        }
    }
}
