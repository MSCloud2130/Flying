//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.09.25 at 10:33:19 PM COT 
//


package com.flying.seekerClient.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Product complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Product"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="_id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="transport" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="accommodation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="food_service" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="isOnOffer" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="place_arrival" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="place_depature" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="img" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="categories" type="{http://gen.seeker.flying.com}ArrayOfString"/&gt;
 *         &lt;element name="reviews" type="{http://gen.seeker.flying.com}ArrayOfReviews"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Product", propOrder = {
    "id",
    "name",
    "transport",
    "accommodation",
    "foodService",
    "price",
    "date",
    "description",
    "isOnOffer",
    "placeArrival",
    "placeDepature",
    "img",
    "categories",
    "reviews"
})
public class Product {

    @XmlElement(name = "_id", required = true)
    protected String id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String transport;
    @XmlElement(required = true)
    protected String accommodation;
    @XmlElement(name = "food_service", required = true)
    protected String foodService;
    protected double price;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(required = true)
    protected String description;
    protected boolean isOnOffer;
    @XmlElement(name = "place_arrival", required = true)
    protected String placeArrival;
    @XmlElement(name = "place_depature", required = true)
    protected String placeDepature;
    @XmlElement(required = true)
    protected String img;
    @XmlElement(required = true)
    protected ArrayOfString categories;
    @XmlElement(required = true)
    protected ArrayOfReviews reviews;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the transport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransport() {
        return transport;
    }

    /**
     * Sets the value of the transport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransport(String value) {
        this.transport = value;
    }

    /**
     * Gets the value of the accommodation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccommodation() {
        return accommodation;
    }

    /**
     * Sets the value of the accommodation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccommodation(String value) {
        this.accommodation = value;
    }

    /**
     * Gets the value of the foodService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFoodService() {
        return foodService;
    }

    /**
     * Sets the value of the foodService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFoodService(String value) {
        this.foodService = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the isOnOffer property.
     * 
     */
    public boolean isIsOnOffer() {
        return isOnOffer;
    }

    /**
     * Sets the value of the isOnOffer property.
     * 
     */
    public void setIsOnOffer(boolean value) {
        this.isOnOffer = value;
    }

    /**
     * Gets the value of the placeArrival property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceArrival() {
        return placeArrival;
    }

    /**
     * Sets the value of the placeArrival property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceArrival(String value) {
        this.placeArrival = value;
    }

    /**
     * Gets the value of the placeDepature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceDepature() {
        return placeDepature;
    }

    /**
     * Sets the value of the placeDepature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceDepature(String value) {
        this.placeDepature = value;
    }

    /**
     * Gets the value of the img property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImg() {
        return img;
    }

    /**
     * Sets the value of the img property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImg(String value) {
        this.img = value;
    }

    /**
     * Gets the value of the categories property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getCategories() {
        return categories;
    }

    /**
     * Sets the value of the categories property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setCategories(ArrayOfString value) {
        this.categories = value;
    }

    /**
     * Gets the value of the reviews property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfReviews }
     *     
     */
    public ArrayOfReviews getReviews() {
        return reviews;
    }

    /**
     * Sets the value of the reviews property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfReviews }
     *     
     */
    public void setReviews(ArrayOfReviews value) {
        this.reviews = value;
    }

}
