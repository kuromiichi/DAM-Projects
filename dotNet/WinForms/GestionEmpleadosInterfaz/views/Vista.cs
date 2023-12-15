using GestionEmpleadosInterfaz.controllers;
using GestionEmpleadosInterfaz.repositories;
using GestionEmpleadosInterfaz.models;
using GestionEmpleadosInterfaz.exceptions;

namespace GestionEmpleadosInterfaz
{
    public partial class Vista : Form
    {
        static DepartamentoController depController = new(new DepartamentoRepository());
        static EmpleadoController empController = new(new EmpleadoRepository());

        public Vista()
        {
            InitializeComponent();
        }

        private void ActualizarDepartamentos()
        {
            listBoxDepartamentos.Items.Clear();
            listBoxDepartamentos.Items
                .AddRange(depController.ObtenerListaDepartamentos().ToArray());
            comboBoxDepartamento.Items.Clear();
            comboBoxDepartamento.Items
                .AddRange(depController.ObtenerListaDepartamentos().ToArray());
        }

        private void ActualizarEmpleados()
        {
            Departamento selectedDep = listBoxDepartamentos.SelectedItem as Departamento;
            if (selectedDep == null) return;

            listBoxEmpleados.Items.Clear();
            listBoxEmpleados.Items
                .AddRange(ObtenerEmpleadosPorDepartamento(selectedDep.Nombre).ToArray());
        }

        private void LimpiarInfo()
        {
            textBoxDni.Text = "";
            textBoxNombre.Text = "";
            textBoxPuesto.Text = "";
            comboBoxDepartamento.SelectedItem = null;
            textBoxCorreo.Text = "";
            textBoxTelefono.Text = "";
        }

        private List<Empleado> ObtenerEmpleadosPorDepartamento(string departamento)
        {
            List<Empleado> empleados = empController.ObtenerListaEmpleados();
            return empleados.FindAll(e => e.Departamento == departamento);
        }

        private void listBoxDepartamentos_SelectedIndexChanged(object sender, EventArgs e)
        {
            LimpiarInfo();
            ActualizarEmpleados();
        }

        private void listBoxEmpleados_SelectedIndexChanged(object sender, EventArgs e)
        {
            Empleado emp = listBoxEmpleados.SelectedItem as Empleado;
            if (emp == null) return;

            textBoxDni.Text = emp.Dni;
            textBoxNombre.Text = emp.Nombre;
            textBoxPuesto.Text = emp.Puesto;
            comboBoxDepartamento.SelectedItem =
                depController.ObtenerDepartamentoPorNombre(emp.Departamento);
            textBoxCorreo.Text = emp.Correo;
            textBoxTelefono.Text = emp.Telefono;
        }

        private void buttonNuevo_Click(object sender, EventArgs e)
        {
            CrearEmpleado crearEmpleado = new(depController.ObtenerListaDepartamentos());
            if (crearEmpleado.ShowDialog() == DialogResult.OK)
            {
                CrearEmpleado(
                    crearEmpleado.dni,
                    crearEmpleado.nombre,
                    crearEmpleado.correo,
                    crearEmpleado.telefono,
                    crearEmpleado.puesto,
                    crearEmpleado.departamento
                );
                ActualizarDepartamentos();
                ActualizarEmpleados();
            }
        }

        private void CrearEmpleado(
            string dni,
            string nombre,
            string correo,
            string telefono,
            string puesto,
            string departamento
        )
        {
            try
            {
                empController.CrearEmpleado(
                    new(dni, nombre, correo, telefono, puesto, departamento)
                )

                ActualizarEmpleados();
                MessageBox.Show(
                    "Se ha creado con �xito",
                    "Crear empleado",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information);
            }
            catch (GestionEmpleadosException e)
            {
                MessageBox.Show(
                    e.Message,
                    "Error al crear empleado",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error);
            }
        }

        private void buttonBorrar_Click(object sender, EventArgs e)
        {
            Empleado emp = listBoxEmpleados.SelectedItem as Empleado;
            if (emp == null) return;

            empController.EliminarEmpleado(emp.Dni);

            LimpiarInfo();
            ActualizarEmpleados();
            MessageBox.Show(
                "Se ha eliminado con �xito",
                "Eliminar empleado",
                MessageBoxButtons.OK,
                MessageBoxIcon.Information);
        }

        private void buttonGuardar_Click(object sender, EventArgs e)
        {
            if (listBoxEmpleados.SelectedItem == null) return;

            Empleado emp = new(
                textBoxDni.Text,
                textBoxNombre.Text,
                textBoxCorreo.Text,
                textBoxTelefono.Text,
                textBoxPuesto.Text,
                comboBoxDepartamento.Text
            );

            try
            {
                empController.ActualizarEmpleado(emp);

                LimpiarInfo();
                ActualizarEmpleados();
                MessageBox.Show(
                    "Se ha actualizado con �xito",
                    "Actualizar empleado",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information);
            }
            catch (GestionEmpleadosException exc)
            {
                MessageBox.Show(
                    exc.Message,
                    "Error al actualizar empleado",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error);
            }
        }

        private void buttonNuevoDep_Click(object sender, EventArgs e)
        {
            CrearDepartamento crearDepartamento = new();
            if (crearDepartamento.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    depController.CrearDepartamento(
                        new Departamento(crearDepartamento.nombre)
                    );
                    ActualizarDepartamentos();
                    ActualizarEmpleados();
                    MessageBox.Show(
                        "Se ha creado con �xito",
                        "Crear departamento",
                        MessageBoxButtons.OK,
                        MessageBoxIcon.Information);
                }
                catch (GestionEmpleadosException exc)
                {
                    MessageBox.Show(
                        exc.Message,
                        "Error al crear departamento",
                        MessageBoxButtons.OK,
                        MessageBoxIcon.Error);
                }
                ActualizarDepartamentos();
                ActualizarEmpleados();
            }
        }

        private void buttonBorrarDep_Click(object sender, EventArgs e)
        {
            Departamento dep = listBoxDepartamentos.SelectedItem as Departamento;
            if (dep == null) return;

            if (ObtenerEmpleadosPorDepartamento(dep.Nombre).Count != 0)
            {
                MessageBox.Show(
                    "No se puede borrar el departamento; " +
                    "existen empleados pertenecientes a �l",
                    "Error al borrar departamento",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error
                );
                return;
            }

            depController.EliminarDepartamento(dep.Id);

            LimpiarInfo();
            ActualizarDepartamentos();
            ActualizarEmpleados();
            MessageBox.Show(
                "Se ha eliminado con �xito",
                "Eliminar departamento",
                MessageBoxButtons.OK,
                MessageBoxIcon.Information);

        }
    }
}
