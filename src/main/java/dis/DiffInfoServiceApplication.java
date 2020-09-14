package dis;

import dis.health.HealthyHealthCheck;
import dis.resource.InfoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DiffInfoServiceApplication extends Application<DiffInfoServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new dis.DiffInfoServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "diff-info-service";
    }

    @Override
    public void initialize(Bootstrap<DiffInfoServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DiffInfoServiceConfiguration configuration, Environment environment) {
        environment.jersey().register(new InfoResource());
        final HealthyHealthCheck healthCheck = new HealthyHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }
}
