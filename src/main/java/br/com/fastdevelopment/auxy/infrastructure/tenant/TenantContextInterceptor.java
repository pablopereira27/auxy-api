package br.com.fastdevelopment.auxy.infrastructure.tenant;

import br.com.fastdevelopment.auxy.infrastructure.error.InvalidTenantException;
import br.com.fastdevelopment.auxy.infrastructure.error.MissingTenantException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
            throw new MissingTenantException(
                "X-Tenant-Id header is required"
            );
        }

        try {
            Long tenantId = Long.valueOf(tenantHeader);

            if (tenantId <= 0) {
                throw new NumberFormatException();
            }

            TenantContext.setTenantId(tenantId);

            return true;

        } catch (NumberFormatException exception) {
            throw new InvalidTenantException(
                "X-Tenant-Id header must contain a positive number"
            );
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
