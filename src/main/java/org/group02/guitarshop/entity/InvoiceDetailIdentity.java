package org.group02.guitarshop.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class InvoiceDetailIdentity implements Serializable {

    @NotNull
    @Column(name = "Id_Invoice")
    private int idInvoice;

    @NotNull
    @Column(name = "Id_Product")
    private int idProduct;

    public InvoiceDetailIdentity() { }

    public InvoiceDetailIdentity(int idInvoice, int idProduct) {
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

}
