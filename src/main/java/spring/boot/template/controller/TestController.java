package spring.boot.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.template.mapper.DataSourceMapper;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @GetMapping("/name")
    public List<String> getName() {

        return dataSourceMapper.getUser();
    }
}
