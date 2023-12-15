namespace GestionEmpleadosInterfaz.exceptions;

public class GestionEmpleadosException : Exception
{
    // Create inner classes for CRUD exceptions
    public GestionEmpleadosException(string message) : base(message)
    {
    }

    public class EmpleadoNotFoundException : GestionEmpleadosException
    {
        public EmpleadoNotFoundException(string message) : base(message)
        {
        }
    }

    public class EmpleadoAlreadyExistsException : GestionEmpleadosException
    {
        public EmpleadoAlreadyExistsException(string message) : base(message)
        {
        }
    }

    public class InvalidEmpleadoException : GestionEmpleadosException
    {
        public InvalidEmpleadoException(string message) : base(message)
        {
        }
    }

    public class DepartamentoNotFoundException : GestionEmpleadosException
    {
        public DepartamentoNotFoundException(string message) : base(message)
        {
        }
    }

    public class DepartamentoAlreadyExistsException : GestionEmpleadosException
    {
        public DepartamentoAlreadyExistsException(string message) : base(message)
        {
        }
    }

    public class InvalidDepartamentoException : GestionEmpleadosException
    {
        public InvalidDepartamentoException(string message) : base(message)
        {
        }
    }
}