package com.ankush003.jshare.dao.impl;

// entity for persisting payment details (persistence layer)
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table(name = "payments")
//public class PaymentDaoImpl {
//    @Id
//    private Long paymentId;
//    private String userId;
//    private String friendId;
//    private String description;
//    private String amount;
//    private Timestamp paymentDate;
//    private String status;
//}

import com.ankush003.jshare.dao.PaymentDao;
import com.ankush003.jshare.domain.Payment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDaoImpl implements PaymentDao {

    private final JdbcTemplate jdbcTemplate;

    public PaymentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static class PaymentRowMapper impements RowMapper<Payement> {

        @Override
        public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Payment.builder()
                    .id(rs.getLong("payment_id"))
                    .userId(rs.getString("user_id"))
                    .friendId(rs.getString("friend_id"))
                    .description(rs.getString("description"))
                    .amount(rs.getString("amount"))
                    .paymentDate(rs.getTimestamp("payment_date"))
                    .status(rs.getString("status"))
                    .build();
        }
    }


}
