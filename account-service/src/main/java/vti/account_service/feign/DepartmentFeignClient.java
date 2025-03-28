package vti.account_service.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service", path = "/api/v1/departments")
@RibbonClient(name = "department-service")
public interface DepartmentFeignClient {

    @GetMapping("/name/{id}")
    public String getDepartmentNameById(@PathVariable("id") Integer id);
}
