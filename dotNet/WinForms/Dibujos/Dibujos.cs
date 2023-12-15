namespace Dibujos;

internal class Dibujos : Form
{
    private int platillo_y = 0;
    private int nube_x = 500;

    public Dibujos()
    {
        InitializeComponent();
        Paint += Dibujos_Paint;
        DoubleBuffered = true;
        MoverPlatillo();
        MoverNube();
    }

    private void InitializeComponent()
    {
        Size = new Size(1000, 1000);
    }

    protected void Dibujos_Paint(object sender, PaintEventArgs e)
    {
        Graphics g = e.Graphics;
        // Fondo
        g.FillRectangle(Brushes.SkyBlue, 0, 0, 1000, 1000);

        // Nubes
        g.FillEllipse(Brushes.White, nube_x, 75 + 300, 150, 100);
        g.FillEllipse(Brushes.White, nube_x + 50, 25 + 300, 200, 150);
        g.FillEllipse(Brushes.White, nube_x + 150, 75 + 300, 150, 100);

        // Platillo
        g.FillEllipse(Brushes.Red, 250, 600 - platillo_y, 500, 150);
        g.FillRectangle(Brushes.SkyBlue, 250, 600 - platillo_y, 500, 30);
        g.FillEllipse(Brushes.CadetBlue, 350, 400 - platillo_y, 300, 200);
        g.FillRectangle(Brushes.CadetBlue, 350, 500 - platillo_y, 300, 130);
    }

    private void MoverPlatillo()
    {
        int direccion = 3;
        var timer = new System.Windows.Forms.Timer();
        timer.Interval = 25;
        timer.Tick += (_, _) =>
        {
            platillo_y += direccion;
            if (platillo_y >= 50 || platillo_y <= -50)
            {
                direccion *= -1;
            }
            Invalidate();
        };
        timer.Start();
    }

    private void MoverNube()
    {
        var timer = new System.Windows.Forms.Timer();
        timer.Interval = 25;
        timer.Tick += (_, _) =>
        {
            nube_x -= 5;
            if (nube_x < -300)
            {
                nube_x = 1000;
            }
            Invalidate();
        };
        timer.Start();
    }
}
