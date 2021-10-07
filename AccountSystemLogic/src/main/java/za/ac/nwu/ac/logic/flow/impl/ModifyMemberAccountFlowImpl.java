package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.MemberAccountDto;
import za.ac.nwu.ac.logic.flow.ModifyMemberAccountFlow;
import za.ac.nwu.ac.translater.MemberAccountTranslator;

import javax.transaction.Transactional;

@Component
public class ModifyMemberAccountFlowImpl implements ModifyMemberAccountFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyMemberAccountFlowImpl.class);
    private final MemberAccountTranslator memberAccountTranslator;

    public ModifyMemberAccountFlowImpl(MemberAccountTranslator userAccountTranslator) {
        this.memberAccountTranslator = userAccountTranslator;
    }


    @Transactional
    @Override
    public MemberAccountDto removeCurrency(Integer subtractVal, Long memberId, Long accountTypeId)
    {
        if(subtractVal >= 0)
        {
            subtractVal = (-1 * subtractVal);
        }
        LOGGER.info("The UserAccount te Update has input values: " +
                "\nSubtractValue = {}" +
                "\nMemberID = {}" +
                "\nAccountTypeID = {}",subtractVal, memberId, accountTypeId);

        Integer oldBal =0;
        Integer newBal =0;

        oldBal =Integer.parseInt(String.valueOf(memberAccountTranslator.getMemberByMemberIDandAccountID(memberId, accountTypeId).getAccountBalance()));

        if((subtractVal + oldBal) >= 0){
            LOGGER.info("Transaction is valid");
            newBal = oldBal + subtractVal;
            MemberAccountDto result = memberAccountTranslator.updateMemberAccount(newBal, memberId, accountTypeId);
            LOGGER.info("UserAccount was updated and has return object {}", result);
            return result;
        }else{
            LOGGER.warn("Transaction is not valid - Cannot subtract more tha you own!");
            throw new RuntimeException("Unable to update the database");
        }
    }

    @Transactional
    @Override
    public MemberAccountDto addCurrency(Integer AddVal, Long memberId, Long accountTypeId)
    {
        if(AddVal <= 0)
        {
            LOGGER.warn("Cannot add negative or 0 amounts ");
            throw new RuntimeException("Cannot add negative or 0 amounts");
        }


        LOGGER.info("The UserAccount te Update has input values: " +
                "\nSubtractValue = {}" +
                "\nMemberID = {}" +
                "\nAccountTypeID = {}",AddVal, memberId, accountTypeId);

        Integer oldBal =0;
        Integer newBal =0;

        oldBal =Integer.parseInt(String.valueOf(memberAccountTranslator.getMemberByMemberIDandAccountID(memberId, accountTypeId).getAccountBalance()));

        if((AddVal + oldBal) >= 0){
            LOGGER.info("Transaction is valid");
            newBal = oldBal + AddVal;
            MemberAccountDto result = memberAccountTranslator.updateMemberAccount(newBal, memberId, accountTypeId);
            LOGGER.info("UserAccount was updated and has return object {}", result);
            return result;
        }else{
            LOGGER.warn("Transaction is not valid - Cannot subtract more tha you own!");
            throw new RuntimeException("Unable to update the database");
        }
    }
}