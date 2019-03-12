package fi.jyu.soa.rest.security.demo4.repositories;

import fi.jyu.soa.rest.security.demo4.models.Address;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository extends BaseRepository {

    public AddressRepository() {
        super(Address.class);
    }

    public Address update(Address address) {

        UpdateOperations<Address> update = super.createOperations();

        if (address.getPostalCode() != null) {

            update.set("postalCode", address.getPostalCode());
        }

        if (address.getStreetAddress() != null) {

            update.set("streetAddress", address.getStreetAddress());
        }

        if (address.getPostOffice() != null) {

            update.set("postOffice", address.getPostOffice());
        }

        UpdateResults updateResults = super.update(address, update);
        return (Address) super.read(address.get_id());
    }
}
