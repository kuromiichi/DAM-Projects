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
    public partial class CrearDepartamento : Form
    {
        public string nombre;

        public CrearDepartamento()
        {
            InitializeComponent();
        }

        private void buttonCrear_Click(object sender, EventArgs e)
        {
            nombre = textBoxNombre.Text;
            DialogResult = DialogResult.OK;
        }
    }
}
