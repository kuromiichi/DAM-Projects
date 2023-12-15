namespace GestionEmpleados.models;

public class Empleado
{
    public string Correo;
    public string Departamento;
    public string Dni;
    public string Nombre;
    public string Puesto;
    public string Telefono;

    public Empleado(string dni, string nombre, string correo, string telefono, string puesto, string departamento)
    {
        Dni = dni;
        Nombre = nombre;
        Correo = correo;
        Telefono = telefono;
        Puesto = puesto;
        Departamento = departamento;
    }

    public override string ToString()
    {
        return $"""
                Empleado [
                    DNI = {Dni}
                    Nombre = {Nombre}
                    Correo = {Correo}
                    Telefono = {Telefono}
                    Puesto = {Puesto}
                    Departamento = {Departamento}
                ]
                """;
    }
}