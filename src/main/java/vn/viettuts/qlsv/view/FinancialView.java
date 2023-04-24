package vn.viettuts.qlsv.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import vn.viettuts.qlsv.entity.Financial;
import vn.viettuts.qlsv.dao.FinancialDao;

public class FinancialView extends JFrame implements ActionListener, ListSelectionListener {
    private final FinancialDao financialDao = new FinancialDao();

    private static final long serialVersionUID = 1L;
    private JButton addFinancialBtn;
    private JButton editFinancialBtn;
    private JButton deleteFinancialBtn;
    private JButton clearBtn;
    private JButton sortFinancialAmountBtn;
    private JButton sortFinancialDateBtn;
    private JButton sortFinancialIdBtn;
    private JButton searchFinancialDateBtn;
    private JButton searchFinancialMinAmountBtn;
    private JButton searchFinancialMaxAmountBtn;
    private JTable financialTable;

    private JTextField idField;
    private JTextField dateField;
    private JRadioButton typeRadio1;
    private JRadioButton typeRadio2;
    private ButtonGroup typeRadio;
    private JTextArea detailsTA;
    private JTextField amountField;
    private JTextField balanceField;
    private JTextField searchFinancialDateField;
    private JTextField searchFinancialMinAmountField;
    private JTextField searchFinancialMaxAmountField;

    // định nghĩa các cột của bảng financial
    private final String [] columnNames = new String [] {
            "ID", "Date", "Type", "Details", "Amount (VND)"};
    // định nghĩa dữ liệu mặc định của bẳng financial là rỗng
    private final Object data = new Object [][] {};
    
    public FinancialView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addFinancialBtn = new JButton("Add");
        editFinancialBtn = new JButton("Edit");
        deleteFinancialBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortFinancialAmountBtn = new JButton("Sort By Amount");
        sortFinancialDateBtn = new JButton("Sort By Date");
        sortFinancialIdBtn = new JButton("Sort By ID");
        searchFinancialDateBtn=new JButton("Search By Date");
        searchFinancialMinAmountBtn=new JButton("Search By Min Amount");
        searchFinancialMaxAmountBtn=new JButton("Search By Max Amount");

        // khởi tạo bảng financial
        JScrollPane jScrollPaneFinancialTable = new JScrollPane();
        financialTable = new JTable();
        
        // khởi tạo các label
        JLabel idLabel = new JLabel("Id");
        JLabel dateLabel = new JLabel("Date");
        JLabel typeLabel = new JLabel("Type");
        JLabel detailsLabel = new JLabel("Details");
        JLabel amountLabel = new JLabel("Amount (VND)");
        JLabel balanceLabel = new JLabel("Available Balance (VND)");

        // khởi tạo Radio Button
        typeRadio1 = new JRadioButton("Thu");
        typeRadio2 = new JRadioButton("Chi");
        typeRadio = new ButtonGroup();
        typeRadio.add(typeRadio1);
        typeRadio.add(typeRadio2);
        add(typeRadio1);
        add(typeRadio2);
        
        // khởi tạo các trường nhập dữ liệu cho financial
        idField = new JTextField(6);
        idField.setEditable(false);
        dateField = new JTextField(15);
        detailsTA = new JTextArea();
        detailsTA.setColumns(15);
        detailsTA.setRows(5);
        JScrollPane jScrollPaneAddress = new JScrollPane();
        jScrollPaneAddress.setViewportView(detailsTA);
        amountField = new JTextField(15);
        balanceField = new JTextField(8);
        balanceField.setEditable(false);
        searchFinancialDateField = new JTextField(10);
        searchFinancialMinAmountField = new JTextField(10);
        searchFinancialMaxAmountField = new JTextField(10);
        
        // cài đặt các cột và data cho bảng financial
        financialTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneFinancialTable.setViewportView(financialTable);
        jScrollPaneFinancialTable.setPreferredSize(new Dimension (480, 370));
        
         // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Financial
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneFinancialTable);
        
        panel.add(addFinancialBtn);
        panel.add(editFinancialBtn);
        panel.add(deleteFinancialBtn);
        panel.add(clearBtn);
        panel.add(sortFinancialAmountBtn);
        panel.add(sortFinancialDateBtn);
        panel.add(sortFinancialIdBtn);
        panel.add(searchFinancialDateBtn);
        panel.add(searchFinancialMinAmountBtn);
        panel.add(searchFinancialMaxAmountBtn);

        panel.add(idLabel);
        panel.add(dateLabel);
        panel.add(typeLabel);
        panel.add(detailsLabel);
        panel.add(amountLabel);
        panel.add(balanceLabel);
        
        panel.add(idField);
        panel.add(dateField);
        panel.add(typeRadio1);
        panel.add(typeRadio2);
        panel.add(jScrollPaneAddress);
        panel.add(amountField);
        panel.add(balanceField);
        panel.add(searchFinancialDateField);
        panel.add(searchFinancialMinAmountField);
        panel.add(searchFinancialMaxAmountField);

        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, dateLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, detailsLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, detailsLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, amountLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, amountLabel, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, balanceLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, balanceLabel, 405, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, dateField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dateField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeRadio1, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeRadio1, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeRadio2, 170, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeRadio2, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, amountField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, amountField, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, balanceField, 170, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, balanceField, 405, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchFinancialDateField, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchFinancialDateField, 285, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchFinancialMinAmountField, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchFinancialMinAmountField, 315, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchFinancialMaxAmountField, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchFinancialMaxAmountField, 345, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneFinancialTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneFinancialTable, 10, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addFinancialBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addFinancialBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editFinancialBtn, 60, SpringLayout.WEST, addFinancialBtn);
        layout.putConstraint(SpringLayout.NORTH, editFinancialBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteFinancialBtn, 60, SpringLayout.WEST, editFinancialBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteFinancialBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchFinancialDateBtn, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchFinancialDateBtn, 280, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchFinancialMinAmountBtn, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchFinancialMinAmountBtn, 310, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchFinancialMaxAmountBtn, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchFinancialMaxAmountBtn, 340, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteFinancialBtn);
        
        layout.putConstraint(SpringLayout.WEST, sortFinancialAmountBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortFinancialAmountBtn, 400, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortFinancialDateBtn, 150, SpringLayout.WEST, sortFinancialAmountBtn);
        layout.putConstraint(SpringLayout.NORTH, sortFinancialDateBtn, 400, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortFinancialIdBtn, 130, SpringLayout.WEST, sortFinancialDateBtn);
        layout.putConstraint(SpringLayout.NORTH, sortFinancialIdBtn, 400, SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.pack();
        this.setTitle("Financial Management");
        this.setSize(800, 500);
        // disable Edit and Delete buttons
        editFinancialBtn.setEnabled(false);
        deleteFinancialBtn.setEnabled(false);
        // enable Add button
        addFinancialBtn.setEnabled(true);
        // show available balance
        int balance=0;
        List<Financial> list = financialDao.getListFinancials();
        for (Financial financial : list) {
            if (financial.getType().equals("Thu")) {
                balance += financial.getAmount();
            } else balance -= financial.getAmount();
        }
        this.showAvailableBalance(balance);
    }

    public void showAvailableBalance(int balance){
        balanceField.setText(""+balance);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


    /**
     * hiển thị list financial vào bảng financialTable
     */
    public void showListFinancials(List<Financial> list) {
        int size = list.size();
        // với bảng financialTable có 5 cột,
        // khởi tạo mảng 2 chiều financials, trong đó:
        // số hàng: là kích thước của list financial 
        // số cột: là 5
        Object [][] financials = new Object[size][5];
        for (int i = 0; i < size; i++) {
            financials[i][0] = list.get(i).getId();
            financials[i][1] = list.get(i).getDate().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
            financials[i][2] = list.get(i).getType();
            financials[i][3] = list.get(i).getDetails();
            financials[i][4] = list.get(i).getAmount();
        }
        financialTable.setModel(new DefaultTableModel(financials, columnNames));
        financialTable.getColumnModel().getColumn(0).setMaxWidth(30);
        financialTable.getColumnModel().getColumn(1).setMaxWidth(70);
        financialTable.getColumnModel().getColumn(2).setMaxWidth(40);
        financialTable.getColumnModel().getColumn(3).setMaxWidth(240);
        financialTable.getColumnModel().getColumn(4).setMaxWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < 3; i++) {
            financialTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            financialTable.getColumnModel().getColumn(i).setResizable(false);
        }
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
        financialTable.getColumnModel().getColumn(4).setCellRenderer( rightRenderer );
    }
    
    /**
     * điền thông tin của hàng được chọn từ bảng financial 
     * vào các trường tương ứng của financial.
     */
    public void fillFinancialFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = financialTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(financialTable.getModel().getValueAt(row, 0).toString());
            dateField.setText(financialTable.getModel().getValueAt(row, 1).toString());
            if (financialTable.getModel().getValueAt(row, 2).toString().equals("Thu")){
                typeRadio1.doClick();
            } else typeRadio2.doClick();
            detailsTA.setText(financialTable.getModel().getValueAt(row, 3).toString());
            amountField.setText(financialTable.getModel().getValueAt(row, 4).toString());
            // enable Edit and Delete buttons
            editFinancialBtn.setEnabled(true);
            deleteFinancialBtn.setEnabled(true);
            // disable Add button
            addFinancialBtn.setEnabled(false);
        }
    }

    /**
     * xóa thông tin financial
     */
    public void clearFinancialInfo() {
        idField.setText("");
        dateField.setText("");
        typeRadio.clearSelection();
        detailsTA.setText("");
        amountField.setText("");
        searchFinancialDateField.setText("");
        searchFinancialMinAmountField.setText("");
        searchFinancialMaxAmountField.setText("");
        // disable Edit and Delete buttons
        editFinancialBtn.setEnabled(false);
        deleteFinancialBtn.setEnabled(false);
        // enable Add button
        addFinancialBtn.setEnabled(true);
    }
    
    /**
     * hiện thị thông tin financial
     */
    public void showFinancial(Financial financial) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        idField.setText("" + financial.getId());
        dateField.setText(financial.getDate().format(formatter));
        if (financial.getType().equals("Thu")){
            typeRadio1.doClick();
        } else typeRadio2.doClick();
        detailsTA.setText(financial.getDetails());
        amountField.setText("" + financial.getAmount());
        // enable Edit and Delete buttons
        editFinancialBtn.setEnabled(true);
        deleteFinancialBtn.setEnabled(true);
        // disable Add button
        addFinancialBtn.setEnabled(false);
    }
    
    /**
     * lấy thông tin financial
     */
    public Financial getFinancialInfo() {
        // validate financial
        if (!validateDate() || !validateType() || !validateDetails() || !validateAmount()) {
            return null;
        }
        try {
            Financial financial = new Financial();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                financial.setId(Integer.parseInt(idField.getText()));
            }
            String[] date = dateField.getText().split("/");
            int day=Integer.parseInt(date[0]);
            int month=Integer.parseInt(date[1]);
            int year=Integer.parseInt(date[2]);
            financial.setDate(LocalDate.of(year, month, day));
            if(typeRadio1.isSelected()) financial.setType("Thu");
            else financial.setType("Chi");
            financial.setDetails(detailsTA.getText().trim());
            financial.setAmount(Integer.parseInt(amountField.getText().trim()));
            return financial;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    public int[] getSearchDate(){
        if (searchFinancialDateField.getText()==null) return null;
        String[] date = searchFinancialDateField.getText().split("/");
        int[] int_date = new int[date.length];
        try {
            for (int i = 0; i < date.length; i++) {
                int_date[i] = Integer.parseInt(date[i]);
            }
            return int_date;
        } catch (Exception e){
            return new int[0];
        }
    }

    public int getSearchMinAmount(){
        if (searchFinancialMinAmountField.getText().equals("")) return -999;
        else return Integer.parseInt(searchFinancialMinAmountField.getText());
    }

    public int getSearchMaxAmount(){
        if (searchFinancialMaxAmountField.getText().equals("")) return -999;
        else return Integer.parseInt(searchFinancialMaxAmountField.getText());
    }

    private boolean validateDetails() {
        return true;
    }

    private boolean validateDate() {
        try {
            String date = dateField.getText();
            if (date == null || "".equals(date.trim())) {
                dateField.requestFocus();
                showMessage("Date is empty!");
                return false;
            }
            String[] split_date = date.split("/");
            if (split_date.length!=3) {
                showMessage("Date must be day/month/year");
                return false;
            }
        } catch (Exception e) {
            dateField.requestFocus();
            showMessage("Invalid Date, Date must be day/month/year!");
            return false;
        }
        return true;
    }
    
    private boolean validateType() {
        if (!typeRadio1.isSelected() && !typeRadio2.isSelected()) {
            showMessage("Must select Type!");
            return false;
        }
        return true;
    }
    
    private boolean validateAmount() {
        try {
            int amount = Integer.parseInt(amountField.getText().trim());
            if (amount < 0) {
                amountField.requestFocus();
                showMessage("Invalid Amount, Amount should be greater than or equal to 0");
                return false;
            }
        } catch (Exception e) {
            amountField.requestFocus();
            showMessage("Invalid Amount");
            return false;
        }
        return true;
    }
    
    public void actionPerformed(ActionEvent e) {
    }
    
    public void valueChanged(ListSelectionEvent e) {
    }
    
    public void addAddFinancialListener(ActionListener listener) {
        addFinancialBtn.addActionListener(listener);
    }
    
    public void addEditFinancialListener(ActionListener listener) {
        editFinancialBtn.addActionListener(listener);
    }
    
    public void addDeleteFinancialListener(ActionListener listener) {
        deleteFinancialBtn.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }
    
    public void addSortFinancialAmountListener(ActionListener listener) {
        sortFinancialAmountBtn.addActionListener(listener);
    }
    
    public void addSortFinancialDateListener(ActionListener listener) {
        sortFinancialDateBtn.addActionListener(listener);
    }

    public void addSortFinancialIdListener(ActionListener listener) {
        sortFinancialIdBtn.addActionListener(listener);
    }

    public void addSearchFinancialDateListener(ActionListener listener) {
        searchFinancialDateBtn.addActionListener(listener);
    }

    public void addSearchFinancialMinAmountListener(ActionListener listener) {
        searchFinancialMinAmountBtn.addActionListener(listener);
    }

    public void addSearchFinancialMaxAmountListener(ActionListener listener) {
        searchFinancialMaxAmountBtn.addActionListener(listener);
    }
    
    public void addListFinancialSelectionListener(ListSelectionListener listener) {
        financialTable.getSelectionModel().addListSelectionListener(listener);
    }
}
