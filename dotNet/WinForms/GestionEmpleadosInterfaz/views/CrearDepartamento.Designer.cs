namespace GestionEmpleadosInterfaz
{
    partial class CrearDepartamento
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
            textBoxNombre = new TextBox();
            labelNombre = new Label();
            buttonCrear = new Button();
            SuspendLayout();
            // 
            // textBoxNombre
            // 
            textBoxNombre.Location = new Point(67, 71);
            textBoxNombre.Name = "textBoxNombre";
            textBoxNombre.Size = new Size(404, 31);
            textBoxNombre.TabIndex = 26;
            // 
            // labelNombre
            // 
            labelNombre.AutoSize = true;
            labelNombre.Location = new Point(67, 43);
            labelNombre.Name = "labelNombre";
            labelNombre.Size = new Size(78, 25);
            labelNombre.TabIndex = 20;
            labelNombre.Text = "Nombre";
            // 
            // buttonCrear
            // 
            buttonCrear.Location = new Point(213, 147);
            buttonCrear.Name = "buttonCrear";
            buttonCrear.Size = new Size(112, 34);
            buttonCrear.TabIndex = 31;
            buttonCrear.Text = "Crear";
            buttonCrear.UseVisualStyleBackColor = true;
            buttonCrear.Click += buttonCrear_Click;
            // 
            // CrearDepartamento
            // 
            AutoScaleDimensions = new SizeF(10F, 25F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(538, 225);
            Controls.Add(buttonCrear);
            Controls.Add(textBoxNombre);
            Controls.Add(labelNombre);
            FormBorderStyle = FormBorderStyle.FixedDialog;
            MaximizeBox = false;
            MinimizeBox = false;
            Name = "CrearDepartamento";
            Text = "Crear departamento";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private TextBox textBoxNombre;
        private Label labelNombre;
        private Button buttonCrear;
    }
}