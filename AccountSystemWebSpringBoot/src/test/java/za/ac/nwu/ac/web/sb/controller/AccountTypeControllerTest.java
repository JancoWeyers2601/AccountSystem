package za.ac.nwu.ac.web.sb.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;
import za.ac.nwu.ac.web.sb.controller.AccountTypeController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTypeControllerTest {

    private static final String APP_URL = "/account-system/mvc";
    private static final String ACCOUNT_TYPE_CONTROLLER_URL = APP_URL + "/account-type";

    @Mock
    private FetchAccountTypeFlow fetchAccountTypeFlow;

    @Mock
    private CreateAccountTypeFlow createAccountTypeFlow;

    @Mock
    private ModifyAccountTypeFlow modifyAccountTypeFlow;

    @InjectMocks
    private AccountTypeController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAll() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payLoad\":[{\"mnemonic\":\"MILES\",\"accountTypeName\":\"Miles account type\",\"creationDate\":[2020,1,1]},{\"mnemonic\":\"Zar\",\"accountTypeName\":\"South African Rand\",\"creationDate\":[2021,10,4]},{\"mnemonic\":\"MILES2\",\"accountTypeName\":\"MILES2 account type\",\"creationDate\":[2021,9,5]}]}" ;

        List<AccountTypeDto> accountTypes = new ArrayList<>();
        accountTypes.add(new AccountTypeDto("MILES", "Miles account type", LocalDate.parse("2020-01-01")));
        accountTypes.add(new AccountTypeDto("Zar", "South African Rand", LocalDate.parse("2021-10-04")));
        accountTypes.add(new AccountTypeDto("MILES2", "MILES2 account type", LocalDate.parse("2021-09-05")));

        when(fetchAccountTypeFlow.getAllAccountTypes()).thenReturn(accountTypes);

        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s", ACCOUNT_TYPE_CONTROLLER_URL, "all")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(fetchAccountTypeFlow, times(1)).getAllAccountTypes();
        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void create () throws Exception
    {
        String accountTypeToBeCreated = "{\"mnemonic\": \"MILES\",\"accountTypeName\": \"MILES account type\",\"creationDate\":[2021,9,05]}";
        String expectedResponse =  "{\"successful\":true,\"payLoad\":" +
                "{\"mnemonic\": \"MILES\",\"accountTypeName\": \"MILES account type\",\"creationDate\":[2021,9,05]}}";

        AccountTypeDto accountType = new AccountTypeDto("MILES","MILES account type" ,LocalDate.parse("2021-09-05"));

        when(createAccountTypeFlow.create(eq(accountType))).then(returnsFirstArg());

        MvcResult mvcResult = mockMvc.perform(post(ACCOUNT_TYPE_CONTROLLER_URL)
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(accountTypeToBeCreated)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        verify(createAccountTypeFlow, times(1)).create(eq(accountType));
        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());

    }

}