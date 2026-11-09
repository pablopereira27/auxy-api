package br.com.fastdevelopment.auxy.infrastructure.tenant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

public class TenantContextInterceptor implements HandlerInterceptor {
    private static final String TENANT_HEADER = "X-Tenant-Id";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        String tenantHeader = request.getHeader(TENANT_HEADER);

        if (tenantHeader == null || tenantHeader.isBlank()) {
            response.sendError(
                HttpStatus.BAD_REQUEST.value(),
                "Missing X-Tenant-Id header"
            );

            return false;
        }

        try {
            Long tenantId = Long.valueOf(tenantHeader);

            if (tenantId <= 0) {
                throw new NumberFormatException();
            }

            TenantContext.setTenantId(tenantId);

            return true;

        } catch (NumberFormatException exception) {
            response.sendError(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid X-Tenant-Id header"
            );

            return false;
        }
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception exception
    ) {
        TenantContext.clear();
    }
}
