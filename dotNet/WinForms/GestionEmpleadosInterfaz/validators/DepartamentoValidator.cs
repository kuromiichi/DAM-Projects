using GestionEmpleadosInterfaz.models;

namespace GestionEmpleadosInterfaz.validators;

public class DepartamentoValidator
{
    public static string ValidarDepartamento(Departamento departamento)
    {
        if (string.IsNullOrWhiteSpace(departamento.Nombre))
            return "El nombre no puede estar vacío";
        return "OK";
    }
}