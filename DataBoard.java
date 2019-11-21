import java.util.Iterator;
import java.util.List;

public interface DataBoard<E extends Data> {
        /**
         * OVERVIEW:contenitore di oggetti generici mutabili che estendono il tipo di
         * data Data.
         */

        /**
         * Typical Element:<Category,ListaAmici,ListaDati> dove:
         *                  Category e` l'insieme delle categorie a cui possono appartenere i post
         *                  ListaAmici e` la lista di amici che sono iscritti ad una specifica Category
         *                  ListaDati e` la lista di dati presente in una specifica Category
         */

        public void createCategory(String Category, String passw) throws NullPointerException, WrongPasswordException;

        /**
         * @Requires: Category != null && passw!=null && this.passw.(equals(passw))
         * @Throws:se Category == null || passw == null lancia l'eccezione
         *            NullPointerException(UNCHECKED) && se !(this.passw.equals(passw))
         *            lancia l'eccezione WrongPasswordException(CHECKED)
         * @Modifies: this
         * @Effects: this.post = this.pre U Category se Category non esiste gia`,
         *           altrimenti il metodo non ha alcun effetto poiche` in this.post
         *           Category e` comunque presente
         */

        public void removeCategory(String Category, String passw) throws NullPointerException, WrongPasswordException;

        /**
         * @Requires: Category!=null && passw!=null && this.passw.(equals(passw))
         * @Throws: se Category==null || passw==null lancia l'eccezione
         *          NullPointerException(UNCHECKED) && se !(this.passw.equals(passw))
         *          lancia l'eccezione WrongPasswordException(CHECKED)
         * @Modifies:this
         * @Effects: this.post=this.pre \ Category se Category e` presente, altrimenti
         *           il metodo non ha alcun effetto poiche` in this.post Categori non e`
         *           comunque presente
         */

        public void addFriend(String Category, String passw, String friend)
                        throws NullPointerException, WrongPasswordException, NoCategoryException;

        /**
         * @Requires:Category!=null && passw!=null && friend!=null &&
         *                          this.passw.(equals(passw)) && Category deve essere
         *                          presente
         * @Throws:se Category==null || passw==null || friend==null lancia l'eccezione
         *            NullPointerException(UNCHECKED) && se !(this.passw.equals(passw))
         *            lancia l'eccezione WrongPasswordException(CHECKED) && se Category
         *            non e` presente lancia l'eccezione NoCategoryException(CHECKED)
         * @Modifies:this
         * @Effects: aggiunge un amico ad una categoria di dati se non e` presente nella
         *           lista della categoria, altrimenti il metodo non ha alcun effetto
         *           poiche` in this.post friend sara` comunque presente
         */

        public void removeFriend(String Category, String passw, String friend)
                        throws NullPointerException, WrongPasswordException, NoCategoryException;

        /**
         * @Requires:Category!=null && passw!=null && friend!=null &&
         *                          this.passw.(equals(passw)) && Category deve essere
         *                          presente
         * @Throws:se Category==null || passw==null || friend==null lancia l'eccezione
         *            NullPointerException(UNCHECKED) && se !(this.passw.equals(passw))
         *            lancia l'eccezione WrongPasswordException(CHECKED) && se Category
         *            non e` presente lancia l'eccezione NoCategoryException(UNCHECKED)
         * @Modifies:this
         * @Effects: rimuove friend da Category se e` presente in Category, altrimenti
         *           il metodo non ha alcun effetto pocihe` in this.post friend comunque
         *           non e` presente
         */

        public boolean put(String passw, E dato, String categoria)
                        throws NullPointerException, WrongPasswordException, NoCategoryException;

