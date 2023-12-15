using System.Text.RegularExpressions;
using GestionEmpleados.controllers;
using GestionEmpleados.Exceptions;
using GestionEmpleados.models;
using GestionEmpleados.repositories;

EmpleadoController empController = new(new EmpleadoRepository());
DepartamentoController depController = new(new DepartamentoRepository());

while (true)
    MenuPrincipal();

void MenuPrincipal()
{
    MostrarOpcionesMenu();

    var opcion = Console.ReadLine()!;
    var numeroOpcion = GestionarOpcion(opcion);

    switch (numeroOpcion)
    {
        case 1:
            CrearDepartamento();
            break;
        case 2:
            ObtenerDepartamento();
            break;
        case 3:
            ObtenerListaDepartamentos();
            break;
        case 4:
            ActualizarDepartamento();
            break;
        case 5:
            EliminarDepartamento();
            break;
        case 6:
            CrearEmpleado();
            break;
        case 7:
            ObtenerEmpleado();
            break;
        case 8:
            ObtenerListaEmpleados();
            break;
        case 9:
            ActualizarEmpleado();
            break;
        case 10:
            EliminarEmpleado();
            break;
        case 0:
            Environment.Exit(0);
            break;
        default:
            Console.WriteLine("Opción no válida");
            break;
    }
}

void CrearDepartamento()
{
    Console.Clear();
    Console.WriteLine("Crear Departamento");
    Console.WriteLine();

    Console.Write("Nombre: ");
    var nombre = Console.ReadLine()!;
    try
    {
        var id = depController.CrearDepartamento(new Departamento(nombre));
        Console.WriteLine("Departamento creado con ID " + id);
    }
    catch (GestionEmpleadosException e)
    {
        Console.WriteLine(e.Message);
    }
    finally
    {
        Console.WriteLine("Pulsa enter para volver");
        Console.ReadLine();
    }
}

void ObtenerDepartamento()
{
    Console.Clear();
    Console.WriteLine("Obtener Departamento");
    Console.WriteLine();

    Console.Write("ID: ");
    var id = int.Parse(Console.ReadLine()!);
    try
    {
        Console.WriteLine(depController.ObtenerDepartamento(id).ToString());
    }
    catch (GestionEmpleadosException e)
    {
        Console.WriteLine(e.Message);
    }
    finally
    {
        Console.WriteLine("Pulsa enter para volver");
        Console.ReadLine();
    }
}

void ObtenerListaDepartamentos()
{
    Console.Clear();
    Console.WriteLine("Obtener Lista de Departamentos");
    Console.WriteLine();

    depController.ObtenerListaDepartamentos()
        .ForEach(Console.WriteLine);

    Console.WriteLine();
    Console.WriteLine("Pulsa enter para volver");
    Console.ReadLine();
}

void ActualizarDepartamento()
{
    Console.Clear();
    Console.WriteLine("Actualizar Departamento");
    Console.WriteLine();

    Console.Write("ID: ");
    var id = int.Parse(Console.ReadLine()!);

    Console.Write("Nombre: ");
    var nombre = Console.ReadLine()!;
    try
    {
        depController.ActualizarDepartamento(id, new Departamento(nombre));
        Console.WriteLine("Departamento actualizado");
    }
    catch (GestionEmpleadosException e)
    {
        Console.WriteLine(e.Message);
    }
    finally
    {
        Console.WriteLine("Pulsa enter para volver");
        Console.ReadLine();
    }
}

void EliminarDepartamento()
{
    Console.Clear();
    Console.WriteLine("Eliminar Departamento");
    Console.WriteLine();

    Console.Write("ID: ");
    var id = int.Parse(Console.ReadLine()!);
    try
    {
        depController.EliminarDepartamento(id);
        Console.WriteLine("Departamento eliminado");
    }
    catch (GestionEmpleadosException e)
    {
        Console.WriteLine(e.Message);
    }
    finally
    {
        Console.WriteLine("Pulsa enter para volver");
        Console.ReadLine();
    }
}

