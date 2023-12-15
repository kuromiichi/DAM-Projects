using System.Text.RegularExpressions;
using GestionEmpleadosInterfaz.models;

namespace GestionEmpleadosInterfaz.validators;

public class EmpleadoValidator
{
    public static string ValidarEmpleado(Empleado empleado)
    {
        if (string.IsNullOrWhiteSpace(empleado.Dni))
            return "El DNI no puede estar vacío";
        Regex dniRegex = new("^[0-9]{8}[A-Z]$");
        if (!dniRegex.IsMatch(empleado.Dni))
            return "El DNI debe tener 8 cifras y una letra mayúscula";
        if (string.IsNullOrWhiteSpace(empleado.Nombre))
            return "El nombre no puede estar vacío";
        if (string.IsNullOrWhiteSpace(empleado.Correo))
            return "El correo no puede estar vacío";
        Regex correoRegex = new("^[^@]+@[^@]+$");
        if (!correoRegex.IsMatch(empleado.Correo))
            return "El correo debe ser válido";
        if (string.IsNullOrWhiteSpace(empleado.Telefono))
            return "El teléfono no puede estar vacío";
        Regex telefonoRegex = new("^\\+?[0-9]+$");
        if (!telefonoRegex.IsMatch(empleado.Telefono))
            return "El teléfono debe ser válido";
        if (string.IsNullOrWhiteSpace(empleado.Puesto))
            return "El puesto no puede estar vacío";
        if (string.IsNullOrWhiteSpace(empleado.Departamento))
            return "El departamento no puede estar vacío";
        return "OK";
    }
}