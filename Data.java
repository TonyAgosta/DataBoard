public interface Data {

    // Overview: tipo di dato generico
    // Typical Element:<Categoria,NomeDelPost,NumeroDiLike> dove:
    //                  Categoria e` la categoria a cui appartiene il post
    //                  NomeDelPost e` il nome associato al post
    //                  NumeroDiLike e` il numero di like totatolizzato dal post

    // Stampa tutti i valori degli attributi di this
    public void Display();

    /**
     * Returns:Stampa this.Categoria, this.Nome, this.Numerodilike,
     */

    // Associa ad un dato una categoria
    public void setCategory(String Categoria) throws NullPointerException;

    /**
     * Requires: Categoria!= null Throws: se Categoria==null lancia l'eccezione
     * NullPointerException Modifies:this Effects:this.categoria=Categoria
     */

    // Restituisce la categoria associata ad un dato
    public String getCategory();

    /**
     * Returns: this.categoria
     */

    // Aggoiunge like al dato che lo chiama
    public void AddLike();

    /**
     * Modifies:this Effects: this.pre=this.pre+1;
     */
    public Integer getLike();

    /**
     * Returns: this.NumeroDiLike
     */
    public String getNamePost();
    /**
     * Returns :this.NamePost
     */

}
