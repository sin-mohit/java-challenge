package jp.co.axa.apidemo.exception;

/**
 * Employee not found custome exception
 */
public class EmployeeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(Long id) {
      super("Could not find employee " + id);
    }
  }