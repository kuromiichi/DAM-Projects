using Microsoft.Win32;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
namespace Paint
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private Point mousePos { get; set; }
        private StrokeCollection strokes = new StrokeCollection();
        private Stack<StrokeCollection> stackUndo = new Stack<StrokeCollection>();
        private Stack<StrokeCollection> stackRedo = new Stack<StrokeCollection>();

        public MainWindow()
        {
            InitializeComponent();
            Title = "Dibujo";
            InkCanvas.StrokeCollected += NewStroke;
            stackUndo.Push(strokes);
        }

        private void NewStroke(object sender, InkCanvasStrokeCollectedEventArgs e)
        {
            strokes = InkCanvas.Strokes.Clone();
            stackUndo.Push(strokes);
            stackRedo.Clear();
        }


        private void Open_Executed(object sender, ExecutedRoutedEventArgs e)
        {
            OpenFileDialog ofd = new();
            ofd.Title = "Guardar dibujo";
            ofd.Filter = "Archivos de imagen|*.png;*.jpg;*.bmp";
            ofd.InitialDirectory = Environment.GetFolderPath(Environment.SpecialFolder.MyPictures);

            if (ofd.ShowDialog() == true)
            {
                InkCanvas.Strokes.Clear();
                BitmapImage bitmap = new(new Uri(ofd.FileName));
                Image image = new();
                image.Source = bitmap;
                InkCanvas.Children.Add(image);
                Title = System.IO.Path.GetFileNameWithoutExtension(ofd.FileName);
            }
        }

        private void Save_Executed(object sender, ExecutedRoutedEventArgs e)
        {
            RenderTargetBitmap rtb = new(
                (int)InkCanvas.ActualWidth,
                (int)InkCanvas.ActualHeight,
                96,
                96,
                PixelFormats.Default
            );

            SaveFileDialog sfd = new();
            sfd.FileName = "Dibujo";
            sfd.DefaultExt = ".png";
            sfd.Filter = "Archivos de imagen|*.png;*.jpg;*.jpeg;*.bmp|PNG (.png)|*.png|JPEG (.jpg)|*.jpg|Bitmap (.bmp)|*.bmp";

            if (sfd.ShowDialog() == true)
            {
                string ext = System.IO.Path.GetExtension(sfd.FileName);
                BitmapEncoder encoder;

                switch (ext)
                {
                    case ".png":
                        encoder = new PngBitmapEncoder();
                        break;
                    case ".bmp":
                        encoder = new BmpBitmapEncoder();
                        break;
                    case ".jpg":
                        encoder = new JpegBitmapEncoder();
                        break;
                    default:
                        encoder = new PngBitmapEncoder();
                        break;
                }

                encoder.Frames.Add(BitmapFrame.Create(rtb));
                using (FileStream fs = new(sfd.FileName, FileMode.Create))
                {
                    encoder.Save(fs);
                }

                Title = System.IO.Path.GetFileNameWithoutExtension(sfd.FileName);
            }
        }

        private void Copy_Executed(object sender, ExecutedRoutedEventArgs e) { }

        private void Paste_Executed(object sender, ExecutedRoutedEventArgs e) { }

        private void Undo_Executed(object sender, ExecutedRoutedEventArgs e)
        {
            if (stackUndo.Count > 0)
            {
                stackRedo.Push(stackUndo.Pop());
                InkCanvas.Strokes = stackUndo.Peek();
            }
        }

        private void Redo_Executed(object sender, ExecutedRoutedEventArgs e)
        {
            if (stackRedo.Count > 0)
            {
                stackUndo.Push(stackRedo.Pop());
                InkCanvas.Strokes = stackUndo.Peek();
            }
        }

        private void Window_MouseMove(object sender, MouseEventArgs e)
        {
            mousePos = e.GetPosition(this);
            if (mousePos.Y > 0 && mousePos.Y < 50 && e.LeftButton == MouseButtonState.Released)
            {
                Toolbar.Visibility = Visibility.Visible;
                InkCanvas.Margin = new Thickness(0, 30, 0, 0);
            }
            else
            {
                Toolbar.Visibility = Visibility.Collapsed;
                InkCanvas.Margin = new Thickness();
            }
        }

        private void ButtonBrush_Click(object sender, RoutedEventArgs e)
        {
            InkCanvas.EditingMode = InkCanvasEditingMode.Ink;
        }

        private void ButtonSelect_Click(object sender, RoutedEventArgs e)
        {
            InkCanvas.EditingMode = InkCanvasEditingMode.Select;
        }

        private void SelectColor(object sender, MouseButtonEventArgs e)
        {
            var ellipse = (Ellipse)sender;
            SolidColorBrush brush = (SolidColorBrush)ellipse.Fill;
            Color color = brush.Color;
            InkCanvas.DefaultDrawingAttributes.Color = color;
            ResetEllipse();
            ellipse.Height = 18;
            ellipse.Width = 18;
        }
        private void ResetEllipse()
        {
            Black.Height = 15;
            Black.Width = 15;
            Blue.Height = 15;
            Blue.Width = 15;
            Green.Height = 15;
            Green.Width = 15;
            DarkRed.Height = 15;
            DarkRed.Width = 15;
            Red.Height = 15;
            Red.Width = 15;
            Orange.Height = 15;
            Orange.Width = 15;
            Salmon.Height = 15;
            Salmon.Width = 15;
            White.Height = 15;
            White.Width = 15;

            Black.Effect = null;
            Blue.Effect = null;
            Green.Effect = null;
            DarkRed.Effect = null;
            Red.Effect = null;
            Orange.Effect = null;
            Salmon.Effect = null;
            White.Effect = null;
        }
    }
}