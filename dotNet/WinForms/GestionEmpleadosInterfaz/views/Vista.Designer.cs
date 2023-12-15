namespace GestionEmpleadosInterfaz
{
    partial class Vista
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            listBoxDepartamentos = new ListBox();
            listBoxEmpleados = new ListBox();
            labelDni = new Label();
            labelNombre = new Label();
            labelPuesto = new Label();
            labelDepartamento = new Label();
            labelCorreo = new Label();
            labelTelefono = new Label();
            textBoxDni = new TextBox();
            textBoxNombre = new TextBox();
            textBoxPuesto = new TextBox();
            textBoxCorreo = new TextBox();
            textBoxTelefono = new TextBox();
            buttonGuardar = new Button();
            buttonBorrar = new Button();
            buttonNuevo = new Button();
            comboBoxDepartamento = new ComboBox();
            buttonNuevoDep = new Button();
            buttonBorrarDep = new Button();
            SuspendLayout();
            // 
            // listBoxDepartamentos
            // 
            listBoxDepartamentos.FormattingEnabled = true;
            listBoxDepartamentos.ItemHeight = 25;
            listBoxDepartamentos.Location = new Point(12, 11);
            listBoxDepartamentos.Name = "listBoxDepartamentos";
            listBoxDepartamentos.Size = new Size(260, 629);
            listBoxDepartamentos.TabIndex = 1;
            listBoxDepartamentos.SelectedIndexChanged += listBoxDepartamentos_SelectedIndexChanged;
            // 
            // listBoxEmpleados
            // 
            listBoxEmpleados.FormattingEnabled = true;
            listBoxEmpleados.ItemHeight = 25;
            listBoxEmpleados.Location = new Point(300, 11);
            listBoxEmpleados.Name = "listBoxEmpleados";
            listBoxEmpleados.Size = new Size(260, 629);
            listBoxEmpleados.TabIndex = 2;
            listBoxEmpleados.SelectedIndexChanged += listBoxEmpleados_SelectedIndexChanged;
            // 
            // labelDni
            // 
            labelDni.Anchor = AnchorStyles.None;
            labelDni.AutoSize = true;
            labelDni.Location = new Point(669, 73);
            labelDni.Name = "labelDni";
            labelDni.Size = new Size(43, 25);
            labelDni.TabIndex = 3;
            labelDni.Text = "DNI";
            // 
            // labelNombre
            // 
            labelNombre.Anchor = AnchorStyles.None;
            labelNombre.AutoSize = true;
            labelNombre.Location = new Point(669, 135);
            labelNombre.Name = "labelNombre";
            labelNombre.Size = new Size(78, 25);
            labelNombre.TabIndex = 4;
            labelNombre.Text = "Nombre";
            // 
            // labelPuesto
            // 
            labelPuesto.Anchor = AnchorStyles.None;
            labelPuesto.AutoSize = true;
            labelPuesto.Location = new Point(669, 197);
            labelPuesto.Name = "labelPuesto";
            labelPuesto.Size = new Size(66, 25);
            labelPuesto.TabIndex = 5;
            labelPuesto.Text = "Puesto";
            // 
            // labelDepartamento
            // 
            labelDepartamento.Anchor = AnchorStyles.None;
            labelDepartamento.AutoSize = true;
            labelDepartamento.Location = new Point(669, 259);
            labelDepartamento.Name = "labelDepartamento";
            labelDepartamento.Size = new Size(127, 25);
            labelDepartamento.TabIndex = 6;
            labelDepartamento.Text = "Departamento";
            // 
            // labelCorreo
            // 
            labelCorreo.Anchor = AnchorStyles.None;
            labelCorreo.AutoSize = true;
            labelCorreo.Location = new Point(669, 323);
            labelCorreo.Name = "labelCorreo";
            labelCorreo.Size = new Size(66, 25);
            labelCorreo.TabIndex = 7;
            labelCorreo.Text = "Correo";
            // 
            // labelTelefono
            // 
            labelTelefono.Anchor = AnchorStyles.None;
            labelTelefono.AutoSize = true;
            labelTelefono.Location = new Point(669, 385);
            labelTelefono.Name = "labelTelefono";
            labelTelefono.Size = new Size(79, 25);
            labelTelefono.TabIndex = 8;
            labelTelefono.Text = "Teléfono";
            // 
            // textBoxDni
            // 
            textBoxDni.Anchor = AnchorStyles.None;
            textBoxDni.Enabled = false;
            textBoxDni.Location = new Point(669, 101);
            textBoxDni.Name = "textBoxDni";
            textBoxDni.Size = new Size(404, 31);
            textBoxDni.TabIndex = 9;
            // 
            // textBoxNombre
            // 
            textBoxNombre.Anchor = AnchorStyles.None;
            textBoxNombre.Location = new Point(669, 163);
            textBoxNombre.Name = "textBoxNombre";
            textBoxNombre.Size = new Size(404, 31);
            textBoxNombre.TabIndex = 10;
            // 
            // textBoxPuesto
            // 
            textBoxPuesto.Anchor = AnchorStyles.None;
            textBoxPuesto.Location = new Point(669, 225);
            textBoxPuesto.Name = "textBoxPuesto";
            textBoxPuesto.Size = new Size(404, 31);
            textBoxPuesto.TabIndex = 11;
            // 
            // textBoxCorreo
            // 
            textBoxCorreo.Anchor = AnchorStyles.None;
            textBoxCorreo.Location = new Point(669, 351);
            textBoxCorreo.Name = "textBoxCorreo";
            textBoxCorreo.Size = new Size(404, 31);
            textBoxCorreo.TabIndex = 13;
            // 
            // textBoxTelefono
            // 
            textBoxTelefono.Anchor = AnchorStyles.None;
            textBoxTelefono.Location = new Point(669, 413);
            textBoxTelefono.Name = "textBoxTelefono";
            textBoxTelefono.Size = new Size(404, 31);
            textBoxTelefono.TabIndex = 14;
            // 
            // buttonGuardar
            // 
            buttonGuardar.Location = new Point(961, 482);
            buttonGuardar.Name = "buttonGuardar";
            buttonGuardar.Size = new Size(112, 34);
            buttonGuardar.TabIndex = 15;
            buttonGuardar.Text = "Guardar";
            buttonGuardar.UseVisualStyleBackColor = true;
            buttonGuardar.Click += buttonGuardar_Click;
            // 
            // buttonBorrar
            // 
            buttonBorrar.Location = new Point(815, 482);
            buttonBorrar.Name = "buttonBorrar";
            buttonBorrar.Size = new Size(112, 34);
            buttonBorrar.TabIndex = 16;
            buttonBorrar.Text = "Borrar";
            buttonBorrar.UseVisualStyleBackColor = true;
            buttonBorrar.Click += buttonBorrar_Click;
            // 
            // buttonNuevo
            // 
            buttonNuevo.Location = new Point(669, 482);
            buttonNuevo.Name = "buttonNuevo";
            buttonNuevo.Size = new Size(112, 34);
            buttonNuevo.TabIndex = 17;
            buttonNuevo.Text = "Nuevo";
            buttonNuevo.UseVisualStyleBackColor = true;
            buttonNuevo.Click += buttonNuevo_Click;
            // 
            // comboBoxDepartamento
            // 
            comboBoxDepartamento.DropDownStyle = ComboBoxStyle.DropDownList;
            comboBoxDepartamento.FormattingEnabled = true;
            comboBoxDepartamento.Location = new Point(669, 287);
            comboBoxDepartamento.Name = "comboBoxDepartamento";
            comboBoxDepartamento.Size = new Size(404, 33);
            comboBoxDepartamento.TabIndex = 18;
            // 
            // buttonNuevoDep
            // 
            buttonNuevoDep.Location = new Point(669, 537);
            buttonNuevoDep.Name = "buttonNuevoDep";
            buttonNuevoDep.Size = new Size(190, 34);
            buttonNuevoDep.TabIndex = 19;
            buttonNuevoDep.Text = "Nuevo departamento";
            buttonNuevoDep.UseVisualStyleBackColor = true;
            buttonNuevoDep.Click += buttonNuevoDep_Click;
            // 
            // buttonBorrarDep
            // 
            buttonBorrarDep.Location = new Point(883, 537);
            buttonBorrarDep.Name = "buttonBorrarDep";
            buttonBorrarDep.Size = new Size(190, 34);
            buttonBorrarDep.TabIndex = 20;
            buttonBorrarDep.Text = "Borrar departamento";
            buttonBorrarDep.UseVisualStyleBackColor = true;
            buttonBorrarDep.Click += buttonBorrarDep_Click;
            // 
            // Vista
            // 
            AutoScaleDimensions = new SizeF(10F, 25F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1178, 644);
            Controls.Add(buttonBorrarDep);
            Controls.Add(buttonNuevoDep);
            Controls.Add(comboBoxDepartamento);
            Controls.Add(buttonNuevo);
            Controls.Add(buttonBorrar);
            Controls.Add(buttonGuardar);
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
            Controls.Add(listBoxEmpleados);
            Controls.Add(listBoxDepartamentos);
            FormBorderStyle = FormBorderStyle.FixedSingle;
            MaximizeBox = false;
            Name = "Vista";
            Text = "Gestión de empleados";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion
        private ListBox listBoxDepartamentos;
        private ListBox listBoxEmpleados;
        private Label labelDni;
        private Label labelNombre;
        private Label labelPuesto;
        private Label labelDepartamento;
        private Label labelCorreo;
        private Label labelTelefono;
        private TextBox textBoxDni;
        private TextBox textBoxNombre;
        private TextBox textBoxPuesto;
        private TextBox textBoxCorreo;
        private TextBox textBoxTelefono;
        private Button buttonGuardar;
        private Button buttonBorrar;
        private Button buttonNuevo;
        private ComboBox comboBoxDepartamento;
        private Button buttonNuevoDep;
        private Button buttonBorrarDep;
    }
}
