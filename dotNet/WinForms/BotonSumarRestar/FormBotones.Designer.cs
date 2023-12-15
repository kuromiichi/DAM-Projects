using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BotonSumarRestar;

partial class FormBotones
{
    private void InitializeComponent()
    {
        botonSumar = new Button();
        botonRestar = new Button();
        numeroText = new Label();
        SuspendLayout();
        // 
        // botonSumar
        // 
        botonSumar.Location = new Point(0, 0);
        botonSumar.Name = "botonSumar";
        botonSumar.Size = new Size(75, 23);
        botonSumar.TabIndex = 0;
        // 
        // botonRestar
        // 
        botonRestar.Location = new Point(0, 0);
        botonRestar.Name = "botonRestar";
        botonRestar.Size = new Size(75, 23);
        botonRestar.TabIndex = 0;
        // 
        // numeroText
        // 
        numeroText.Location = new Point(0, 0);
        numeroText.Name = "numeroText";
        numeroText.Size = new Size(100, 23);
        numeroText.TabIndex = 0;
        // 
        // FormBotones
        // 
        ClientSize = new Size(278, 244);
        Name = "FormBotones";
        Load += FormBotones_Load_1;
        ResumeLayout(false);
    }
}
