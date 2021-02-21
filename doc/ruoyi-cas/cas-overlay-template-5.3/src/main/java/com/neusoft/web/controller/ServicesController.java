package com.neusoft.web.controller;

import org.apereo.cas.services.RegexRegisteredService;
import org.apereo.cas.services.ReturnAllAttributeReleasePolicy;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;

@RestController
public class ServicesController {
    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    @GetMapping(value = "/addClient/{serviceId}/{id}")
    public String addClient(@PathVariable("serviceId") String serviceId, @PathVariable("id") int id) throws IOException {
        String a="^(https|imaps|http)://"+serviceId+".*";
        RegexRegisteredService service = new RegexRegisteredService();
        ReturnAllAttributeReleasePolicy re = new ReturnAllAttributeReleasePolicy();
        service.setServiceId(a);
        service.setId(id);
        service.setAttributeReleasePolicy(re);
        service.setName("login");
        service.setLogoutUrl(new URL("http://"+serviceId));//这个是为了单点登出而作用的
        servicesManager.save(service);
        servicesManager.load();
        return "success";
    }
}
