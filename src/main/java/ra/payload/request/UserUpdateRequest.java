package ra.payload.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserUpdateRequest {
    private String fullName;
    private String address;
    private boolean sex;
    private String birthDate;
    private String phoneNumber;
}
