package com.example.zuzia.oszczedzanie2.Model.Entities;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingsEntity extends Entity {
    private long desirable_id;
    private BigDecimal amount;
    private Date created_at;

    public SavingsEntity(long id, long desirable_id, BigDecimal amount, Date created_at) {
        super(id);
        this.desirable_id = desirable_id;
        this.amount = amount;
        this.created_at = created_at;
    }

    public SavingsEntity() {
        super();
    }

    public SavingsEntity(long id, long desirable_id, int amount, String created_at) {
        super(id);
        this.amount = new BigDecimal(amount);
        this.desirable_id = desirable_id;

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.created_at = format.parse(created_at);
        } catch (ParseException e) {
            System.out.println("Wrong Date format in database");
        }
    }

    public SavingsEntity(long id) {
        super(id);
    }

    public long getDesirableId() {
        return desirable_id;
    }

    public void setDesirableId(long desirable_id) {
        this.desirable_id = desirable_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", desirable id: " + getDesirableId() +
                ", amount: " + getAmount() +
                ", created at: " + getCreatedAt();
    }
}
