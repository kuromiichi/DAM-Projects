namespace GestionEmpleados.models;

public class Departamento
{
    public int Id;
    public string Nombre;

    public Departamento(string nombre)
    {
        Nombre = nombre;
    }

    public override string ToString()
    {
        return $"""
                Departamento [
                    ID = {Id}
                    Nombre = {Nombre}
                ]
                """;
    }
}