namespace Notepad;

partial class Notepad
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
        System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Notepad));
        menuStrip = new MenuStrip();
        archivoToolStripMenuItem = new ToolStripMenuItem();
        nuevoToolStripMenuItem = new ToolStripMenuItem();
        abrirToolStripMenuItem = new ToolStripMenuItem();
        guardarToolStripMenuItem = new ToolStripMenuItem();
        guardarComoToolStripMenuItem = new ToolStripMenuItem();
        imprimirToolStripMenuItem = new ToolStripMenuItem();
        editarToolStripMenuItem = new ToolStripMenuItem();
        deshacerToolStripMenuItem = new ToolStripMenuItem();
        cortarToolStripMenuItem = new ToolStripMenuItem();
        copiarToolStripMenuItem = new ToolStripMenuItem();
        pegarToolStripMenuItem = new ToolStripMenuItem();
        eliminarToolStripMenuItem = new ToolStripMenuItem();
        formatoToolStripMenuItem = new ToolStripMenuItem();
        fuenteToolStripMenuItem = new ToolStripMenuItem();
        verxToolStripMenuItem = new ToolStripMenuItem();
        barraDeEstadoToolStripMenuItem = new ToolStripMenuItem();
        ayudaToolStripMenuItem = new ToolStripMenuItem();
        acercaDeToolStripMenuItem = new ToolStripMenuItem();
        verAyudaToolStripMenuItem = new ToolStripMenuItem();
        textBox = new RichTextBox();
        statusStrip = new StatusStrip();
        springLabel = new ToolStripStatusLabel();
        cursorPosLabel = new ToolStripStatusLabel();
        menuStrip.SuspendLayout();
        statusStrip.SuspendLayout();
        SuspendLayout();
        // 
        // menuStrip
        // 
        menuStrip.ImageScalingSize = new Size(24, 24);
        menuStrip.Items.AddRange(new ToolStripItem[] { archivoToolStripMenuItem, editarToolStripMenuItem, formatoToolStripMenuItem, verxToolStripMenuItem, ayudaToolStripMenuItem });
        menuStrip.LayoutStyle = ToolStripLayoutStyle.Flow;
        menuStrip.Location = new Point(0, 0);
        menuStrip.Name = "menuStrip";
        menuStrip.Size = new Size(800, 33);
        menuStrip.TabIndex = 1;
        menuStrip.Text = "menuStrip";
        // 
        // archivoToolStripMenuItem
        // 
        archivoToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { nuevoToolStripMenuItem, abrirToolStripMenuItem, guardarToolStripMenuItem, guardarComoToolStripMenuItem, imprimirToolStripMenuItem });
        archivoToolStripMenuItem.Name = "archivoToolStripMenuItem";
        archivoToolStripMenuItem.Size = new Size(88, 29);
        archivoToolStripMenuItem.Text = "Archivo";
        // 
        // nuevoToolStripMenuItem
        // 
        nuevoToolStripMenuItem.Name = "nuevoToolStripMenuItem";
        nuevoToolStripMenuItem.ShortcutKeys = Keys.Control | Keys.N;
        nuevoToolStripMenuItem.Size = new Size(404, 34);
        nuevoToolStripMenuItem.Text = "Nuevo";
        nuevoToolStripMenuItem.Click += nuevoToolStripMenuItem_Click;
        // 
        // abrirToolStripMenuItem
        // 
        abrirToolStripMenuItem.Name = "abrirToolStripMenuItem";
        abrirToolStripMenuItem.ShortcutKeys = Keys.Control | Keys.A;
        abrirToolStripMenuItem.Size = new Size(404, 34);
        abrirToolStripMenuItem.Text = "Abrir";
        abrirToolStripMenuItem.Click += abrirToolStripMenuItem_Click;
        // 
        // guardarToolStripMenuItem
        // 
        guardarToolStripMenuItem.Name = "guardarToolStripMenuItem";
        guardarToolStripMenuItem.ShortcutKeys = Keys.Control | Keys.G;
        guardarToolStripMenuItem.Size = new Size(404, 34);
        guardarToolStripMenuItem.Text = "Guardar";
        guardarToolStripMenuItem.Click += guardarToolStripMenuItem_Click;
        // 
        // guardarComoToolStripMenuItem
        // 
        guardarComoToolStripMenuItem.Name = "guardarComoToolStripMenuItem";
        guardarComoToolStripMenuItem.ShortcutKeys = Keys.Control | Keys.Shift | Keys.S;
        guardarComoToolStripMenuItem.Size = new Size(404, 34);
        guardarComoToolStripMenuItem.Text = "Guardar como...";
        guardarComoToolStripMenuItem.Click += guardarComoToolStripMenuItem_Click;
        // 
        // imprimirToolStripMenuItem
        // 
        imprimirToolStripMenuItem.Name = "imprimirToolStripMenuItem";
        imprimirToolStripMenuItem.ShortcutKeys = Keys.Control | Keys.P;
        imprimirToolStripMenuItem.Size = new Size(404, 34);
        imprimirToolStripMenuItem.Text = "Imprimir";
        imprimirToolStripMenuItem.Click += imprimirToolStripMenuItem_Click;
        // 
        // editarToolStripMenuItem
        // 
        editarToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { deshacerToolStripMenuItem, cortarToolStripMenuItem, copiarToolStripMenuItem, pegarToolStripMenuItem, eliminarToolStripMenuItem });
        editarToolStripMenuItem.Name = "editarToolStripMenuItem";
        editarToolStripMenuItem.Size = new Size(85, 29);
        editarToolStripMenuItem.Text = "Edición";
        // 
        // deshacerToolStripMenuItem
        // 
        deshacerToolStripMenuItem.Name = "deshacerToolStripMenuItem";
        deshacerToolStripMenuItem.ShortcutKeys = Keys.Control | Keys.Z;
        deshacerToolStripMenuItem.Size = new Size(255, 34);
        deshacerToolStripMenuItem.Text = "Deshacer";
        deshacerToolStripMenuItem.Click += deshacerToolStripMenuItem_Click;
        // 
        // cortarToolStripMenuItem
        // 
        cortarToolStripMenuItem.Name = "cortarToolStripMenuItem";
        cortarToolStripMenuItem.ShortcutKeys = Keys.Control | Keys.X;
        cortarToolStripMenuItem.Size = new Size(255, 34);
        cortarToolStripMenuItem.Text = "Cortar";
        cortarToolStripMenuItem.Click += cortarToolStripMenuItem_Click;
        // 
        // copiarToolStripMenuItem
        // 
        copiarToolStripMenuItem.Name = "copiarToolStripMenuItem";
        copiarToolStripMenuItem.ShortcutKeys = Keys.Control | Keys.C;
        copiarToolStripMenuItem.Size = new Size(255, 34);
        copiarToolStripMenuItem.Text = "Copiar";
        copiarToolStripMenuItem.Click += copiarToolStripMenuItem_Click;
        // 
        // pegarToolStripMenuItem
        // 
        pegarToolStripMenuItem.Name = "pegarToolStripMenuItem";
        pegarToolStripMenuItem.ShortcutKeys = Keys.Control | Keys.V;
        pegarToolStripMenuItem.Size = new Size(255, 34);
        pegarToolStripMenuItem.Text = "Pegar";
        pegarToolStripMenuItem.Click += pegarToolStripMenuItem_Click;
        // 
        // eliminarToolStripMenuItem
        // 
        eliminarToolStripMenuItem.Name = "eliminarToolStripMenuItem";
        eliminarToolStripMenuItem.ShortcutKeys = Keys.Delete;
        eliminarToolStripMenuItem.Size = new Size(255, 34);
        eliminarToolStripMenuItem.Text = "Eliminar";
        eliminarToolStripMenuItem.Click += eliminarToolStripMenuItem_Click;
        // 
        // formatoToolStripMenuItem
        // 
        formatoToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { fuenteToolStripMenuItem });
        formatoToolStripMenuItem.Name = "formatoToolStripMenuItem";
        formatoToolStripMenuItem.Size = new Size(96, 29);
        formatoToolStripMenuItem.Text = "Formato";
        // 
        // fuenteToolStripMenuItem
        // 
        fuenteToolStripMenuItem.Name = "fuenteToolStripMenuItem";
        fuenteToolStripMenuItem.Size = new Size(179, 34);
        fuenteToolStripMenuItem.Text = "Fuente...";
        fuenteToolStripMenuItem.Click += fuenteToolStripMenuItem_Click;
        // 
        // verxToolStripMenuItem
        // 
        verxToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { barraDeEstadoToolStripMenuItem });
        verxToolStripMenuItem.Name = "verxToolStripMenuItem";
        verxToolStripMenuItem.Size = new Size(53, 29);
        verxToolStripMenuItem.Text = "Ver";
        // 
        // barraDeEstadoToolStripMenuItem
        // 
        barraDeEstadoToolStripMenuItem.Checked = true;
        barraDeEstadoToolStripMenuItem.CheckOnClick = true;
        barraDeEstadoToolStripMenuItem.CheckState = CheckState.Checked;
        barraDeEstadoToolStripMenuItem.Name = "barraDeEstadoToolStripMenuItem";
        barraDeEstadoToolStripMenuItem.Size = new Size(238, 34);
        barraDeEstadoToolStripMenuItem.Text = "Barra de estado";
        barraDeEstadoToolStripMenuItem.Click += barraDeEstadoToolStripMenuItem_Click;
        // 
        // ayudaToolStripMenuItem
        // 
        ayudaToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { acercaDeToolStripMenuItem, verAyudaToolStripMenuItem });
        ayudaToolStripMenuItem.Name = "ayudaToolStripMenuItem";
        ayudaToolStripMenuItem.Size = new Size(79, 29);
        ayudaToolStripMenuItem.Text = "Ayuda";
        // 
        // acercaDeToolStripMenuItem
        // 
        acercaDeToolStripMenuItem.Name = "acercaDeToolStripMenuItem";
        acercaDeToolStripMenuItem.Size = new Size(270, 34);
        acercaDeToolStripMenuItem.Text = "Acerca de";
        acercaDeToolStripMenuItem.Click += acercaDeToolStripMenuItem_Click;
        // 
        // verAyudaToolStripMenuItem
        // 
        verAyudaToolStripMenuItem.Name = "verAyudaToolStripMenuItem";
        verAyudaToolStripMenuItem.Size = new Size(270, 34);
        verAyudaToolStripMenuItem.Text = "Ver ayuda";
        verAyudaToolStripMenuItem.Click += verAyudaToolStripMenuItem_Click;
        // 
        // textBox
        // 
        textBox.AcceptsTab = true;
        textBox.DetectUrls = false;
        textBox.Dock = DockStyle.Fill;
        textBox.Location = new Point(0, 33);
        textBox.Margin = new Padding(0);
        textBox.Name = "textBox";
        textBox.Size = new Size(800, 417);
        textBox.TabIndex = 0;
        textBox.Text = "";
        textBox.SelectionChanged += TextBox_SelectionChanged;
        textBox.TextChanged += textBox_TextChanged;
        // 
        // statusStrip
        // 
        statusStrip.ImageScalingSize = new Size(24, 24);
        statusStrip.Items.AddRange(new ToolStripItem[] { springLabel, cursorPosLabel });
        statusStrip.Location = new Point(0, 418);
        statusStrip.Name = "statusStrip";
        statusStrip.Size = new Size(800, 32);
        statusStrip.TabIndex = 2;
        statusStrip.Text = "statusStrip";
        // 
        // springLabel
        // 
        springLabel.Name = "springLabel";
        springLabel.Size = new Size(690, 25);
        springLabel.Spring = true;
        // 
        // cursorPosLabel
        // 
        cursorPosLabel.Name = "cursorPosLabel";
        cursorPosLabel.Size = new Size(95, 25);
        cursorPosLabel.Text = "Ln 1, Col 1";
        // 
        // Notepad
        // 
        AutoScaleDimensions = new SizeF(10F, 25F);
        AutoScaleMode = AutoScaleMode.Font;
        AutoScroll = true;
        ClientSize = new Size(800, 450);
        Controls.Add(statusStrip);
        Controls.Add(textBox);
        Controls.Add(menuStrip);
        Icon = (Icon)resources.GetObject("$this.Icon");
        MainMenuStrip = menuStrip;
        Name = "Notepad";
        Text = "Bloc de notas";
        menuStrip.ResumeLayout(false);
        menuStrip.PerformLayout();
        statusStrip.ResumeLayout(false);
        statusStrip.PerformLayout();
        ResumeLayout(false);
        PerformLayout();
    }

    #endregion
    private MenuStrip menuStrip;
    private ToolStripMenuItem archivoToolStripMenuItem;
    private ToolStripMenuItem editarToolStripMenuItem;
    private ToolStripMenuItem formatoToolStripMenuItem;
    private ToolStripMenuItem verxToolStripMenuItem;
    private ToolStripMenuItem nuevoToolStripMenuItem;
    private ToolStripMenuItem abrirToolStripMenuItem;
    private ToolStripMenuItem guardarToolStripMenuItem;
    private ToolStripMenuItem guardarComoToolStripMenuItem;
    private ToolStripMenuItem imprimirToolStripMenuItem;
    private ToolStripMenuItem deshacerToolStripMenuItem;
    private ToolStripMenuItem cortarToolStripMenuItem;
    private ToolStripMenuItem copiarToolStripMenuItem;
    private ToolStripMenuItem pegarToolStripMenuItem;
    private ToolStripMenuItem eliminarToolStripMenuItem;
    private ToolStripMenuItem fuenteToolStripMenuItem;
    private ToolStripMenuItem barraDeEstadoToolStripMenuItem;
    private ToolStripMenuItem ayudaToolStripMenuItem;
    private ToolStripMenuItem acercaDeToolStripMenuItem;
    private ToolStripMenuItem verAyudaToolStripMenuItem;
    private RichTextBox textBox;
    private StatusStrip statusStrip;
    private ToolStripStatusLabel springLabel;
    private ToolStripStatusLabel cursorPosLabel;
}