void CrearEmpleado()
{
    Console.Clear();
    Console.WriteLine("Crear Empleado");
    Console.WriteLine();

    Console.Write("DNI: ");
    var dni = Console.ReadLine()!;
    Console.Write("Nombre: ");
    var nombre = Console.ReadLine()!;
    Console.Write("Correo: ");
    var email = Console.ReadLine()!;
    Console.Write("Teléfono: ");
    var telefono = Console.ReadLine()!;
    Console.Write("Puesto: ");
    var puesto = Console.ReadLine()!;
    Console.Write("Departamento: ");
    var departamento = Console.ReadLine()!;

    try
    {
        depController.ObtenerDepartamento(int.Parse(departamento));
        empController.CrearEmpleado(new Empleado(dni, nombre, email, telefono, puesto, departamento));
        Console.WriteLine("Empleado creado con DNI " + dni);
    }
    catch (FormatException)
    {
        Console.WriteLine("El departamento no es válido, debe ser un entero positivo");
    }
    catch (GestionEmpleadosException e)
    {
        Console.WriteLine(e.Message);
    }
    finally
    {
        Console.WriteLine("Pulsa enter para volver");
        Console.ReadLine();
    }
}

void ObtenerEmpleado()
{
    Console.Clear();
    Console.WriteLine("Obtener Empleado");
    Console.WriteLine();

    Console.Write("DNI: ");
    var dni = Console.ReadLine()!;
    try
    {
        Console.WriteLine(empController.ObtenerEmpleado(dni).ToString());
    }
    catch (GestionEmpleadosException e)
    {
        Console.WriteLine(e.Message);
    }
    finally
    {
        Console.WriteLine();
        Console.WriteLine("Pulsa enter para volver");
        Console.ReadLine();
    }
}

void ObtenerListaEmpleados()
{
    Console.Clear();
    Console.WriteLine("Obtener Lista de Empleados");
    Console.WriteLine();

    empController.ObtenerListaEmpleados()
        .ForEach(Console.WriteLine);

    Console.WriteLine();
    Console.WriteLine("Pulsa enter para volver");
    Console.ReadLine();
}

void ActualizarEmpleado()
{
    Console.Clear();
    Console.WriteLine("Actualizar Empleado");
    Console.WriteLine();

    Console.Write("DNI: ");
    var dni = Console.ReadLine()!;
    Console.Write("Nombre: ");
    var nombre = Console.ReadLine()!;
    Console.Write("Correo: ");
    var email = Console.ReadLine()!;
    Console.Write("Teléfono: ");
    var telefono = Console.ReadLine()!;
    Console.Write("Puesto: ");
    var puesto = Console.ReadLine()!;
    Console.Write("Departamento: ");
    var departamento = Console.ReadLine()!;

    try
    {
        empController.ActualizarEmpleado(new Empleado(dni, nombre, email, telefono, puesto, departamento));
        Console.WriteLine("Empleado actualizado");
    }
    catch (GestionEmpleadosException e)
    {
        Console.WriteLine(e.Message);
    }
    finally
    {
        Console.WriteLine("Pulsa enter para volver");
        Console.ReadLine();
    }
}

void EliminarEmpleado()
{
    Console.Clear();
    Console.WriteLine("Eliminar Empleado");
    Console.WriteLine();

    Console.Write("DNI: ");
    var dni = Console.ReadLine()!;
    try
    {
        empController.EliminarEmpleado(dni);
        Console.WriteLine("Empleado eliminado");
    }
    catch (GestionEmpleadosException e)
    {
        Console.WriteLine(e.Message);
    }
    finally
    {
        Console.WriteLine("Pulsa enter para volver");
        Console.ReadLine();
    }
}

int GestionarOpcion(string opcion)
{
    if (new Regex("\\d{1,2}").IsMatch(opcion)) return int.Parse(opcion);

    return -1;
}

void MostrarOpcionesMenu()
{
    Console.Clear();
    Console.WriteLine("Sistema de Gestión de Empleados");
    Console.WriteLine();
    Console.WriteLine("1. Crear Departamento");
    Console.WriteLine("2. Obtener Departamento");
    Console.WriteLine("3. Obtener Lista de Departamentos");
    Console.WriteLine("4. Actualizar Departamento");
    Console.WriteLine("5. Eliminar Departamento");
    Console.WriteLine("6. Crear Empleado");
    Console.WriteLine("7. Obtener Empleado");
    Console.WriteLine("8. Obtener Lista de Empleados");
    Console.WriteLine("9. Actualizar Empleado");
    Console.WriteLine("10. Eliminar Empleado");
    Console.WriteLine("0. Salir");
    Console.WriteLine();
    Console.Write("Elige una opción: ");
}