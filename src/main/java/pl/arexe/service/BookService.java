package pl.arexe.service;

import pl.arexe.dao.BookRepositoryDao;
import pl.arexe.entity.Book;

import javax.inject.Inject;
import javax.ws.rs.*;
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

    @GET
    @Path("/books/{ISBN}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookByISBN(@PathParam("ISBN") Long ISBN){
        Book bookByISBN = bookDao.getBookByISBN(ISBN);
        if(bookByISBN != null){
            LOGGER.info("*** Returned book with ISBN=" + ISBN);
            return Response.ok(bookByISBN).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Book not found for ISBN=" + ISBN).build();
    }

    @POST
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        Book newBook = Book.builder()
                .ISBN(book.getISBN())
                .title(book.getTitle())
                .authors(book.getAuthors())
                .releaseDate(book.getReleaseDate())
                .pages(book.getPages())
                .build();
            bookDao.addBook(newBook);
        LOGGER.info("*** New book added: " + newBook);
        return getBooks();
    }

    @PUT
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book) {
        if (bookDao.getBookByISBN(book.getISBN()) != null) {
            bookDao.updateBook(book);
            LOGGER.info("*** Book updated: " + book);
            return Response.ok(book).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Unable to update book: " + book).build();
    }

    @DELETE
    @Path("/books/{ISBN}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBook(@PathParam("ISBN") Long ISBN) {
        if (bookDao.getBookByISBN(ISBN) != null) {
            bookDao.deleteBook(ISBN);
            LOGGER.info("*** Deleted book with ISBN= " + ISBN);
            return Response.ok("Deleted book with ISBN=" + ISBN).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Book not found for ISBN=" + ISBN).build();
    }
}
