namespace GestionEmpleadosInterfaz
{
    partial class CrearEmpleado
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            comboBoxDepartamento = new ComboBox();
            textBoxTelefono = new TextBox();
            textBoxCorreo = new TextBox();
            textBoxPuesto = new TextBox();
            textBoxNombre = new TextBox();
            textBoxDni = new TextBox();
            labelTelefono = new Label();
            labelCorreo = new Label();
            labelDepartamento = new Label();
            labelPuesto = new Label();
            labelNombre = new Label();
            labelDni = new Label();
            buttonCrear = new Button();
            SuspendLayout();
            // 
            // comboBoxDepartamento
            // 
            comboBoxDepartamento.DropDownStyle = ComboBoxStyle.DropDownList;
            comboBoxDepartamento.FormattingEnabled = true;
            comboBoxDepartamento.Location = new Point(67, 255);
            comboBoxDepartamento.Name = "comboBoxDepartamento";
            comboBoxDepartamento.Size = new Size(404, 33);
            comboBoxDepartamento.TabIndex = 30;
            // 
            // textBoxTelefono
            // 
            textBoxTelefono.Location = new Point(67, 381);
            textBoxTelefono.Name = "textBoxTelefono";
            textBoxTelefono.Size = new Size(404, 31);
            textBoxTelefono.TabIndex = 29;
            // 
            // textBoxCorreo
            // 
            textBoxCorreo.Location = new Point(67, 319);
            textBoxCorreo.Name = "textBoxCorreo";
            textBoxCorreo.Size = new Size(404, 31);
            textBoxCorreo.TabIndex = 28;
            // 
            // textBoxPuesto
            // 
            textBoxPuesto.Location = new Point(67, 193);
            textBoxPuesto.Name = "textBoxPuesto";
            textBoxPuesto.Size = new Size(404, 31);
            textBoxPuesto.TabIndex = 27;
            // 
            // textBoxNombre
            // 
            textBoxNombre.Location = new Point(67, 131);
            textBoxNombre.Name = "textBoxNombre";
            textBoxNombre.Size = new Size(404, 31);
            textBoxNombre.TabIndex = 26;
            // 
            // textBoxDni
            // 
            textBoxDni.Location = new Point(67, 69);
            textBoxDni.Name = "textBoxDni";
            textBoxDni.Size = new Size(404, 31);
            textBoxDni.TabIndex = 25;
            // 
            // labelTelefono
            // 
            labelTelefono.AutoSize = true;
            labelTelefono.Location = new Point(67, 353);
            labelTelefono.Name = "labelTelefono";
            labelTelefono.Size = new Size(79, 25);
            labelTelefono.TabIndex = 24;
            labelTelefono.Text = "Teléfono";
            // 
            // labelCorreo
            // 
            labelCorreo.Location = new Point(67, 291);
            labelCorreo.Name = "labelCorreo";
            labelCorreo.Size = new Size(66, 25);
            labelCorreo.TabIndex = 23;
            labelCorreo.Text = "Correo";
            // 
            // labelDepartamento
            // 
            labelDepartamento.AutoSize = true;
            labelDepartamento.Location = new Point(67, 227);
            labelDepartamento.Name = "labelDepartamento";
            labelDepartamento.Size = new Size(127, 25);
            labelDepartamento.TabIndex = 22;
            labelDepartamento.Text = "Departamento";
            // 
            // labelPuesto
            // 
            labelPuesto.AutoSize = true;
            labelPuesto.Location = new Point(67, 165);
            labelPuesto.Name = "labelPuesto";
            labelPuesto.Size = new Size(66, 25);
            labelPuesto.TabIndex = 21;
            labelPuesto.Text = "Puesto";
            // 
            // labelNombre
            // 
            labelNombre.AutoSize = true;
            labelNombre.Location = new Point(67, 103);
            labelNombre.Name = "labelNombre";
            labelNombre.Size = new Size(78, 25);
            labelNombre.TabIndex = 20;
            labelNombre.Text = "Nombre";
            // 
            // labelDni
            // 
            labelDni.AutoSize = true;
            labelDni.Location = new Point(67, 41);
            labelDni.Name = "labelDni";
            labelDni.Size = new Size(43, 25);
            labelDni.TabIndex = 19;
            labelDni.Text = "DNI";
            // 
            // buttonCrear
            // 
            buttonCrear.Location = new Point(213, 462);
            buttonCrear.Name = "buttonCrear";
            buttonCrear.Size = new Size(112, 34);
            buttonCrear.TabIndex = 31;
            buttonCrear.Text = "Crear";
            buttonCrear.UseVisualStyleBackColor = true;
            buttonCrear.Click += buttonCrear_Click;
            // 
            // CrearEmpleado
            // 
            AutoScaleDimensions = new SizeF(10F, 25F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(538, 537);
            Controls.Add(buttonCrear);
            Controls.Add(comboBoxDepartamento);
            Controls.Add(textBoxTelefono);
            Controls.Add(textBoxCorreo);
            Controls.Add(textBoxPuesto);
            Controls.Add(textBoxNombre);
            Controls.Add(textBoxDni);
            Controls.Add(labelTelefono);
            Controls.Add(labelCorreo);
            Controls.Add(labelDepartamento);
            Controls.Add(labelPuesto);
            Controls.Add(labelNombre);
            Controls.Add(labelDni);
            FormBorderStyle = FormBorderStyle.FixedDialog;
            MaximizeBox = false;
            MinimizeBox = false;
            Name = "CrearEmpleado";
            Text = "Crear empleado";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private ComboBox comboBoxDepartamento;
        private TextBox textBoxTelefono;
        private TextBox textBoxCorreo;
        private TextBox textBoxPuesto;
        private TextBox textBoxNombre;
        private TextBox textBoxDni;
        private Label labelTelefono;
        private Label labelCorreo;
        private Label labelDepartamento;
        private Label labelPuesto;
        private Label labelNombre;
        private Label labelDni;
        private Button buttonCrear;
    }
}