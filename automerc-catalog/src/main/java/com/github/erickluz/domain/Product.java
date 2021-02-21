package com.github.erickluz.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Product") 
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonProperty("idProduct")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idProduct;
	@Column(nullable = false)
    public String name;
    @Column(nullable = false)
    public BigDecimal price;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="id_usuario", nullable = false)
    public Long idUser;

    public Product(){
        
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
	public Long getUser() {
		return idUser;
	}

	public void setUser(Long idUser) {
		this.idUser = idUser;
	}

	@Override
    public String toString() {
        return "Product [id=" + idProduct + ", name=" + name + ", price=" + price + "]";
    }
}