        /**
         * @throws NoCategoryException
         * @Requires: passw!=null && categoria!=null && this.passw.equals(passw) &&
         *            dato!=null && Category deve essere presente
         * @Throws: se passw==null || categoria==null || dato==null lancia l'eccezione
         *          NullPointereEception(UNCHEKED) && se !(this.passw.equals(passw))
         *          lancia l'eccezione WrongPasswordException(CHECKED) && se Category
         *          non e` presente lancia l'eccezione NoCategoryException(CHECKED)
         * @Modifies:this
         * @Effects: se riesce ad inserire l'elemento(cioe` non e` presente) restituisce
         *           true, altrimenti restituisce false //da questo momento in poi
         *           stiamo assumendo che non ci possono essere duplicati
         */

        public E get(String passw, E dato)
                        throws NullPointerException, WrongPasswordException, DataNotFoundException, NoCategoryException;

        /**
         * @Requires: passw!=null && dato!=null && this.passw.equals(passw)
         * @Throws: se passw==null || dato==null lancia l'eccezione
         *          NullPointerException(UNCHECKED) && se !(this.passw.equals(passw))
         *          lancia l'eccezione WrongPasswordException(CHECKED) &&
         * @Return: restituisce dato se appartiene alla collezione altrimenti
         *          restituisce null
         */

        public E remove(String passw, E dato) throws NullPointerException, WrongPasswordException;

        /**
         * @Requires: passw!=null && dato!=null && this.passw.equals(passw)
         * @Throws: se passw==null || dato==null lancia l'eccezione
         *          NullPointerException(UNCHECKED) && se !(this.passw.equals(passw))
         *          lancia l'eccezione WrongPasswordException(CHECKED) &&
         * @Modifies: this
         * @Return: elimina il dato dalla collezione se presente e lo restituisce
         *          altrimenti restituisce null
         */

        public List<E> getDataCategory(String passw, String Category)
                        throws NullPointerException, WrongPasswordException, NoCategoryException;

        /**
         * @Requires: passw!=null && Category!=null && this.passw.equals(passw) &&
         *            Category deve esistere
         * @Throws: se passw==null || Category==null lancia l'eccezione
         *          NullPointerException(UNCHECKED) && se !(this.passw.equals(passw))
         *          lancia l'eccezione WrongPasswordException(CHECKED) && se Category
         *          non e` presente lancia l'eccezione NoCategoryException(CHECKED)
         * @Return: crea e restituisce la lista dei dati presenti in Category
         */

        public Iterator<E> getIterator(String passw) throws NullPointerException, WrongPasswordException;

        /**
         * @Requires: passw!=null && this.passw.equals(passw)
         * @Throws: se passw==null lancia l'eccezione NullPointerException(UNCHECKED) &&
         *          se !(this.passw.equals(passw)) lancia l'eccezione
         *          WrongPasswordException(CHECKED)
         * @Return: Crea una collezione di dati ordinata rispetto ai like e restituisce
         *          un iteratore su di essa
         * 
         */
        public void insertLike(String friend, E data)
                        throws NullPointerException, DataNotFoundException, PermessDeniedException, NoCategoryException;

        /**
         * @Requires: friend!=null && data!=null && data deve appartenere alla
         *            collezione && friend appartiene alla lista di amici della
         *            categoria di cui il dato fa parte
         * @Throws: se friend==null || data==null lancia l'eccezione
         *          NullPointerException(UNCHECKED) && se data non appartiene alla
         *          collezione lancia l'eccezione DataNotFoundException(CHECKED) && se
         *          friend non fa parte della lista di amici della categoria di cui il
         *          dato fa parte lancia l'eccezione PermessDeniedException(CHECKED)
         * @Modifies:this
         * @Effects: incrementa il numero di like associati al dato
         */

        public Iterator<E> getFriendIterator(String friend) throws NullPointerException, NoOneCategoryException;
        /**
         * @Requires: friend!=null && friend deve essere in una lista di amici di almeno
         *            una categoria
         * @Throws: se friend==null lancia l'eccezione NullPointerException(UNCHECKED)
         *          se friend non e` in una lista di almeno una categoria lancia
         *          l'eccezione NoOneCategoryException(CHECKED)
         * @Return: Crea una collezione con tutti i dati presenti in bacheca e condivisi
         *          con friend e restituisce un iteratore su di essa
         */
}
