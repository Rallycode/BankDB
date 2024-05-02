package com.onlinebanking.BankDB.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_history") // Changed table name to follow convention
public class Transaction {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "transaction_id", columnDefinition = "VARCHAR(36)") // Adjust column definition for UUID
    private String transactionId;

    @Column(name = "from_account")
    private Long fromAccount;

    @Column(name = "to_account")
    private Long toAccount;

    private Long amount;
    private String description;
    private String status;

    @Column(name = "transaction_timestamp")
    @CreationTimestamp
    private Date transactionTimeStamp;
}
