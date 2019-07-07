package com.kodila.hibernate.invoice;

import com.kodila.hibernate.invoice.dao.InvoiceDao;
import com.kodila.hibernate.invoice.dao.ItemDao;
import com.kodila.hibernate.invoice.dao.ProductDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InoviceDaoTestSuite {

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    ProductDao productDao;

    private static final String NUMBER = " NUMBER";

    @Test
    public void testInvoiceDaoSave(){
    //given
        Item item = new Item(new BigDecimal(10), 2, new BigDecimal(5));
        Item item1 = new Item(new BigDecimal(15), 5, new BigDecimal(2));
        Item item2 = new Item(new BigDecimal(43), 2, new BigDecimal(20));

        //Many Items to One Product
        Product product = new Product("banana");
        product.getItems().add(item);
        product.getItems().add(item1);
        product.getItems().add(item2);

        Invoice invoice = new Invoice(NUMBER);
        invoice.getItems().add(item);
        invoice.getItems().add(item1);
        invoice.getItems().add(item2);

        //when
        productDao.save(product);
        int productId = product.getId();
        invoiceDao.save(invoice);
        int invoiceId = invoice.getId();
//
        //then
        Assert.assertNotEquals(0,productId);
        Assert.assertNotEquals(0,invoiceId);
    }
}
