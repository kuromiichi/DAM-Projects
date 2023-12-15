using GestionEmpleadosInterfaz.models;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GestionEmpleadosInterfaz
{
    public partial class CrearEmpleado : Form
    {
        private List<Departamento> departamentos;
        public string dni;
        public string nombre;
        public string puesto;
        public string departamento;
        public string correo;
        public string telefono;

        public CrearEmpleado(List<Departamento> departamentos)
        {
            this.departamentos = departamentos;
            InitializeComponent();
            comboBoxDepartamento.Items.AddRange(departamentos.ToArray());
        }

        private void buttonCrear_Click(object sender, EventArgs e)
        {
            dni = textBoxDni.Text;
            nombre = textBoxNombre.Text;
            puesto = textBoxPuesto.Text;
            departamento = comboBoxDepartamento.Text;
            correo = textBoxCorreo.Text;
            telefono = textBoxTelefono.Text;

            DialogResult = DialogResult.OK;
        }
    }
}
