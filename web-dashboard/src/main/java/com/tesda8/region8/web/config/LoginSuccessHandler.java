package com.tesda8.region8.web.config;

import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final AuditLogService auditLogService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        System.out.println("The user " + username + " has logged in.");
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        String formattedDate = LocalDateTime.now().format(formatter);

        AuditLog auditLog = new AuditLog();
        auditLog.setIsDeleted(false);
        auditLog.setAuditAction(AuditAction.LOGIN);
        auditLog.setAuditDate(LocalDateTime.now());
        auditLog.setUpdatedBy(username);
        auditLog.setUpdatedDate(LocalDateTime.now());
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Username", username);
        attributes.put("Date", formattedDate);
        attributes.put("IP Address", details.getRemoteAddress());
        auditLog.setEntityAttributes(attributes);
        auditLogService.saveAuditLog(auditLog);

        super.onAuthenticationSuccess(request, response, authentication);

    }
}
