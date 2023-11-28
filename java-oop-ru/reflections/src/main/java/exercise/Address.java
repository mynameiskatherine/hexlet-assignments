package exercise;

class Address {
    // BEGIN
    @exercise.NotNull
    @exercise.MinLength(minLength = 4)
    // END
    private String country;

    // BEGIN
    @exercise.NotNull
    // END
    private String city;

    // BEGIN
    @exercise.NotNull
    // END
    private String street;

    // BEGIN
    @exercise.NotNull
    // END
    private String houseNumber;

    private String flatNumber;

    Address(String country, String city, String street, String houseNumber, String flatNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }
}
