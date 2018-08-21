import javax.swing.*;
import java.awt.*;

public class AddWindow extends JFrame {
    private JLabel fullName = new JLabel("Full Name"),
    address = new JLabel("Address"),
    number = new JLabel("Number");
    private JTextField nameField = new JTextField(),
    addressField  = new JTextField(),
    numberField = new JTextField();
    BtnController btnController;
    private JButton confirmBtn = new JButton("confirm");
    AddWindow(BtnController btnController){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4,2,3,3));
        setBounds(150,150,400,200);
        this.btnController = btnController;

        add(fullName);
        add(nameField);
        add(address);
        add(addressField);
        add(number);
        add(numberField);
        add(new JPanel());
        confirmBtn.addActionListener(btnController);
        add(confirmBtn);
    }
    void showWindow(String btnFunc){
        confirmBtn.setText(btnFunc);
        setVisible(true);
    }
    String getFullName(){
        return nameField.getText();
    }
    void setFullName(String name){
        nameField.setText(name);
    }
    String getAddress(){
        return addressField.getText();
    }
    void setAddress(String address){
        addressField.setText(address);
    }
    Long getNumber(){
        return Long.parseLong(numberField.getText());
    }
    void setNumber(int number){
        numberField.setText(number+"");
    }

    public void clearFields() {
        nameField.setText("");
        addressField.setText("");
        numberField.setText("");
    }
}
