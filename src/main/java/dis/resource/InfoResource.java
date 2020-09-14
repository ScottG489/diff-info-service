package dis.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import udp.parse.UnifiedDiff;
import udp.parse.UnifiedDiffParser;
import udp.parse.util.UnifiedDiffParserCreator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/info")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
@Slf4j
public class InfoResource extends BaseResource {
    @POST
    @Timed
    public Response doAction(String diffContent) throws JsonProcessingException {
        log.info("Info resource triggered.");

        // TODO: This is still published as groovy. Publish the project as Java
        UnifiedDiffParser unifiedDiffParser = UnifiedDiffParserCreator.getInstance().create();
        List<UnifiedDiff> unifiedDiffs = unifiedDiffParser.parse(diffContent);

        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .entity(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(unifiedDiffs.get(0)))
                .build();
    }

}
