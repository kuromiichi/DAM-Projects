using GestionEmpleadosInterfaz.models;

namespace GestionEmpleadosInterfaz.repositories;

public class EmpleadoRepository : ICrudRepository<Empleado, string>
{
    private readonly Dictionary<string, Empleado> _empleados = new();

    public string? Add(Empleado entity)
    {
        return _empleados.TryAdd(entity.Dni, entity) ? entity.Dni : null;
    }

    public Empleado? Get(string id)
    {
        return _empleados.GetValueOrDefault(id);
    }

    public List<Empleado> GetAll()
    {
        return _empleados.Values.ToList();
    }

    public bool Update(string id, Empleado entity)
    {
        if (!_empleados.ContainsKey(id)) return false;
        _empleados[id] = entity;
        return true;
    }

    public bool Remove(string id)
    {
        return _empleados.Remove(id);
    }
}