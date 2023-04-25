package vn.viettuts.qlsv.dao;

import vn.viettuts.qlsv.entity.Financial;
import vn.viettuts.qlsv.entity.FinancialXML;
import vn.viettuts.qlsv.utils.FileUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * FinancialDao class
 */
public class FinancialDao {
    private static final String FINANCIAL_FILE_NAME = "src\\main\\java\\vn\\viettuts\\qlsv\\financial.xml";
    private List<Financial> listFinancials;

    public FinancialDao() {
        this.listFinancials = readListFinancials();
        if (listFinancials == null) {
            listFinancials = new ArrayList<>();
        }
    }

    /**
     * Lưu các đối tượng financial vào file financial.xml
     */
    public void writeListFinancials(List<Financial> financials) {
        FinancialXML financialXML = new FinancialXML();
        financialXML.setFinancial(financials);
        FileUtils.writeXMLtoFile(FINANCIAL_FILE_NAME, financialXML);
    }

    /**
     * Đọc các đối tượng financial từ file financial.xml
     */
    public List<Financial> readListFinancials() {
        List<Financial> list = new ArrayList<>();
        FinancialXML financialXML = (FinancialXML) FileUtils.readXMLFile(
                FINANCIAL_FILE_NAME, FinancialXML.class);
        if (financialXML != null) {
            list = financialXML.getFinancial();
        }
        return list;
    }
    

    /**
     * thêm financial vào listFinancials và lưu listFinancials vào file
     */
    public void add(Financial financial) {
        int id = 1;
        sortFinancialByID();
        id=listFinancials.get(listFinancials.size()-1).getId()+1;
        financial.setId(id);
        listFinancials.add(financial);
        writeListFinancials(listFinancials);
    }

    /**
     * cập nhật financial vào listFinancials và lưu listFinancials vào file
     */
    public void edit(Financial financial) {
        int size = listFinancials.size();
        for (int i = 0; i < size; i++) {
            if (listFinancials.get(i).getId() == financial.getId()) {
                listFinancials.get(i).setDate(financial.getDate());
                listFinancials.get(i).setType(financial.getType());
                listFinancials.get(i).setDetails(financial.getDetails());
                listFinancials.get(i).setAmount(financial.getAmount());
                writeListFinancials(listFinancials);
                break;
            }
        }
    }

    /**
     * xóa financial từ listFinancials và lưu listFinancials vào file
     */
    public void delete(Financial financial) {
        boolean isFound = false;
        for (Financial listFinancial : listFinancials) {
            if (listFinancial.getId() == financial.getId()) {
                financial = listFinancial;
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listFinancials.remove(financial);
            writeListFinancials(listFinancials);
        }
    }

    /**
     * sắp xếp danh sách financial theo Date theo tứ tự tăng dần
     */
    public void sortFinancialByDate() {
        listFinancials.sort(Comparator.comparing(Financial::getDate));
    }

    /**
     * sắp xếp danh sách financial theo Amount theo tứ tự tăng dần
     */
    public void sortFinancialByAmount() {
        listFinancials.sort((financial1, financial2) -> {
            if (financial1.getAmount() > financial2.getAmount()) {
                return 1;
            }
            return -1;
        });
    }

    /**
     * sắp xếp danh sách financial theo ID theo tứ tự tăng dần (Theo thứ tự thêm vào)
     */
    public void sortFinancialByID() {
        listFinancials.sort((financial1, financial2) -> {
            if (financial1.getId() > financial2.getId()) {
                return 1;
            }
            return -1;
        });
    }

    public List<Financial> getListFinancials() {
        sortFinancialByID();
        return listFinancials;
    }

    public void setListFinancials(List<Financial> listFinancials) {
        this.listFinancials = listFinancials;
    }
}