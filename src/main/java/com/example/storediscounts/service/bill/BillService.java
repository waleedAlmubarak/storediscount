package com.example.storediscounts.service.bill;

import com.example.storediscounts.dao.Bill;
import com.example.storediscounts.dao.User;

public interface BillService {
    double getNetPayableAmount(Bill bill, User user);
}
