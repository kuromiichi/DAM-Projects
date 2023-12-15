using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SorteoWF.ui
{
    public partial class CreatePrize : Form
    {
        public string name { get; set; }
        public CreatePrize()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            name = textBox1.Text;
            DialogResult = DialogResult.OK;
        }
    }
}
