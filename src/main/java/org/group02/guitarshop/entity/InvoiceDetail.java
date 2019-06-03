package org.group02.guitarshop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "INVOICE_DETAIL")
@IdClass(InvoiceDetailPK.class)
public class InvoiceDetail {
    @Id
    @Column(name = "Id_Invoice", nullable = false)
    private int idInvoice;

    @Id
    @Column(name = "Id_Product", nullable = false)
    private int idProduct;

    @Column(name = "Quantity", nullable = true)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "Id_Invoice", referencedColumnName = "Id", nullable = false, insertable = false, updatable = false)
    private Invoice invoiceByIdInvoice;

    @ManyToOne
    @JoinColumn(name = "Id_Product", referencedColumnName = "Id", nullable = false, insertable = false, updatable = false)
    private Product productByIdProduct;

}
