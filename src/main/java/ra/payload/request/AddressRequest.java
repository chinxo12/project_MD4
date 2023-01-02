package ra.payload.request;

import lombok.Data;

@Data
public class AddressRequest {
    private String fullName;
    private String address;
    private String phoneNumber;
}
