using GestionEmpleadosInterfaz.exceptions;
using GestionEmpleadosInterfaz.models;
using GestionEmpleadosInterfaz.repositories;
using GestionEmpleadosInterfaz.validators;

namespace GestionEmpleadosInterfaz.controllers;

public class EmpleadoController
{
    private readonly EmpleadoRepository _repository;

    public EmpleadoController(EmpleadoRepository repository)
    {
        _repository = repository;
    }

    public void CrearEmpleado(Empleado empleado)
    {
        var validation = EmpleadoValidator.ValidarEmpleado(empleado);
        if (validation != "OK")
            throw new GestionEmpleadosException.InvalidEmpleadoException(
                "El empleado no es válido: " + validation
            );
        if (_repository.Add(empleado) == null)
            throw new GestionEmpleadosException.EmpleadoAlreadyExistsException(
                "Ya existe un empleado con ese DNI: " + empleado.Dni
            );
    }

    public Empleado ObtenerEmpleado(string dni)
    {
        var empleado = _repository.Get(dni);
        if (empleado == null)
            throw new GestionEmpleadosException.EmpleadoNotFoundException("El empleado no existe: " + dni);
        return empleado;
    }

    public List<Empleado> ObtenerListaEmpleados()
    {
        return _repository.GetAll();
    }

    public void ActualizarEmpleado(Empleado empleado)
    {
        var validation = EmpleadoValidator.ValidarEmpleado(empleado);
        if (validation != "OK")
            throw new GestionEmpleadosException.InvalidEmpleadoException(
                "El empleado no es válido: " + validation
            );
        if (!_repository.Update(empleado.Dni, empleado))
            throw new GestionEmpleadosException.EmpleadoNotFoundException("El empleado no existe: " + empleado.Dni);
    }

    public void EliminarEmpleado(string dni)
    {
        if (!_repository.Remove(dni))
            throw new GestionEmpleadosException.EmpleadoNotFoundException("El empleado no existe: " + dni);
    }
}