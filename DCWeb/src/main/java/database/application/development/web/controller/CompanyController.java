package database.application.development.web.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@CrossOrigin
@RestController
@RequestMapping("apartments")
@Api(tags = "Company", description="Operations about Company")
@Slf4j
public class CompanyController {
}
