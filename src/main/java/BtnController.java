import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BtnController implements ActionListener {
    private AddWindow addWin = new AddWindow(this);
    private SearchForm searchForm;
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "add":
                addWin.showWindow("confirm");
                break;
            case "confirm":
                Logic.addNumber(addWin.getNumber(),addWin.getFullName(),addWin.getAddress());
                try {
                    Logic.getCurrentWin().updateList(Logic.getAllElements());
                } catch (SQLException e1) { e1.printStackTrace(); }
                addWin.dispose();
                break;
            case "edit":
                String[] element = Logic.getCurrentWin().getPhoneList().getSelectedValue().split(";");
                addWin.setNumber(Long.parseLong(element[0]));
                addWin.setFullName(element[1]);
                addWin.setAddress(element[2]);
                addWin.showWindow("confirm edit");
                break;
            case "confirm edit":
                element = Logic.getCurrentWin().getPhoneList().getSelectedValue().split(";");
                Logic.editNumber(addWin.getNumber(),addWin.getFullName(),addWin.getAddress(), Long.parseLong(element[0]));
                try {
                    Logic.getCurrentWin().updateList(Logic.getAllElements());
                } catch (SQLException e1) { e1.printStackTrace(); }
                addWin.dispose();
                break;
            case "delete":
                element = Logic.getCurrentWin().getPhoneList().getSelectedValue().split(";");
                Logic.deleteElement(Long.parseLong(element[0]));
                try {
                    Logic.getCurrentWin().updateList(Logic.getAllElements());
                } catch (SQLException e1) { e1.printStackTrace(); }
                break;
            case "sort":
                ResultSet list = Logic.sortBy(Logic.getCurrentWin().getSortTypesBox().getSelectedItem().toString());
                try {
                    Logic.getCurrentWin().updateList(list);
                } catch (SQLException e1) { e1.printStackTrace(); }
                break;
            case "find by number":
                searchForm = new SearchForm("number",this);
                break;
            case "find by full name":
                searchForm = new SearchForm("fullName",this);
                break;
            case "find by address":
                searchForm = new SearchForm("address",this);
                break;
            case "search":
                searchForm.dispose();
                switch (searchForm.getParameterName()){
                    case "number":
                        String searchResult = Logic.getElementByNumber(Long.parseLong(searchForm.getParameter()));
                        JOptionPane.showMessageDialog(null,searchResult);
                        break;
                    case "fullName":
                        searchResult = Logic.getElementByFullName(searchForm.getParameter());
                        JOptionPane.showMessageDialog(null,searchResult);
                        break;
                    case "address":
                        searchResult = Logic.getElementByAddress(searchForm.getParameter());
                        JOptionPane.showMessageDialog(null,searchResult);
                        break;
                }
                break;
        }
    }
}
