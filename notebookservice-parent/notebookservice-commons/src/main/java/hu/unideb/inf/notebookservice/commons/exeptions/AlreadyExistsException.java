package hu.unideb.inf.notebookservice.commons.exeptions;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(final String message) {
        super(message);
    }
}
