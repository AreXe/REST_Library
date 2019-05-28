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

    /**
     * List of the books
     * Usage: GET /library-api/api/books
     * @return book list as JSON
     */
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        LOGGER.info("*** Returned book list");
        return Response.ok(bookDao.getBookList()).build();
    }

    /**
     * Get book by ISBN
     * Usage: GET /library-api/api/books/{ISBN}
     * @param ISBN book ID as Long number type (ISBN)
     * @return book as JSON
     */
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

    /**
     * Adds the book to the list
     * Usage: POST /library-api/api/books
     * Example JSON for Body:
     * {
     *         "ISBN": 9780136597230,
     *         "authors": [
     *             "Bruce Eckel"
     *         ],
     *         "pages": 1098,
     *         "releaseDate": 1998,
     *         "title": "Thinking in Java"
     * }
     * @param book object you want you add as JSON
     * @return book list as JSON
     */
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

    /**
     * Updates the book by the ISBN
     * Usage: PUT /library-api/api/books
     * Example JSON for Body:
     * {
     *         "ISBN": 9782255421405,
     *         "authors": [
     *             "Michalak Hanna",
     *             "Pyrak Stefan"
     *         ],
     *         "pages": 320,
     *         "releaseDate": 2013,
     *         "title": "Budynki jedorodzinne. Projektowanie konstrukcyjne, realizacja, użytkowanie."
     * }
     * @param book object you want to replace as JSON, checked by the ISBN number
     * @return updated book as JSON
     */
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

    /**
     * Deletes the book with given ISBN number on path param
     * Usage: DELETE /library-api/api/books/{ISBN}
     * @param ISBN book ID as Long number type (ISBN)
     * @return confirmation of deletion with book ISBN number
     */
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
