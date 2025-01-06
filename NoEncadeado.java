

class NoEncadeado<T> {
    protected T elemento;
    protected NoEncadeado<T> proximo = null;

    protected NoEncadeado(T elemento){
        this.elemento = elemento;
    }
}