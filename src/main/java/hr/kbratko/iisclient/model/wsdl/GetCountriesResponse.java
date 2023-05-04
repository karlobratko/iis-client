
package hr.kbratko.iisclient.model.wsdl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="countries" type="{http://www.kbratko.hr/soapcountries/schema}countries"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
  "countries"
})
@XmlRootElement(name = "getCountriesResponse", namespace = "http://www.kbratko.hr/soapcountries/schema")
public class GetCountriesResponse {

  @XmlElement(namespace = "http://www.kbratko.hr/soapcountries/schema", required = true)
  protected Countries countries;

  /**
   * Gets the value of the countries property.
   *
   * @return
   *     possible object is
   *     {@link Countries }
   *
   */
  public Countries getCountries() {
    return countries;
  }

  /**
   * Sets the value of the countries property.
   *
   * @param value
   *     allowed object is
   *     {@link Countries }
   *
   */
  public void setCountries(Countries value) {
    this.countries = value;
  }

}
