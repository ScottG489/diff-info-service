package dis.resource

import com.codahale.metrics.annotation.Timed
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import udp.parse.UnifiedDiff
import udp.parse.UnifiedDiffParser
import udp.parse.util.UnifiedDiffParserCreator

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/info")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
@Slf4j
class InfoResource extends BaseResource {
    @POST
    @Timed
    Response doAction(String diffContent) {
        log.info("Info resource triggered.")

        UnifiedDiffParser unifiedDiffParser =
                UnifiedDiffParserCreator.getInstance().create()
        List<UnifiedDiff> unifiedDiffs = unifiedDiffParser.parse(diffContent)

        return Response.ok()
                // TODO: Is this safe?
                .header("Access-Control-Allow-Origin", "*")
                .entity(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(unifiedDiffs.get(0))).build()
    }
}
