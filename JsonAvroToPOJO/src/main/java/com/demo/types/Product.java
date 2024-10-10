
package com.demo.types;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


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
@Generated("jsonschema2pojo")
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
    public Integer getpId() {
        return pId;
    }

    /**
     * The unique identifier for a product
     * (Required)
     * 
     */
    @JsonProperty("pId")
    public void setpId(Integer pId) {
        this.pId = pId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Product.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("pId");
        sb.append('=');
        sb.append(((this.pId == null)?"<null>":this.pId));
        sb.append(',');
        sb.append("prodName");
        sb.append('=');
        sb.append(((this.prodName == null)?"<null>":this.prodName));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("colors");
        sb.append('=');
        sb.append(((this.colors == null)?"<null>":this.colors));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
