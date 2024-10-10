
package com.demo.types;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Product
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pId",
    "prodName",
    "price",
    "colors"
})
public class Product {

    /**
     * The unique identifier for a product
     * (Required)
     * 
     */
    @JsonProperty("pId")
    @JsonPropertyDescription("The unique identifier for a product")
    private Integer pId;
    /**
     * Name of the product
     * (Required)
     * 
     */
    @JsonProperty("prodName")
    @JsonPropertyDescription("Name of the product")
    private String prodName;
    /**
     * The price of the product
     * (Required)
     * (Required)
     * 
     */
    @JsonProperty("price")
    @JsonPropertyDescription("The price of the product")
    private Double price;
    /**
     * colors available for the product
     * 
     */
    @JsonProperty("colors")
    @JsonPropertyDescription("colors available for the product")
    private List<String> colors = new ArrayList<String>();

    /**
     * The unique identifier for a product
     * (Required)
     * 
     */
    @JsonProperty("pId")
    public Integer getPId() {
        return pId;
    }

    /**
     * The unique identifier for a product
     * (Required)
     * 
     */
    @JsonProperty("pId")
    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public Product withPId(Integer pId) {
        this.pId = pId;
        return this;
    }

    /**
     * Name of the product
     * (Required)
     * 
     */
    @JsonProperty("prodName")
    public String getProdName() {
        return prodName;
    }

    /**
     * Name of the product
     * (Required)
     * 
     */
    @JsonProperty("prodName")
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Product withProdName(String prodName) {
        this.prodName = prodName;
        return this;
    }

    /**
     * The price of the product
     * (Required)
     * (Required)
     * 
     */
    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    /**
     * The price of the product
     * (Required)
     * (Required)
     * 
     */
    @JsonProperty("price")
    public void setPrice(Double price) {
        this.price = price;
    }

    public Product withPrice(Double price) {
        this.price = price;
        return this;
    }

    /**
     * colors available for the product
     * 
     */
    @JsonProperty("colors")
    public List<String> getColors() {
        return colors;
    }

    /**
     * colors available for the product
     * 
     */
    @JsonProperty("colors")
    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public Product withColors(List<String> colors) {
        this.colors = colors;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("pId", pId).append("prodName", prodName).append("price", price).append("colors", colors).toString();
    }

}
