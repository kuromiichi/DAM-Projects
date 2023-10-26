using GestionEmpleados.Exceptions;
using GestionEmpleados.models;
using GestionEmpleados.repositories;
using GestionEmpleados.validators;

namespace GestionEmpleados.controllers;

public class DepartamentoController
{
    private readonly DepartamentoRepository _repository;

    public DepartamentoController(DepartamentoRepository repository)
    {
        _repository = repository;
    }

    public int CrearDepartamento(Departamento departamento)
    {
        var validation = DepartamentoValidator.ValidarDepartamento(departamento);
        if (validation != "OK")
            throw new GestionEmpleadosException.InvalidDepartamentoException(
                "El departamento no es válido: " + validation
            );
        var id = _repository.Add(departamento);
        if (id == 0)
            throw new GestionEmpleadosException.DepartamentoAlreadyExistsException(
                "El departamento ya existe: " + departamento.Nombre
            );
        return id;
    }

    public Departamento ObtenerDepartamento(int id)
    {
        var departamento = _repository.Get(id);
        if (departamento == null)
            throw new GestionEmpleadosException.DepartamentoNotFoundException("El departamento no existe: " + id);
        return departamento;
    }

    public List<Departamento> ObtenerListaDepartamentos()
    {
        return _repository.GetAll();
    }

    public void ActualizarDepartamento(int id, Departamento departamento)
    {
        var validation = DepartamentoValidator.ValidarDepartamento(departamento);
        if (validation != "OK")
            throw new GestionEmpleadosException.InvalidDepartamentoException(
                "El departamento no es válido: " + validation
            );
        if (!_repository.Update(id, departamento))
        {
            if (ObtenerListaDepartamentos().Any(d => d.Nombre == departamento.Nombre))
                throw new GestionEmpleadosException.DepartamentoAlreadyExistsException("El departamento ya existe: " +
                    departamento);
            throw new GestionEmpleadosException.DepartamentoNotFoundException("El departamento no existe: " + id);
        }
    }

    public void EliminarDepartamento(int id)
    {
        if (!_repository.Remove(id))
            throw new GestionEmpleadosException.DepartamentoNotFoundException("El departamento no existe: " + id);
    }
}