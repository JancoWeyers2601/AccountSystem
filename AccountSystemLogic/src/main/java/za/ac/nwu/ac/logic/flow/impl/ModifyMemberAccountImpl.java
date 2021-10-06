package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.MemberAccountDto;
import za.ac.nwu.ac.logic.flow.ModifyMemberAccountFlow;
import za.ac.nwu.ac.translater.MemberAccountTranslator;

import javax.transaction.Transactional;

@Component
public class ModifyMemberAccountImpl implements ModifyMemberAccountFlow
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyMemberAccountImpl.class);

    private final MemberAccountTranslator MAC;

    public ModifyMemberAccountImpl(MemberAccountTranslator mac)
    {
        MAC = mac;
    }

    @Transactional
    @Override
    public MemberAccountDto Add_Currency(Integer val, Long member, Long account)
    {
        if(val>0)
        {

        }

        return null;
    }

    @Override
    public MemberAccountDto Remove_Currency(Integer val, Long member, Long account)
    {
        if(val>0)
        {
            val = val * -1;
        }

        LOGGER.info("The member account has values to update"+
                "\nval = {}" +
                "\nmember = {}" +
                "\naccount = {}" , val,member,account);

        Integer val_db = 0;
        Integer val_new = 0 ;

        val_db = Integer.parseInt(String.valueOf(MAC.getMemberByMemberIDandAccountID(member,account).getAccountBalance()));

        if (val + val_db >= 0)
        {
            LOGGER.info("Transaction is valid");
            val_new = val + val_db ;
            MemberAccountDto result = MAC.updateMemberAccount(val_new,member,account);
            LOGGER.info("UserAccount was updated with new value {} " , result);
            return result;
        }
        else
        {
            LOGGER.warn("Transaction is not valid Unable to remove more than what you have");
            throw new RuntimeException("unable to update the database");

        }



    }
}
