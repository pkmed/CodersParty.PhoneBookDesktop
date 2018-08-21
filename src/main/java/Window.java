import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

class Window extends JFrame {
    private DefaultListModel<String> phoneListModel = new DefaultListModel<>();
    private JList<String> phoneList = new JList<>(phoneListModel);
    private JButton addPhone = new JButton("add"),
            editPhone = new JButton("edit"),
            deletePhone = new JButton("delete"),
            sortList = new JButton("sort"),
            findByNumber = new JButton("find by number"),
            findByFullName = new JButton("find by full name"),
            findByAddress = new JButton("find by address");
    private DefaultComboBoxModel<String> sortTypes = new DefaultComboBoxModel<>();
    private JComboBox<String> sortTypesBox = new JComboBox<>(sortTypes);
    private BtnController btnController;
    Window(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2,3,3));
        setBounds(100,100,400,300);
        btnController = new BtnController();

        addPhone.addActionListener(btnController);
        editPhone.addActionListener(btnController);
        deletePhone.addActionListener(btnController);
        sortList.addActionListener(btnController);
        findByAddress.addActionListener(btnController);
        findByFullName.addActionListener(btnController);
        findByNumber.addActionListener(btnController);

        Container btns = new Container();
        btns.setLayout(new FlowLayout());
        btns.add(addPhone);
        btns.add(editPhone);
        btns.add(deletePhone);
        sortTypes.addElement("fullName");
        sortTypes.addElement("address");
        btns.add(sortTypesBox);
        btns.add(sortList);
        btns.add(findByNumber);
        btns.add(findByFullName);
        btns.add(findByAddress);

        add(phoneList);
        add(btns);

        setVisible(true);
    }
    JComboBox<String> getSortTypesBox(){
        return sortTypesBox;
    }
    JList<String> getPhoneList(){
        return phoneList;
    }
    void updateList(ResultSet data) throws SQLException {
        phoneListModel.clear();
        while(data.next()){
            String element;
            element = data.getString(2)+";";
            element += data.getString(3)+";";
            element += data.getString(4);
            phoneListModel.addElement(element);
        }
    }
}
