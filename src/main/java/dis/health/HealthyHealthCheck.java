package dis.health;

import com.codahale.metrics.health.HealthCheck;

public class HealthyHealthCheck extends HealthCheck {
    public HealthyHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

    private final String template;
}
