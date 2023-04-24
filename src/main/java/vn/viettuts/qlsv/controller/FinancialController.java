package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vn.viettuts.qlsv.dao.FinancialDao;
import vn.viettuts.qlsv.entity.Financial;
import vn.viettuts.qlsv.view.FinancialView;

public class FinancialController {
    private final FinancialDao financialDao;
    private final FinancialView financialView;

    public FinancialController(FinancialView view) {
        this.financialView = view;
        financialDao = new FinancialDao();

        view.addAddFinancialListener(new AddFinancialListener());
        view.addEditFinancialListener(new EditFinancialListener());
        view.addDeleteFinancialListener(new DeleteFinancialListener());
        view.addClearListener(new ClearFinancialListener());
        view.addSortFinancialAmountListener(new SortFinancialAmountListener());
        view.addSortFinancialDateListener(new SortFinancialDateListener());
        view.addSortFinancialIdListener(new SortFinancialIdListener());
        view.addSearchFinancialDateListener(new SearchFinancialDateListener());
        view.addSearchFinancialAmountListener(new SearchFinancialAmountListener());
        view.addListFinancialSelectionListener(new ListFinancialSelectionListener());
    }

    public void showFinancialView() {
        List<Financial> financialList = financialDao.getListFinancials();
        financialView.setVisible(true);
        financialView.showListFinancials(financialList);
    }

