import javax.swing.*;
import java.awt.*;

class SearchForm extends JFrame {
    private JLabel parameterName = new JLabel();
    private JTextField inputField = new JTextField();
    private JButton searchBtn = new JButton("search");
    private BtnController btnController;

    SearchForm(String type, BtnController btnController){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2,2,3,3));
        setBounds(150,150,400,200);
        this.btnController = btnController;

        searchBtn.addActionListener(btnController);
        parameterName.setText(type);

        add(parameterName);
        add(inputField);
        add(new JPanel());
        add(searchBtn);

        setVisible(true);
    }
    String getParameterName(){
        return parameterName.getText();
    }
    String getParameter(){
        return inputField.getText();
    }
}
