package trail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {
    private String name;
    private String surname;
    private int numberPectoral;

    private static int numberAssignment = 1;
    private static List<Runner> listRunner = new ArrayList<>();

    public Runner() {
    }

    public Runner(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumberPectoral() {
        return numberPectoral;
    }

    public void setNumberPectoral(int numberPectoral) {
        this.numberPectoral = numberPectoral;
    }

    public static int getNumberAssignment() {
        return numberAssignment;
    }

    public static void setNumberAssignment(int numberAssignment) {
        Runner.numberAssignment = numberAssignment;
    }

    public static List<Runner> getListRunner() {
        return listRunner;
    }

    public static void setListRunner(List<Runner> listRunner) {
        Runner.listRunner = listRunner;
    }

    public static Runner getRunner(int numberPectoral) {
        return listRunner.stream()
                .filter(runner -> runner.getNumberPectoral() == numberPectoral)
                .findFirst()
                .orElse(null);
    }

    public static List<Runner> getRunner(String surname) {
        return listRunner.stream()
                .filter(runner -> runner.getSurname().equals(surname))
                .sorted(Comparator.comparingInt(Runner::getNumberPectoral))
                .collect(Collectors.toList());
    }

    public static Runner newRunner(String name, String surname) {
        if (!name.isEmpty() && !surname.isEmpty()) {
            Runner runner = new Runner(name, surname);
            runner.setNumberPectoral(numberAssignment++);
            listRunner.add(runner);
            return runner;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static List<Runner> getRunnersByName() {
        return listRunner.stream()
                .sorted(Comparator.comparing(Runner::getSurname)
                        .thenComparing(Runner::getName)
                        .thenComparingInt(Runner::getNumberPectoral))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Runner" + "\n" +
                "name='" + name + '\'' + "\n" +
                ", surname='" + surname + '\'' + "\n" +
                ", numberPectoral=" + numberPectoral + "\n";
    }

}
