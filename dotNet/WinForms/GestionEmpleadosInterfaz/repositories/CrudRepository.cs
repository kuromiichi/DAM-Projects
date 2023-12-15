namespace GestionEmpleadosInterfaz.repositories;

public interface ICrudRepository<T, TId>
{
    public TId? Add(T entity);
    public T? Get(TId id);
    public List<T> GetAll();
    public bool Update(TId id, T entity);
    public bool Remove(TId id);
}