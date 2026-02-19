package com.qtrip.models;

/**
 * Booking model for API requests/responses.
 *
 * @author Natarajan M
 */
public class Booking {

    private String id;
    private String name;
    private String date;
    private int person;
    private String adventure;
    private String userId;
    private String transactionId;
    private String status;

    public Booking() {}

    public static BookingBuilder builder() {
        return new BookingBuilder();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public int getPerson() { return person; }
    public void setPerson(int person) { this.person = person; }

    public String getAdventure() { return adventure; }
    public void setAdventure(String adventure) { this.adventure = adventure; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Booking{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", date='" + date + '\'' +
            ", person=" + person +
            ", adventure='" + adventure + '\'' +
            ", transactionId='" + transactionId + '\'' +
            ", status='" + status + '\'' +
            '}';
    }

    /**
     * Builder class for Booking.
     */
    public static class BookingBuilder {
        private final Booking booking = new Booking();

        public BookingBuilder name(String name) {
            booking.setName(name);
            return this;
        }

        public BookingBuilder date(String date) {
            booking.setDate(date);
            return this;
        }

        public BookingBuilder person(int person) {
            booking.setPerson(person);
            return this;
        }

        public BookingBuilder adventure(String adventure) {
            booking.setAdventure(adventure);
            return this;
        }

        public BookingBuilder userId(String userId) {
            booking.setUserId(userId);
            return this;
        }

        public Booking build() {
            return booking;
        }
    }
}

