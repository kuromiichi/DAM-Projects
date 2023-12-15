using System.Drawing.Printing;

namespace Notepad;

public partial class Notepad : Form
{
    private string fileName;
    private string filePath;
    private string originalContents;
    public Notepad()
    {
        InitializeComponent();
        Load += Notepad_Load;
        FormClosing += Notepad_FormClosing;
    }

    private void Notepad_Load(object sender, EventArgs e)
    {
        ResetNotepad();
    }

    private void Notepad_FormClosing(object sender, FormClosingEventArgs e)
    {
        if (textBox.Text != originalContents)
            if (!GuardarDialog()) e.Cancel = true;
    }

    private void ResetNotepad()
    {
        textBox.Clear();
        fileName = "Sin t�tulo";
        ActualizarTitulo();
        filePath = "";
        originalContents = "";
    }

    private void ActualizarTitulo()
    {
        Text = $"{fileName}: Bloc de notas";
    }

    private bool GuardarDialog()
    {
        DialogResult res = MessageBox.Show(
            $"�Quieres guardar los cambios de {fileName}?",
            "Bloc de notas",
            MessageBoxButtons.YesNoCancel,
            MessageBoxIcon.None
        );

        if (res == DialogResult.Yes)
        {
            return GuardarComo();
        }
        else if (res == DialogResult.No)
        {
            ResetNotepad();
        }
        else return false;

        return true;
    }

    private bool GuardarComo()
    {
        using (SaveFileDialog save = new())
        {
            save.InitialDirectory = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments);
            save.FileName = fileName;
            save.Filter = "Archivos de texto (*.txt)|*.txt|Todos los archivos|*.*";
            save.Title = "Guardar como";

            if (save.ShowDialog() == DialogResult.OK)
            {
                filePath = save.FileName;
                fileName = Path.GetFileNameWithoutExtension(filePath);
                using (StreamWriter sw = new(filePath)) sw.Write(textBox.Text);
                Text = $"{fileName}: Bloc de notas";
                originalContents = textBox.Text;
                return true;
            }
            else return false;
        }
    }

    private void textBox_TextChanged(object sender, EventArgs e)
    {
        if (textBox.Text != originalContents && !textBox.Text.StartsWith('*'))
            Text = $"*{fileName}: Bloc de notas";
    }

    private void TextBox_SelectionChanged(object sender, EventArgs e)
    {
        int pos = textBox.SelectionStart;
        int line = textBox.GetLineFromCharIndex(pos);
        int col = pos - textBox.GetFirstCharIndexOfCurrentLine();

        cursorPosLabel.Text = $"Ln {line + 1}, Col {col + 1}";
    }

    private void nuevoToolStripMenuItem_Click(object sender, EventArgs e)
    {
        if (textBox.Text != originalContents) GuardarDialog();
        else ResetNotepad();
    }

    private void abrirToolStripMenuItem_Click(object sender, EventArgs e)
    {
        if (textBox.Text != originalContents)
            if (!GuardarDialog()) return;
        using (OpenFileDialog open = new())
        {
            open.InitialDirectory = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments);
            open.Filter = "Archivos de texto (*.txt)|*.txt|Todos los archivos|*.*";
            open.Title = "Abrir";

            if (open.ShowDialog() == DialogResult.OK)
            {
                filePath = open.FileName;
                fileName = Path.GetFileNameWithoutExtension(filePath);
                ActualizarTitulo();

                Stream stream = open.OpenFile();
                using (StreamReader streamReader = new(stream))
                {
                    originalContents = streamReader.ReadToEnd();
                    textBox.Text = originalContents;
                }
            }
        }
    }

    private void guardarToolStripMenuItem_Click(object sender, EventArgs e)
    {
        if (textBox.Text == originalContents) return;

        if (filePath == "") GuardarComo();
        else
        {
            using (StreamWriter sw = new(filePath)) sw.Write(textBox.Text);
            Text = $"{fileName}: Bloc de notas";
            originalContents = textBox.Text;
        }
    }
    private void guardarComoToolStripMenuItem_Click(object sender, EventArgs e)
    {
        GuardarComo();
    }

    private void imprimirToolStripMenuItem_Click(object sender, EventArgs e)
    {
        using (PrintDialog print = new())
        {
            if (print.ShowDialog() == DialogResult.OK)
            {
                PrintDocument document = new();
                document.PrintPage += Document_PrintPage;

                document.Print();
            }
        }
    }

    private void Document_PrintPage(object sender, PrintPageEventArgs e)
    {
        e.Graphics.DrawString(textBox.Text, textBox.Font, Brushes.Black, 100, 100);
    }

    private void deshacerToolStripMenuItem_Click(object sender, EventArgs e)
    {
        textBox.Undo();
    }

    private void cortarToolStripMenuItem_Click(object sender, EventArgs e)
    {
        textBox.Cut();
    }

    private void copiarToolStripMenuItem_Click(object sender, EventArgs e)
    {
        textBox.Copy();
    }

    private void pegarToolStripMenuItem_Click(object sender, EventArgs e)
    {
        textBox.Paste();
    }

    private void eliminarToolStripMenuItem_Click(object sender, EventArgs e)
    {
        if (textBox.SelectionLength > 0)
        {
            textBox.Text = textBox.Text.Remove(textBox.SelectionStart, textBox.SelectionLength);
        }
    }

    private void fuenteToolStripMenuItem_Click(object sender, EventArgs e)
    {
        using (FontDialog font = new())
        {
            if (font.ShowDialog() == DialogResult.OK)
            {
                if (textBox.SelectionLength == 0)
                {
                    // No text selected, set the font for the entire content
                    textBox.Font = font.Font;
                    textBox.ForeColor = font.Color;
                }
                else
                {
                    // Apply font and color to the selected text
                    textBox.SelectionFont = font.Font;
                    textBox.SelectionColor = font.Color;
                }
            }
        }
    }

    private void barraDeEstadoToolStripMenuItem_Click(object sender, EventArgs e)
    {
        statusStrip.Visible = !statusStrip.Visible;
    }

    private void acercaDeToolStripMenuItem_Click(object sender, EventArgs e)
    {
        MessageBox.Show(
            "Bloc de notas tremendamente funcional y completo\n" +
            "Emma Fern�ndez Barranco\n" +
            "2023 copyright no s� qu�",
            "Acerca de Bloc de notas",
            MessageBoxButtons.OK,
            MessageBoxIcon.Information
        );
    }

    private void verAyudaToolStripMenuItem_Click(object sender, EventArgs e)
    {
        System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo
        {
            FileName = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
            UseShellExecute = true
        });
    }
}
