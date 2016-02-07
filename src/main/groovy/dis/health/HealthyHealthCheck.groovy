package dis.health

import com.codahale.metrics.health.HealthCheck
// TODO: Without this Result can't be resolved for some reason
import com.codahale.metrics.health.HealthCheck.Result;

// TODO: Find out how to write a proper health check.
public class HealthyHealthCheck extends HealthCheck {
    private final String template;

    public HealthyHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
