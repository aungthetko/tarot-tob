package com.tarot.tarotwebapp.model.order;

import com.tarot.tarotwebapp.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_tb")
public class Order {

    @Id
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime orderAt;
    private Boolean isConfirmed;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String address;

}
