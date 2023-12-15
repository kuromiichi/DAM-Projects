using GestionEmpleadosInterfaz.models;

namespace GestionEmpleadosInterfaz.repositories;

public class DepartamentoRepository : ICrudRepository<Departamento, int>
{
    private static int _nextId = 1;
    private readonly Dictionary<int, Departamento> _departamentos = new();

    public int Add(Departamento entity)
    {
        if (_departamentos.Values.ToList().Any(e => e.Nombre == entity.Nombre))
            return 0;
        entity.Id = GetNextId();
        _departamentos[entity.Id] = entity;
        return entity.Id;
    }

    public Departamento? Get(int id)
    {
        return _departamentos.GetValueOrDefault(id);
    }

    public Departamento? GetByName(string name)
    {
        return _departamentos.Values.ToList().Find(e => e.Nombre == name);
    }

    public List<Departamento> GetAll()
    {
        return _departamentos.Values.ToList();
    }

    public bool Update(int id, Departamento entity)
    {
        if (!_departamentos.ContainsKey(id)) return false;
        if (_departamentos.Values.ToList().Any(e => e.Nombre == entity.Nombre && e.Id != id))
            return false;
        entity.Id = id;
        _departamentos[id] = entity;
        return true;
    }

    public bool Remove(int id)
    {
        return _departamentos.Remove(id);
    }

    private static int GetNextId()
    {
        return _nextId++;
    }
}