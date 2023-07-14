package trail;

public class Delegate {
    private String name;
    private String surname;
    private String fiscalCode;


    public Delegate(){

    }

    public Delegate(String name, String surname, String fiscalCode) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public   String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }


    @Override
    public String toString() {
        return "Delegate"  +
                "name='" + name + "\n" +
                ", surname='" + surname + "\n" +
                ", fiscalCode='" + fiscalCode + "\n" ;

    }
}
