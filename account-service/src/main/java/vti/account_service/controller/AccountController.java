package vti.account_service.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vti.account_service.dto.AccountDTO;
import vti.account_service.entity.Account;
import vti.account_service.feign.DepartmentFeignClient;
import vti.account_service.service.IAccountService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {
    @Autowired
    private IAccountService acService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartmentFeignClient departmentFeignClient;

    @GetMapping
    public List<AccountDTO> getListAccounts() {
        List<Account> accounts = acService.getListAccounts();

        return modelMapper.map(
                accounts,
                new TypeToken<List<AccountDTO>>() {
                }.getType());
    }

    @GetMapping("/department/{accountId}")
    public String getDepartmentByAccountId(@PathVariable("accountId") int accountId) {
        return acService.getDepartmentByAccountId(accountId);
    }

    @GetMapping("/department/name/{departmentId}")
    public String getDepartmentNameById(@PathVariable("departmentId") Integer departmentId) {
        return departmentFeignClient.getDepartmentNameById(departmentId);
    }

}
