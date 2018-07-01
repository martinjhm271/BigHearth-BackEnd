package escuelaing.edu.co.bighearth.controller;

import escuelaing.edu.co.bighearth.model.Credentials;
import escuelaing.edu.co.bighearth.model.Organization;
import escuelaing.edu.co.bighearth.security.SHA1;
import escuelaing.edu.co.bighearth.model.Volunteer;
import escuelaing.edu.co.bighearth.service.OrganizationService;
import escuelaing.edu.co.bighearth.service.ServicesException;
import escuelaing.edu.co.bighearth.service.VolunteerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private OrganizationService organizationService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Token login(@RequestBody Credentials login) throws ServletException {

        String jwtToken = "";

        if (login.getEmail() == null || login.getPassword() == null) {
            throw new ServletException("Please fill in email and password");
        }

        String email = login.getEmail();
        String password = login.getPassword();

        Volunteer c1 = null;
        try {
            c1 = volunteerService.getVolunteerByEmail(email);
        } catch (ServicesException e) {
            throw new ServletException("Invalid login. Please check your email and password. Error 0X001");
        }
        Organization c2 = null;
        try {
            c2 = organizationService.getOrganizationbyEmail(email);
        } catch (ServicesException e) {
            throw new ServletException("Invalid login. Please check your email and password. Error 0X002");
        }

        String pwd;
        String roltemp;

        if (c1 == null && c2==null) {
            throw new ServletException("Invalid login. Please check your email and password. Error 0X003");
        }
        else if(c1!=null){
            pwd= c1.getPassword();
            roltemp = "volunteer";
        }
        else{
            pwd= c2.getPassword();
            roltemp = "organization";
        }

        try {
            password = SHA1.generateHash(password);
        } catch (Exception e) {
        }

        if (!password.equals(pwd)) {
            System.out.println(password);
            System.out.println(pwd);
            throw new ServletException("Invalid login. Please check your email and password. Error 0X004");
        }

        jwtToken = Jwts.builder().setSubject(email).claim("roles", roltemp).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        return new Token(jwtToken, roltemp);
    }



    public class Token {

        private String access_token;
        private String rol;

        public Token(String access_token, String rol) {
            this.access_token = access_token;
            this.rol = rol;
        }

        public String getAccessToken() {
            return access_token;
        }

        public void setAccessToken(String access_token) {
            this.access_token = access_token;
        }

        public String getrol() {
            return rol;
        }

        public void setrol(String rol) {
            this.rol = rol;
        }
    }




}
