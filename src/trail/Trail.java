package trail;

import java.util.*;
import java.util.stream.Collectors;


public class Trail {
    private List<Location> pathRace = new ArrayList<>();
    private List<Delegate> delegates = new ArrayList<>();
    private HashMap<Location, List<Delegate>> organizzatori = new HashMap<>();  //  assegno localita (key) lista di controllori (value)

    private HashMap<String, HashMap<Integer, Long>> passageTimes = new HashMap<>(); // associo ad una localita (key) il tempo del corridore(value)

    public void addLocation(String location) {
        int count = pathRace.size()+1;
        Location l = new Location();
        l.setNameLocation(location);
        l.setOrderNum(count);
        getPathRace().add(l);

    }

    public Location getLocation(String location) {
        Location location1 = pathRace.stream()
                .filter(x -> x.getNameLocation().equals(location)).findFirst().get();
        return location1;
    }

    public List<Location> getPath() {
        return pathRace;
    }

    public Delegate newDelegate(String name, String surname, String id) {

        Delegate firstDelegate = new Delegate(name, surname, id);
        delegates.add(firstDelegate);
        return firstDelegate;
    }

    public void getDelegates() {
        List<Delegate>listaDelegati= delegates.stream()
                .sorted(Comparator.comparing(Delegate::getSurname)).collect(Collectors.toList());

        listaDelegati.forEach(e-> System.out.println(e));
     }


    public void assignDelegate(String location, List<Delegate> namesDelegate) throws TrailException {
        Location place = pathRace.stream()
                .filter(x -> x.getNameLocation().equals(location)).findFirst().orElse(null);
        if (namesDelegate.stream()
                .allMatch(delegates::contains)) {

            organizzatori.put(place, namesDelegate);
        } else {
            throw new TrailException("Posizione non trovata: " + location);
        }


    }

    public List<Delegate> getDelegateList(String locationName) {
        return organizzatori.entrySet().stream()
                .filter(x -> x.getKey().getNameLocation().equals(locationName))
                .map(Map.Entry::getValue)
                .findFirst().get();


    }


    public long recordPassage(String cf, String location, int bibNumber,long tempocorso) throws TrailException {
        Delegate foundDelegate = delegates.stream()       //verifico che esista il controllore
                .filter(d -> d.getFiscalCode().equals(cf))
                .findFirst().orElse(null);

        Location foundLocation = pathRace.stream()      //verifico l'esistanza della localita
                .filter(l -> l.getNameLocation().equals(location))
                .findFirst().orElse(null);

        Runner foundRunner = Runner.getRunner(bibNumber);   // verifico che esista il runner x il pettorale specifico

        if (foundDelegate != null && foundLocation != null && foundRunner != null) {
            //long passageTime = System.currentTimeMillis(); // registro il tempo specifico
            long passageTime =tempocorso;
            HashMap<Integer, Long> tempiEnumeroPettorale = new HashMap<>();  // creo l'hashamp che mi richiede il mio passageTime
            tempiEnumeroPettorale.put(bibNumber, passageTime); // riempio il mio hashmap con il numero di pettorale e il tempo
            if (!passageTimes.containsKey(location)) {          // se l'hashmap NON contiene ancora la localita la inserisco e registro i tempi
                passageTimes.put(location, tempiEnumeroPettorale);
            } else {
                passageTimes.get(location).put(bibNumber, passageTime); // se invece esiste gia la localita' io vado a recuperarmela e aggiorno la lista

            }

            return passageTime;
        } else {
            throw new TrailException();
        }


    }


    public List<HashMap<Integer, Long>> getPassTime(String position) throws TrailException {
      return  passageTimes.entrySet().stream()
                .filter(z->z.getKey().equals(position))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        /*
        partendo dall'hashmap dove registri i tempi, lo rendi uno stream con l'entrySET, filtri x la posizione (citta) specifica,
        ottenendo praticamente la <key> come citta e come <value> hai una lista formata da un hasmap formato da <key-value> cioe numero pettorale,tempo registrato.
        quindi facendo il map, con map.entry vai a prendere soltanto il value cioe la coppia int-long e ottieni una lista formata rispettivamente da numero pettorale - tempo registrato.

         */
    }


    public Long getSpecificRunner(String position, int bibNumber) throws TrailException {
        return passageTimes.entrySet().stream()
                .filter(entry -> entry.getKey().equals(position))
                .flatMap(entry -> entry.getValue().entrySet().stream())
                .filter(mapEntry -> mapEntry.getKey() == bibNumber)
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }


    /*
    prendo il mio hasmap-> lo rendo uno stream
    lo filtro x la posizione specifica,
    facendo il flatMap accedo all'hashmap interno cioe' hasmap<integer,long> di una localita'
    lo rendo quindi uno stream a sua volta, dato che quello + interno ancora non l'avevo toccato attraverso il  .flatMap(entry -> entry.getValue().entrySet().stream())
    filtro x il numero di pettorale specifico ,
    facendo il map.Entry vado a prendermi il numero di tempo registrato

    * */



    public List<Runner> getRanking() {

         List<Integer> pectoralNumber= passageTimes.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .flatMap(hashMapInterno -> hashMapInterno.entrySet().stream())
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
 /*
 *  primo step- ottengo da passageTimes uno stream,
 * poi prendo in considerazione con il Map.Entry::getvalue l'hashmap + interno cioe hasmap<integer,long> (rappresentato da pettorali corridori, tempo registrato)
 * vado ad aprire ulteriormente questo hasmap attraverso il flatMap rendendolo uno stream,
 * vado ad ordinare i value cioe i tempi registrati ,
 * ottengo un nuovo array dato dalle key dei numeri di pettorale con i tempi ordinati
 * lo rendo una lista
 * */

        List<Runner> runners = passageTimes.entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().entrySet().stream())
                .sorted(Map.Entry.comparingByValue())
                .map(entry -> Runner.getRunner(entry.getKey()))
                .collect(Collectors.toList());

        return runners;


/*
     la lista di runner è uguale al mio  hasmap fatto a stream cioe, string(localita),hasmhmap<integer(nPettoral),long(tempoRegistrato)>
    prendo poi i valori dell hasmhmap <integer(nPettoral),long(tempoRegistrato)>
    lo rendo uno stream,
     ordino x i tempi di registrazione
e poi faccio un nuovo array, richiamando la classe Runner. con il metodo getRunner passando la key che corrisponde all integer del pettorale
 */

    }

    public void setDelegates(List<Delegate> delegates) {
        this.delegates = delegates;
    }

    public List<Location> getPathRace() {
        return pathRace;
    }

    public void setPathRace(List<Location> pathRace) {
        this.pathRace = pathRace;
    }

    public HashMap<Location, List<Delegate>> getOrganizzatori() {
        return organizzatori;
    }

    public void setOrganizzatori(HashMap<Location, List<Delegate>> organizzatori) {
        this.organizzatori = organizzatori;
    }

    public HashMap<String, HashMap<Integer, Long>> getPassageTimes() {
        return passageTimes;
    }

    public void setPassageTimes(HashMap<String, HashMap<Integer, Long>> passageTimes) {
        this.passageTimes = passageTimes;
    }

}
