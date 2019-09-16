package com.shankulk.dto;

import com.shankulk.domain.Money;

public class TransferRequest {
    public long destinationAccountId;
    public Money money;

    public TransferRequest() {}

    public TransferRequest(long destinationAccountId, Money money) {
        this.destinationAccountId = destinationAccountId;
        this.money = money;
    }
}
