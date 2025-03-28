package vti.account_service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import vti.account_service.entity.Account;
import vti.account_service.entity.Department;
import vti.account_service.repository.IAccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository acRepository;

    @Autowired
    private RestClient restClient;

    @Value("${department.service.path}")
    private String departmentServicePath;

    @Override
    public List<Account> getListAccounts() {
        return acRepository.findAll();
    }

    @Override
    public String getDepartmentByAccountId(int accountId) {
        Optional<Account> accountOpt = acRepository.findById(accountId);
        if (accountOpt.isEmpty()) {
            return null;
        }

        Account account = accountOpt.get();
        Department department = account.getDepartment();

        if (department == null) {
            return null;
        }

        int departmentId = department.getId();
        String departmentName = restClient.get()
                .uri(departmentServicePath + departmentId)
                .retrieve()
                .body(String.class);

        return departmentName;
    }

}
