package exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsufficientCreditsException extends RuntimeException {
    String msg;
}
