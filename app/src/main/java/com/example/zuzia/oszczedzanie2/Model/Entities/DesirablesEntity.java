package com.example.zuzia.oszczedzanie2.Model.Entities;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DesirablesEntity extends Entity {

    private BigDecimal amount;
    private String type;
    private Date created_at;

    public DesirablesEntity(long id, BigDecimal amount, String type, Date created_at) {
        super(id);
        this.amount = amount;
        this.type = type;
        this.created_at = created_at;
    }

    public DesirablesEntity(BigDecimal amount, String type, Date created_at) {
        this.amount = amount;
        this.type = type;
        this.created_at = created_at;
    }

    public DesirablesEntity() {
        super();
    }

    public DesirablesEntity(long id, int amount, String type, String created_at) {
        super(id);
        this.amount = new BigDecimal(amount);
        this.type = type;
        setCreatedAt(created_at);
    }

    public DesirablesEntity(long id) {
        super(id);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public void setCreatedAt(String created_at) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.created_at = format.parse(created_at);
        } catch (ParseException e) {
            System.out.println("Wrong Date format in database");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                ", amount: " + getAmount() +
                ", type: " + getType() +
                ", created at: " + getCreatedAt();
    }

}