    /**
     * Lớp AddFinancialListener 
     * chứa cài đặt cho sự kiện click button "Add"
     */
    class AddFinancialListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<Financial> list = financialDao.getListFinancials();
            Financial financial = financialView.getFinancialInfo();
            int balance=0;
            if (financial != null) {
                for (Financial value : list) {
                    if (value.getType().equals("Thu")) {
                        balance += value.getAmount();
                    } else {
                        balance -= value.getAmount();
                    }
                }
                if (balance-financial.getAmount()<0 && financial.getType().equals("Chi")) {
                    financialView.showMessage("Unaffordable!");
                }
                else {
                    financialDao.add(financial);
                    financialView.showFinancial(financial);
                    financialView.showListFinancials(list);
                    financialView.showMessage("Added!");
                }
            }
        }
    }

    /**
     * Lớp EditFinancialListener 
     * chứa cài đặt cho sự kiện click button "Edit"
     */
    class EditFinancialListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Financial financial = financialView.getFinancialInfo();
            List<Financial> list;
            Financial old_financial = financialView.getFinancialInfo();
            int balance=0;
            if (financial != null) {
                financialDao.edit(financial);
                list = financialDao.getListFinancials();
                for (Financial value : list) {
                    if (value.getType().equals("Thu")) {
                        balance += value.getAmount();
                    } else {
                        balance -= value.getAmount();
                    }
                }
                if (balance<0) {
                    financialDao.edit(old_financial);
                    financialView.showMessage("Invalid Edit, Available Balance < 0!");
                }
                else {
                    financialView.showFinancial(financial);
                    financialView.showListFinancials(financialDao.getListFinancials());
                    financialView.showMessage("Edited!");
                }
            }
        }
    }

    /**
     * Lớp DeleteFinancialListener 
     * chứa cài đặt cho sự kiện click button "Delete"
     */
    class DeleteFinancialListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<Financial> list = financialDao.getListFinancials();
            Financial financial = financialView.getFinancialInfo();
            if (financial != null) {
                int balance=0;
                for (Financial value : list) {
                    if (value.getType().equals("Thu")) {
                        balance += value.getAmount();
                    } else {
                        balance -= value.getAmount();
                    }
                }
                if (financial.getType().equals("Thu") && balance-financial.getAmount()<0) financialView.showMessage("Invalid Delete, Available Balance < 0");
                else {
                    financialDao.delete(financial);
                    financialView.clearFinancialInfo();
                    financialView.showListFinancials(financialDao.getListFinancials());
                    financialView.showMessage("Deleted!");
                }
            }
        }
    }

    /**
     * Lớp ClearFinancialListener 
     * chứa cài đặt cho sự kiện click button "Clear"
     */
    class ClearFinancialListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            financialView.clearFinancialInfo();
            financialView.showListFinancials(financialDao.getListFinancials());
        }
    }

    /**
     * Lớp SortFinancialAmountListener
     * chứa cài đặt cho sự kiện click button "Sort By Amount"
     */
    class SortFinancialAmountListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            financialDao.sortFinancialByAmount();
            financialView.showListFinancials(financialDao.getListFinancials());
        }
    }

    /**
     * Lớp SortFinancialDateListener
     * chứa cài đặt cho sự kiện click button "Sort By Date"
     */
    class SortFinancialDateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            financialDao.sortFinancialByDate();
            financialView.showListFinancials(financialDao.getListFinancials());
        }
    }

    /**
     * Lớp SortFinancialIdListener
     * chứa cài đặt cho sự kiện click button "Sort By Date"
     */
    class SortFinancialIdListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            financialDao.sortFinancialByID();
            financialView.showListFinancials(financialDao.getListFinancials());
        }
    }

    /**
     * Lớp SearchFinancialDateListener
     * chứa cài đặt cho sự kiện click button "Search By Date"
     */
    class SearchFinancialDateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Kiểm tra đã điền thanh search chưa
            if (financialView.getSearchDate()==null) {
                financialView.showMessage("Blank search box!");
                return;
            }
            boolean check_found = false, check_error=false;
            int[] date=financialView.getSearchDate();
            List<Financial> financials = financialDao.getListFinancials();
            List<Financial> found  = new ArrayList<>();
            if (date.length==0){
                check_error=true;
            }
            else if (date.length==1) {
                for (Financial financial : financials) {
                    if (financial.getDate().getYear()==date[0]) {
                        check_found = true;
                        found.add(financial);
                    }
                }
            }
            else if (date.length==2){
                for (Financial financial : financials) {
                    if (financial.getDate().getMonthValue()==date[0] && financial.getDate().getYear()==date[1]) {
                        check_found = true;
                        found.add(financial);
                    }
                }
            }
            else if (date.length==3){
                for (Financial financial : financials) {
                    if (financial.getDate().equals(LocalDate.of(date[2], date[1], date[0]))) {
                        check_found = true;
                        found.add(financial);
                    }
                }
            }
            if (check_error) {
                financialView.showMessage("Date should be \"day/month/year\"");
            }
            else if (!check_found) {
                financialView.showMessage("Not found!");
            }
            else {
                int balance=0;
                financialView.showListFinancials(found);
            }
        }
    }

    /**
     * Lớp SearchFinancialAmountListener
     * chứa cài đặt cho sự kiện click button "Search By Min Amount"
     */
    class SearchFinancialAmountListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Kiểm tra đã điền thanh search chưa
            if (financialView.getSearchMinAmount()==-999) {
                financialView.showMessage("Blank search box!");
                return;
            }
            boolean check = false;
            List<Financial> financials = financialDao.getListFinancials();
            List<Financial> found  = new ArrayList<>();
            for (Financial financial : financials) {
                if (financial.getAmount()>=financialView.getSearchMinAmount()) {
                    check = true;
                    found.add(financial);
                }
            }
            if (!check) {
                financialView.showMessage("Not found!");
            } else {
                financialView.showListFinancials(found);
            }
        }
    }

    /**
     * Lớp SearchFinancialMaxAmountListener
     * chứa cài đặt cho sự kiện click button "Search By Max Amount"
     */
    class SearchFinancialMaxAmountListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Kiểm tra đã điền thanh search chưa
            if (financialView.getSearchMaxAmount()==-999) {
                financialView.showMessage("Blank search box!");
                return;
            }
            boolean check = false;
            List<Financial> financials = financialDao.getListFinancials();
            List<Financial> found  = new ArrayList<>();
            for (Financial financial : financials) {
                if (financial.getAmount()<=financialView.getSearchMaxAmount()) {
                    check = true;
                    found.add(financial);
                }
            }
            if (!check) {
                financialView.showMessage("Not found!");
            } else {
                financialView.showListFinancials(found);
            }
        }
    }

    /**
     * Lớp ListFinancialSelectionListener 
     * chứa cài đặt cho sự kiện chọn financial trong bảng financial
     */
    class ListFinancialSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            financialView.fillFinancialFromSelectedRow();
        }
    }
}