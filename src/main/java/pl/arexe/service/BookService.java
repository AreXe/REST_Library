package pl.arexe.service;

import pl.arexe.dao.BookRepositoryDao;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * Main service class for the "library-api" RESTful service
 * @author Arkadiusz Szlachta
 */
@Path("/")
public class BookService {

    private static final Logger LOGGER = Logger.getLogger(BookService.class.getName());

    @Inject
    BookRepositoryDao bookDao;

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        LOGGER.info("*** Returned book list");
        return Response.ok(bookDao.getBookList()).build();
    }
}
