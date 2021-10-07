package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.MemberAccountDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.ModifyMemberAccountFlow;

@RestController
@RequestMapping("member-account")
public class MemberAccountController {
    private final Logger LOGGER = LoggerFactory.getLogger(MemberAccountController.class);

    private final ModifyMemberAccountFlow modifyMemberAccountFlow;

    @Autowired
    public MemberAccountController(ModifyMemberAccountFlow modifyMemberAccountFlow) {
        this.modifyMemberAccountFlow = modifyMemberAccountFlow;
    }

    @PutMapping("remove/{remove}")
    @ApiOperation(value = "Subtract value from the UserAccount",
            notes="")
    @ApiResponses(value={
            @ApiResponse(code=201, message = "Account Type Successfully Updated", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberAccountDto>>removeMemberAccount(
            @ApiParam(value="Transaction Value",
                    name="remove",
                    example = "100",
                    required = true)
            @PathVariable("remove") final String subtractValue,

            @ApiParam(value = "The MemberID that uniquely identifies the UserAccountOwner.",
                    name = "memberID",
                    example = "12345",
                    required = true)
            @RequestParam("memberID") final Long memberID,

            @ApiParam(value = "The AccountTypeID that uniquely identifies the AccountType.",
                    name="accountTypeID",
                    example = "100000000000003",
                    required = true)
            @RequestParam("accountTypeID") final Long accountTypeID
    ){
        Integer intToPass =0;
        try{
            intToPass = Integer.parseInt(subtractValue);
        }catch (NumberFormatException e){
            LOGGER.error("Remove Value parse operation failed", e);
        }
        LOGGER.info("Value of SubtractValue {}", subtractValue);
        LOGGER.info("Value of MemberID {}", memberID);
        LOGGER.info("Value of AccountTypeID {}", accountTypeID);

        MemberAccountDto userAccount = modifyMemberAccountFlow.removeCurrency(intToPass, memberID, accountTypeID);
        LOGGER.info("Update operation completed successfully");
        GeneralResponse<MemberAccountDto> response = new GeneralResponse<>(true, userAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("add/{add}")
    @ApiOperation(value = "Add value from the UserAccount",
            notes="")
    @ApiResponses(value={
            @ApiResponse(code=201, message = "Account Type Successfully Updated", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberAccountDto>>addUserAccount(
            @ApiParam(value="Transaction Value",
                    name="add",
                    example = "100",
                    required = true)
            @PathVariable("add") final String addValue,

            @ApiParam(value = "The MemberID that uniquely identifies the UserAccountOwner.",
                    name = "memberID",
                    example = "12345",
                    required = true)
            @RequestParam("memberID") final Long memberID,

            @ApiParam(value = "The AccountTypeID that uniquely identifies the AccountType.",
                    name="accountTypeID",
                    example = "100000000000003",
                    required = true)
            @RequestParam("accountTypeID") final Long accountTypeID
    ){
        Integer intToPass =0;
        try{
            intToPass = Integer.parseInt(addValue);
        }catch (NumberFormatException e){
            LOGGER.error("Add Value parse operation failed", e);
        }
        LOGGER.info("Value of AddValue {}", addValue);
        LOGGER.info("Value of MemberID {}", memberID);
        LOGGER.info("Value of AccountTypeID {}", accountTypeID);

        MemberAccountDto memberAccount = modifyMemberAccountFlow.addCurrency(intToPass, memberID, accountTypeID);
        LOGGER.info("Update operation completed successfully");
        GeneralResponse<MemberAccountDto> response = new GeneralResponse<>(true, memberAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}