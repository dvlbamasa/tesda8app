package com.tesda8.region8.web.restcontroller.audit;

import com.tesda8.region8.audit.model.dto.AuditLogFilter;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audit")
public class AuditRestController {

    private final AuditLogService auditLogService;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public Page<AuditLog> findAllAuditLog(@RequestParam("pageSize") int pageSize,
                                          @RequestParam("pageNumber") int pageNumber,
                                          @RequestBody AuditLogFilter auditLogFilter) {
        return auditLogService.findAll(pageNumber, pageSize, auditLogFilter);
    }
}
