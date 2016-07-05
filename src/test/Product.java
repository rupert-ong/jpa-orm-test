package test;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="products")
public class Product {
  @Id
  @Column(name="id")
  @GeneratedValue
  private long id;

  @Column(name="productName")
  private String productName;

  public long getId(){
    return this.id;
  }

  public String getProductName(){
    return this.productName;
  }

  public void setProductName(String productName){
    this.productName = productName;
  }
}