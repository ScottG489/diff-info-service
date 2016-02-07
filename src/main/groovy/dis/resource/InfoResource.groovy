package dis.resource

import com.codahale.metrics.annotation.Timed
import dis.json.Response
import groovy.util.logging.Slf4j

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/info")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class InfoResource extends BaseResource {
    @GET
    @Timed
    @Override
    public Response doAction() {
        log.info("Info resource triggered.")

        return new Response(true, "Done")
    }
}
