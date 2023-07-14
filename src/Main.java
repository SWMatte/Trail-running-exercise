import trail.*;


import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws TrailException {


        Runner e0 = new Runner();
        Runner e1 = new Runner();
        Runner e2 = new Runner();
        Runner e3 = new Runner();
        Runner e4 = new Runner();
        e0 =e0.newRunner("Aldo","Tommasini"); //crea un runner e aggiungilo alla lista
        e1 =e1.newRunner("Giovanni","Bertolli");
        e2 =e2.newRunner("Giacomo","Affranti");
        e3 =e3.newRunner("Stefano","Biciasca");
        e4 =e4.newRunner("Antonino","Tommasini");


        System.out.println(Runner.getRunner(2)); //stampa il runner in base al numero
        System.out.println("--------------------LISTA FILTRATA X STESSO COGNOME--------------------------------------");
        System.out.println(Runner.getRunner("Tommasini")); // stampa una lista filtrata x cognome

        System.out.println("-----------------------LISTA FILTRATA---------------------------------------");

        System.out.println(Runner.getRunnersByName()); // stampa una lista filtrata x cognome
        System.out.println("------------------N DELLA TAPPA IN BASE AL NOME--------------------------");


        Trail trail = new Trail();
        trail.addLocation("Roma");
        trail.addLocation("Milano");
        trail.addLocation("Venezia");



        System.out.println(trail.getLocation("Venezia")); // ottieni il numero della localita' in base al nome
        System.out.println("---------------LISTA DELLE LOCALITA----------------------------------------");

        trail.getPath().forEach(e-> System.out.println(e));  // lista delle localita


        System.out.println("-------------------LISTA DEI GIUDICI-------------------------------------");
        Delegate a= trail.newDelegate("Matteo","Pierlini","MP1");
        Delegate b= trail.newDelegate("Franca","Spalanca","FS5");
        Delegate c= trail.newDelegate("Pietro","Filterni","PF12");


         trail.getDelegates() ; //ottieni la lista dei delegati

        System.out.println("---------------LISTA DEI GIUDICI X CITTA-------------------");
        List listaGiudici = Arrays.asList(a,b);
        trail.assignDelegate("Roma",listaGiudici);  // asegno i delegati A-B alla location Roma

        System.out.println(trail.getDelegateList("Roma")); // ottengo i delegati che sono assegnati alla specifica citta'

        System.out.println("--------------Registrazione tempo  dei runner x la citta--------------------");

        System.out.println( trail.recordPassage("PF12","Roma",1,5)); // registra il tempo di questo corridore in quella localita
        System.out.println( trail.recordPassage("PF12","Roma",3,6)); // registra il tempo di questo corridore in quella localita
        System.out.println( trail.recordPassage("MP1","Roma",2,10)); // registra il tempo di questo corridore in quella localita
        System.out.println( trail.recordPassage("FS5","Milano",4,2)); // registra il tempo di questo corridore in quella localita


        System.out.println("--------------Ti dice il tempo dei corridori x specifica citta------------------");

         System.out.println(trail.getPassTime("Milano"));

         System.out.println("--------------OTTENGO X CITTA SPECIFICA E PETTORALE SPECIFICO IL TEMPO DI UN RUNNER-------------------");
         System.out.println(trail.getSpecificRunner("Milano",1));

         System.out.println("--------Classifica GENERALE SENZA CONSIDERARE LE CITTA-----------");

         trail.getRanking().forEach(e-> System.out.println(e));




    }
}