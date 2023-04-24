package vn.viettuts.qlsv.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "financials")
@XmlAccessorType(XmlAccessType.FIELD)
public class FinancialXML {
    
    private List<Financial> financial;

    public List<Financial> getFinancial() {
        return financial;
    }

    public void setFinancial(List<Financial> financial) {
        this.financial = financial;
    }
}
