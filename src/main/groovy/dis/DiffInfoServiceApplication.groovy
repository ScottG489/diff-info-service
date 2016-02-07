package dis

import dis.resource.InfoResource
import dis.resource.Resource
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import dis.health.HealthyHealthCheck

public class DiffInfoServiceApplication extends Application<DiffInfoServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new DiffInfoServiceApplication().run(args);
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
    public void run(DiffInfoServiceConfiguration configuration,
                    Environment environment) {
        final List<Resource> resources = [
                new InfoResource()
        ]
        registerAll(environment, resources)

        final HealthyHealthCheck healthCheck =
                new HealthyHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

    static void registerAll(Environment environment, ArrayList<Resource> resources) {
        for (Resource resource : resources) {
            environment.jersey().register(resource);
        }
    }
}
