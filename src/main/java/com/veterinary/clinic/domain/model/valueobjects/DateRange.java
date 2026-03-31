import com.veterinary.clinic.domain.exception.InvalidReservationException;

import java.time.LocalDate;

public class DateRange {

    private final LocalDate checkIn;
    private final LocalDate checkOut;

    public DateRange(LocalDate checkIn, LocalDate checkOut) {

        if (checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        if (checkOut.isBefore(checkIn)) {
            throw new InvalidReservationException();
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }
}