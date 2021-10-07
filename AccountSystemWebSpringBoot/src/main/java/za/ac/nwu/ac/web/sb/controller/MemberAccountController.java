package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.MemberAccountDto;
import za.ac.nwu.ac.domain.persistence.MemberAccount;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.impl.ModifyMemberAccountFlowImpl;

@RestController
@RequestMapping("member-account")
public class MemberAccountController
{
    private final Logger LOGGER = LoggerFactory.getLogger(MemberAccountController.class);

    private final ModifyMemberAccountFlowImpl modifyMemberAccountFlow;

    @Autowired
    public MemberAccountController(ModifyMemberAccountFlowImpl modifyMemberAccountFlow) {
        this.modifyMemberAccountFlow = modifyMemberAccountFlow;
    }

    @PutMapping("remove/{remove}")
    @ApiOperation(value = "Remove specified amount from user",
            notes = "")

    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "AccountTypes Returned", response = GeneralResponse.class),
                    @ApiResponse(code = 400, message = "Sorry, Bad request", response = GeneralResponse.class),
                    @ApiResponse(code = 404, message = "Not Found", response = GeneralResponse.class),
                    @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
            }
    )

    public ResponseEntity<GeneralResponse<MemberAccountDto>> removeUserAccount(
            @ApiParam(value = "Transaction Value",
                    name = "remove",
                    example = "600",
                    required = true)
            @PathVariable("remove") final String Removevalue,

            @ApiParam(value = "The MemberID that uniquely identifies the UserAccountOwner.",
                    name = "memberID",
                    example = "12345",
                    required = true)
            @RequestParam("memberID") final Long memberID,

            @ApiParam(value = "The AccountTypeID that uniquely identifies the AccountType.",
                    name = "accountTypeID",
                    example = "100000000000003",
                    required = true)
            @RequestParam("accountTypeID") final Long accountTypeID
    ) {


        Integer inttopas = 0 ;
        try
        {
            inttopas = Integer.parseInt(Removevalue);
        }
        catch (NumberFormatException e)
        {
            LOGGER.error("Remove value parse failed ",e);
        }
        LOGGER.info("Value to be removed {}",Removevalue);
        LOGGER.info("Value of member {}",memberID);
        LOGGER.info("value of account{}",accountTypeID);

        MemberAccountDto MemberAccount = modifyMemberAccountFlow.Remove_Currency(inttopas,memberID,accountTypeID);
        LOGGER.info("Update successful");

        GeneralResponse<MemberAccountDto> response = new GeneralResponse<>(true, MemberAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
