package vn.viettuts.qlsv.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "financial")
@XmlAccessorType(XmlAccessType.FIELD)
public class Financial implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    @XmlElement(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate date;
    private String type;
    private String details;
    private int amount;

    public Financial() {
    }

    public Financial(int id, LocalDate date, String type, String details, int amount) {
        super();
        this.id = id;
        this.date = date;
        this.type = type;
        this.details = details;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String address) {
        this.details = address;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
